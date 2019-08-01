package gojek.assignment.com.gojek_androidassignment.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.location.Location
import android.view.View
import com.google.android.gms.location.LocationServices
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class MainWeatherViewModel(application: Application) : AndroidViewModel(application) {

    var repository: MainRepository = MainRepository.getInstance(application)
    var responseModel: MutableLiveData<ApiResponse>
    var fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    lateinit var location: Location


    init {
        responseModel = repository.weatherResponseModel
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                this.location=location!!
            }
    }


    inner class MyClickHandlers(internal var context: Context) {

        fun onRetry(view: View) {
            repository.makeWeatherForecastRequest(5, "Bangalore")
        }
    }
}