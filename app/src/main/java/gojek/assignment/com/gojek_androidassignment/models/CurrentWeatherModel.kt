package gojek.assignment.com.gojek_androidassignment.models

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
data class CurrentWeatherModel(var last_updated_epoch : Long, var temp_c : Int,var wind_kph : String, var feelslike_c : String)