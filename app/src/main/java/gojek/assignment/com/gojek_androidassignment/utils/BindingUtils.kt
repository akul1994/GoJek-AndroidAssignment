package gojek.assignment.com.gojek_androidassignment.utils

import android.os.Build
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Akul Aggarwal on 04/08/19.
 */
object BindingUtils {

    @JvmStatic
    @BindingAdapter("animatedVisibility")
    fun setAnimatedVisibility(target: View, isVisible: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(target.rootView as ViewGroup)
        }
        target.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("dayText")
    fun setDayText(textView: TextView, epoch: Long) {

        val date = Date(epoch*1000)
        val sdf = SimpleDateFormat("EEEE")
        var day=sdf.format(date)
        textView.text = day
    }

}
