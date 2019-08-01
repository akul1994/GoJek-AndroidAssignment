package gojek.assignment.com.gojek_androidassignment.adapters

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gojek.assignment.com.gojek_androidassignment.R
import gojek.assignment.com.gojek_androidassignment.databinding.FragmentWeatherInfoBinding
import gojek.assignment.com.gojek_androidassignment.databinding.RowForecastBinding
import gojek.assignment.com.gojek_androidassignment.models.ForecastDayModel

/**
 * Created by Akul Aggarwal on 01/08/19.
 */
class ForecastAdapter(var forecast: ArrayList<ForecastDayModel>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding = RowForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecast.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        var model = forecast[p1]
        holder.binding.model = model
    }


    inner class ViewHolder(val binding: RowForecastBinding) : RecyclerView.ViewHolder(binding.root)
}