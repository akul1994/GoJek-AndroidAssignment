package gojek.assignment.com.gojek_androidassignment.fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gojek.assignment.com.gojek_androidassignment.R
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel

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

    lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_weather_info, container, false)
        return mView
    }

    private fun init()
    {

    }


}
