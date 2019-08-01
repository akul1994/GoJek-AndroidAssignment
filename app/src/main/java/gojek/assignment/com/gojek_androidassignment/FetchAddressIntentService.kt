package gojek.assignment.com.gojek_androidassignment

import android.app.IntentService
import android.content.Intent
import android.location.Geocoder
import java.util.*

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class FetchAddressIntentService(name : String) : IntentService(name)
{


    override fun onHandleIntent(p0: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())

    }

}