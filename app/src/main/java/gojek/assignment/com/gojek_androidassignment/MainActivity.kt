package gojek.assignment.com.gojek_androidassignment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
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

    private fun init()
    {
        weatherViewModel.responseModel.observe(this, Observer<ApiResponse> {
            consumeResponse(it)
        })
    }

    private fun consumeResponse(response: ApiResponse?)
    {
        if(response==null)
            return
        when(response.status)
        {

            constants.Status.LOADING -> TODO()
            constants.Status.SUCCESS -> TODO()
            constants.Status.ERROR -> TODO()
            constants.Status.COMPLETED -> TODO()
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
