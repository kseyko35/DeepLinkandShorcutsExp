package com.kseyko.navigationdeeplinkwidgetsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kseyko.navigationdeeplinkwidgetsample.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.signup.setOnClickListener {
            if (binding.email.text.isNotEmpty() && binding.username.text.isNotEmpty() && binding.password.text.isNotEmpty()){
                findNavController().navigate(R.id.action_navigation_sign_up_to_navigation_home)
                Toast.makeText(requireContext(),"Account is created.", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(requireContext(),"Please fill all content.", Toast.LENGTH_SHORT).show()

        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}