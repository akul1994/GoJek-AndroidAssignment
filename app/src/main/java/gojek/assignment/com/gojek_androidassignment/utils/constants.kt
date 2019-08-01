package phonepe.interview.com.dunzo.utils

/**
 * Created by Akul Aggarwal on 28/07/19.
 */
object constants
{

    object UrlConstants
    {
        const val BASE_URL = "https://api.apixu.com/"
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
        COMPLETED
    }

    const val SUCCESS_RESULT = 0
    const val FAILURE_RESULT = 1
    const val PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress"
    const val RECEIVER = "$PACKAGE_NAME.RECEIVER"
    const val RESULT_DATA_KEY = "${PACKAGE_NAME}.RESULT_DATA_KEY"
    const val LOCATION_DATA_EXTRA = "${PACKAGE_NAME}.LOCATION_DATA_EXTRA"

}

