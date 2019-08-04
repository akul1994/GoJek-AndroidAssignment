package gojek.assignment.com.gojek_androidassignment.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Akul Aggarwal on 04/08/19.
 */
object BindingUtils {

    @JvmStatic
    @BindingAdapter("app:format", "app:argId")
    fun setFormattedText(textView: TextView, format: String, argId: Int) {
        if (argId == 0) return
        textView.text = String.format(format, textView.resources.getString(argId))
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
