package com.kseyko.navigationdeeplinkwidgetsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kseyko.navigationdeeplinkwidgetsample.databinding.WelcomeFragmentBinding


class WelcomeFragment : Fragment() {
    private val safeArgs: WelcomeFragmentArgs by navArgs()
    private var _binding: WelcomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WelcomeFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.welcome).plus(" " + safeArgs.userName)

        binding.welcomeName.text = safeArgs.userName

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.welcome_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_logout -> {
                findNavController().popBackStack(R.id.navigation_welcome,true)
                findNavController().navigate(R.id.navigation_home)
            }
            R.id.navigation_settings -> findNavController().navigate(R.id.navigation_settings)
        }
        return super.onOptionsItemSelected(item)
    }

}