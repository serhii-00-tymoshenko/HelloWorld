package com.mintokoneko.helloworld.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintokoneko.helloworld.databinding.FragmentCountriesBinding
import com.mintokoneko.helloworld.repository.data.Country
import com.mintokoneko.helloworld.ui.countries.adapters.CountryAdapter
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModel
import com.mintokoneko.helloworld.utils.getViewModel

class CountriesFragment : Fragment() {
    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(requireActivity(), requireContext())

        setupRecycler(requireContext())
        fetchData()
        initObservers()
    }

    private fun fetchData() {
        val countries = viewModel.countries.value
        if (countries == null) {
            viewModel.fetchData()
        }
    }

    private fun initObservers() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countryAdapter.submitList(countries)
        }
    }

    private fun setupRecycler(context: Context) {
        countryAdapter = CountryAdapter { country -> setCurrentCountry(country) }
        val countriesRecycler = binding.countriesRecycler
        countriesRecycler.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setCurrentCountry(country: Country) {
        viewModel.setCurrentCountry(country)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}