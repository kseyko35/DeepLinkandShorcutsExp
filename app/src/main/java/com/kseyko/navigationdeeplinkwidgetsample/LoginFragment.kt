package com.kseyko.navigationdeeplinkwidgetsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.kseyko.navigationdeeplinkwidgetsample.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.signin.setOnClickListener {
            if (binding.username.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                val action = LoginFragmentDirections.actionNavigationHomeToWelcomeFragment(binding.username.text.toString())
                findNavController().navigate(action)
            }

            else    Toast.makeText(requireContext(),"Please fill username and password",Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}