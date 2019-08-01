package phonepe.interview.com.dunzo.network

import android.content.Context
import gojek.assignment.com.gojek_androidassignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import phonepe.interview.com.dunzo.utils.AppUtils
import phonepe.interview.com.dunzo.utils.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Akul Aggarwal on 28/07/19.
 */
object RetrofitUtils {

    fun getRetrofitClient(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(constants.UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(context))
            .build()
    }


    private fun getOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            builder.addInterceptor(interceptor)
            val interceptor1 = HttpLoggingInterceptor()
            interceptor1.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor1)
        }

        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        return builder.build()
    }
}