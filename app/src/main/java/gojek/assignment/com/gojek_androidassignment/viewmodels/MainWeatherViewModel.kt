package gojek.assignment.com.gojek_androidassignment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.location.Location
import android.view.View
import com.google.android.gms.location.LocationServices
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository
import java.lang.StringBuilder

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class MainWeatherViewModel(application: Application) : AndroidViewModel(application) {

    var repository: MainRepository = MainRepository.getInstance(application)
    var responseModel: MutableLiveData<ApiResponse>
    var fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    lateinit var locationString: String


    init {
        responseModel = repository.weatherResponseModel
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                val sb=StringBuilder(location!!.latitude.toString())
                sb.append(",")
                sb.append(location.longitude.toString())
                locationString=sb.toString()
                repository.makeWeatherForecastRequest(5,locationString)
            }
    }


    inner class MyClickHandlers(internal var context: Context) {

        fun onRetry(view: View) {
            repository.makeWeatherForecastRequest(5, locationString)
        }
    }
}