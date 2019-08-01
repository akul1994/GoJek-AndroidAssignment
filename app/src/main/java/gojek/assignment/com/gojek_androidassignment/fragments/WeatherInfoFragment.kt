package gojek.assignment.com.gojek_androidassignment.fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gojek.assignment.com.gojek_androidassignment.R
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import android.databinding.DataBindingUtil
import gojek.assignment.com.gojek_androidassignment.databinding.FragmentWeatherInfoBinding


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
        binding.viewModel=weatherViewModel
        return binding.root
    }

    private fun init() {

    }


}