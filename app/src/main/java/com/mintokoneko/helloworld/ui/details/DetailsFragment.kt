package com.mintokoneko.helloworld.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.helloworld.databinding.FragmentDetailsBinding
import com.mintokoneko.helloworld.repository.MainRepository
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModel
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModelFactory
import com.mintokoneko.helloworld.utils.getViewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(requireActivity(), requireContext())
        initObservers()
    }

    private fun initObservers() {
        viewModel.currentCountry.observe(viewLifecycleOwner) { country ->
            binding.countryName.text = country.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}