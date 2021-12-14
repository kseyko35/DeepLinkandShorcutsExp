package com.kseyko.navigationdeeplinkwidgetsample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kseyko.navigationdeeplinkwidgetsample.R
import com.kseyko.navigationdeeplinkwidgetsample.databinding.FragmentForgetPasswordBinding
import java.util.*

class ForgetPasswordFragment : Fragment() {

    private var _binding: FragmentForgetPasswordBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        binding.sendPassword.setOnClickListener {
            if (binding.email.text.isNotEmpty()){
                Toast.makeText(requireContext(), "Your mail send. Please enter the key", Toast.LENGTH_SHORT).show()
                binding.sendPassword.visibility= View.GONE
                binding.emailLayout.visibility= View.GONE
                binding.changePassword.visibility= View.VISIBLE
                binding.keyPasswordLayout.visibility=View.VISIBLE
                createNotification()
            }
            else    Toast.makeText(requireContext(),"Please fill email correctly",Toast.LENGTH_SHORT).show()


        }
        binding.changePassword.setOnClickListener {
            Toast.makeText(requireContext(), "Your password changed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_forget_password_to_navigation_home)
        }
        return binding.root
    }

    private fun createNotification() {
        val notificationManager =
        context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH)
            )
        }

        val key = Random().nextInt(10000)
        val builder = NotificationCompat.Builder(
            requireContext(), "deeplink")
            .setContentTitle("Key from app")
            .setContentText(key.toString())
            .setSmallIcon(R.drawable.ic_forget_password)
            .setContentIntent(createDeepLink(key))
            .setAutoCancel(true)
        notificationManager.notify(0, builder.build())
    }

    private fun createDeepLink(key : Int): PendingIntent {
        val args = Bundle()
        args.putInt("passKey", key)
        args.putString("forgetpassword", "Notification")
        return findNavController().createDeepLink()
            .setDestination(R.id.navigation_forget_password)
            .setArguments(args)
            .createPendingIntent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forgetPasswordSituation.text = "Come from " +arguments?.getString("forgetpassword")
        if (arguments?.getInt("passKey")!=0){
            binding.keyPassword.setText(arguments?.getInt("passKey").toString());
            binding.sendPassword.visibility= View.GONE
            binding.emailLayout.visibility= View.GONE
            binding.changePassword.visibility= View.VISIBLE
            binding.keyPasswordLayout.visibility=View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}