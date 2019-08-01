package gojek.assignment.com.gojek_androidassignment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils.replace
import android.view.View
import gojek.assignment.com.gojek_androidassignment.fragments.WeatherInfoFragment
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*
import phonepe.interview.com.dunzo.utils.constants
import android.view.animation.LinearInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import gojek.assignment.com.gojek_androidassignment.fragments.FragmentError


class MainActivity : AppCompatActivity() {

    private val weatherViewModel: MainWeatherViewModel by lazy {
        ViewModelProviders.of(this).get(MainWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        weatherViewModel.responseModel.observe(this, Observer<ApiResponse> {
            consumeResponse(it)
        })
        MainRepository.getInstance(this).makeWeatherForecastRequest(5, "Bangalore")
    }

    private fun consumeResponse(response: ApiResponse?) {
        if (response == null)
            return
        when (response.status) {

            constants.Status.LOADING -> {
                clearAllFragments()
                showLoader()
            }
            constants.Status.SUCCESS -> {
                cancelLoader()
                replaceFragmentWithAnimation(
                    WeatherInfoFragment(),
                    containerFrags,
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_bottom
                )
                weatherViewModel.responseModel.value =
                    ApiResponse(constants.Status.COMPLETED, response.weatherResponseModel)
            }
            constants.Status.ERROR -> {
                cancelLoader()
                replaceFragmentWithAnimation(
                    FragmentError(),
                    containerFrags,
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_bottom
                )
            }
            constants.Status.COMPLETED -> {
            }
        }
    }

    private lateinit var rotateAnimation: RotateAnimation

    private fun showLoader() {
        containerFrags.visibility = View.GONE
        rotateAnimation = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.interpolator = LinearInterpolator()
        imgLoading.visibility = View.VISIBLE
        imgLoading.startAnimation(rotateAnimation)

    }

    private fun cancelLoader() {
        imgLoading.visibility = View.GONE
        rotateAnimation.cancel()
        imgLoading.clearAnimation()
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commitAllowingStateLoss()
    }

    private fun replaceFragmentWithAnimation(
        fragment: Fragment,
        container: FrameLayout,
        startAnimation: Int,
        endAnimation: Int
    ) {
        container.visibility = View.VISIBLE
        supportFragmentManager.inTransaction {
            setCustomAnimations(startAnimation, endAnimation, startAnimation, endAnimation)
            replace(container.id, fragment)
        }
    }

    private fun clearAllFragments()
    {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }
}
