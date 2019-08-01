package gojek.assignment.com.gojek_androidassignment.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import gojek.assignment.com.gojek_androidassignment.models.ApiResponse
import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel
import gojek.assignment.com.gojek_androidassignment.repository.MainRepository

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class MainWeatherViewModel(application : Application) : AndroidViewModel(application) {

    var repository: MainRepository = MainRepository.getInstance(application)
    var responseModel: MutableLiveData<ApiResponse>

    init {
        responseModel = repository.weatherResponseModel
    }

}