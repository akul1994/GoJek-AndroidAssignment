package gojek.assignment.com.gojek_androidassignment.fragments


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide.init

import gojek.assignment.com.gojek_androidassignment.R
import gojek.assignment.com.gojek_androidassignment.databinding.FragmentFragmentErrorBinding
import gojek.assignment.com.gojek_androidassignment.databinding.FragmentWeatherInfoBinding
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentError : Fragment() {

    private val weatherViewModel: MainWeatherViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainWeatherViewModel::class.java)
    }


    private lateinit var binding: FragmentFragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragmentErrorBinding.inflate(inflater, container, false)
        binding.handlers = weatherViewModel.MyClickHandlers(context!!)
        return binding.root
    }


}
