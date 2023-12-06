package com.mintokoneko.helloworld.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.mintokoneko.helloworld.databinding.FragmentContentBinding
import com.mintokoneko.helloworld.ui.countries.CountriesFragment
import com.mintokoneko.helloworld.ui.details.DetailsFragment

class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContent(requireActivity())
    }

    private fun setupContent(activity: FragmentActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()

        val countriesContainerId = binding.countriesContainer.id
        val countriesFragment = CountriesFragment()
        initTransaction(transaction, countriesContainerId, countriesFragment)

        val detailsFragmentId = binding.detailsFragment.id
        val detailsFragment = DetailsFragment()
        initTransaction(transaction, detailsFragmentId, detailsFragment)

        transaction.commit()
    }

    private fun initTransaction(
        transaction: FragmentTransaction,
        fragmentContainerId: Int,
        fragment: Fragment
    ) {
        transaction.replace(fragmentContainerId, fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}