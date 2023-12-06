package com.mintokoneko.helloworld.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.mintokoneko.helloworld.R
import com.mintokoneko.helloworld.databinding.FragmentLoginBinding
import com.mintokoneko.helloworld.repository.MainRepository
import com.mintokoneko.helloworld.repository.data.Credentials
import com.mintokoneko.helloworld.ui.content.ContentFragment
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModel
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModelFactory
import com.mintokoneko.helloworld.utils.getViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(requireActivity(), requireContext())
        initObservers(requireActivity())

        binding.login.setOnClickListener {
            setOnLoginClickBehavior(requireContext())
        }
    }

    private fun setOnLoginClickBehavior(context: Context) {
        val login = binding.loginField.editText?.text.toString()
        val password = binding.passwordField.editText?.text.toString()

        if (login.isNotEmpty() && password.isNotEmpty()) {
            viewModel.setCredentials(Credentials(login, password))
        } else {
            Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservers(activity: FragmentActivity) {
        viewModel.credentials.observe(viewLifecycleOwner) { credentials ->
            if (credentials != null) {
                beginTransaction(activity)
            }
        }
    }

    private fun beginTransaction(activity: FragmentActivity) {
        val mainFragmentId = R.id.main_fragment
        val contentFragment = ContentFragment()

        val contentTransaction = activity.supportFragmentManager.beginTransaction()
        contentTransaction.replace(mainFragmentId, contentFragment).addToBackStack(null).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}