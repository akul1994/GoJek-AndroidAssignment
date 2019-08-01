package phonepe.interview.com.dunzo.network

import gojek.assignment.com.gojek_androidassignment.models.WeatherResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Akul Aggarwal on 28/07/19.
 */
interface EndpointInterface {

    @GET("/v1/forecast.json")
    fun getSearchResults(@Query("q") query: String, @Query("key") key: String, @Query("days") days: Int)
            : Call<WeatherResponseModel>


}