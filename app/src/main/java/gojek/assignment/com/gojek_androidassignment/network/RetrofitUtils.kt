package phonepe.interview.com.dunzo.network

import android.content.Context
import phonepe.interview.com.dunzo.utils.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Akul Aggarwal on 28/07/19.
 */
object RetrofitUtils {

    fun getRetrofitClient(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(constants.UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}