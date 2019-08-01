package gojek.assignment.com.gojek_androidassignment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import gojek.assignment.com.gojek_androidassignment.fragments.WeatherInfoFragment
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*
import phonepe.interview.com.dunzo.utils.constants


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
            }
            constants.Status.SUCCESS -> {
                replaceFragmentWithAnimation(
                    WeatherInfoFragment(),
                    containerFrags.id,
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_bottom
                )
                weatherViewModel.responseModel.value = ApiResponse(constants.Status.COMPLETED, response.weatherResponseModel)
            }
            constants.Status.ERROR -> {
            }
            constants.Status.COMPLETED -> {
            }
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().addToBackStack("").func().commitAllowingStateLoss()
    }

    private fun replaceFragmentWithAnimation(
        fragment: Fragment,
        containerId: Int,
        startAnimation: Int,
        endAnimation: Int
    ) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(startAnimation, endAnimation, startAnimation, endAnimation)
            replace(containerId, fragment)
        }
    }
}
