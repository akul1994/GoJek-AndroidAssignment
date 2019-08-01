package phonepe.interview.com.dunzo.network

import phonepe.interview.com.dunzo.models.SearchResultResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Akul Aggarwal on 28/07/19.
 */
interface EndpointInterface
{

  @GET("/customsearch/v1")
  fun getSearchResults(@Query("q") searchQuery: String,@Query("cx") cx: String,@Query("key") key: String,@Query("start") start : Int)
    :Call<SearchResultResponseModel>


}