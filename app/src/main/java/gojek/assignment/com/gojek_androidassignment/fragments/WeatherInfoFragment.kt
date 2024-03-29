package gojek.assignment.com.gojek_androidassignment.fragments


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import gojek.assignment.com.gojek_androidassignment.adapters.ForecastAdapter
import gojek.assignment.com.gojek_androidassignment.databinding.FragmentWeatherInfoBinding
import kotlinx.android.synthetic.main.fragment_weather_info.view.*
import androidx.recyclerview.widget.DividerItemDecoration


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherInfoFragment : Fragment() {

    private val weatherViewModel: MainWeatherViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainWeatherViewModel::class.java)
    }


    private lateinit var binding: FragmentWeatherInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        binding.viewModel = weatherViewModel
        init()
        return binding.root
    }

    private fun init() {
        var list = binding.root.listForecast
        list.layoutManager = LinearLayoutManager(context!!)
        var adapter =
            ForecastAdapter(weatherViewModel.weatherResponseModel.value!!.forecast.forecastday)
        val dividerItemDecoration = DividerItemDecoration(
            list.getContext(),
            DividerItemDecoration.VERTICAL
        )
        list.addItemDecoration(dividerItemDecoration)
        list.adapter = adapter
        weatherViewModel.visibility=true
    }


}
