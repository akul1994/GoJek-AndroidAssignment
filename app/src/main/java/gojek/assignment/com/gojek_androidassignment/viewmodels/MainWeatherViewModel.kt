package gojek.assignment.com.gojek_androidassignment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import gojek.assignment.com.gojek_androidassignment.models.ApiStatus
import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository
import phonepe.interview.com.dunzo.utils.constants
import java.lang.StringBuilder

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class MainWeatherViewModel(application: Application) : AndroidViewModel(application) {

    var repository: MainRepository = MainRepository.getInstance(application)
    var statusModel: MutableLiveData<ApiStatus>
    var weatherResponseModel : MutableLiveData<WeatherResponseModel>
    var fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    lateinit var locationString: String

    var visibility = false

    init {
        weatherResponseModel=repository.weatherResponseModel
        statusModel = repository.apiStatusModel
        fetchLocation()
    }


    fun fetchLocation() {
        if (ContextCompat.checkSelfPermission(
                getApplication(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    var loc=location
                    if(loc==null) {
                        loc = Location("dummy")
                        loc.latitude = 20.3
                        loc.longitude = 52.6
                    }
                    val sb = StringBuilder(loc!!.latitude.toString())
                    sb.append(",")
                    sb.append(loc.longitude.toString())
                    locationString = sb.toString()
                    repository.makeWeatherForecastRequest(constants.DAYS, locationString)
                }
        }
    }

    inner class MyClickHandlers(internal var context: Context) {

        fun onRetry(view: View) {
            repository.makeWeatherForecastRequest(constants.DAYS, locationString)
        }
    }
}