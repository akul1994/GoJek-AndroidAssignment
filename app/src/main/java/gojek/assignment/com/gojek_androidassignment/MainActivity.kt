package gojek.assignment.com.gojek_androidassignment

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.View
import gojek.assignment.com.gojek_androidassignment.fragments.WeatherInfoFragment
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import phonepe.interview.com.dunzo.utils.constants
import android.view.animation.LinearInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        checkAndAskPermission()
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

    private fun clearAllFragments() {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    private val REQ_CODE_PERMISSIONS = 101

    private fun checkAndAskPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQ_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQ_CODE_PERMISSIONS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    weatherViewModel.fetchLocation()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }



}
