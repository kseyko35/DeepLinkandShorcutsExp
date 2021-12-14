package com.kseyko.navigationdeeplinkwidgetsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kseyko.navigationdeeplinkwidgetsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createDynamicShortcutMenu()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_sign_up, R.id.navigation_forget_password
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_welcome -> binding.navView.visibility = View.GONE
                R.id.navigation_settings -> binding.navView.visibility = View.GONE
                R.id.navigation_forget_password -> {
                    val argumentProfile =
                        NavArgument.Builder().setDefaultValue("Bottom Navigation Button").build()
                    destination.addArgument("forgetpassword", argumentProfile)
                    binding.navView.visibility = View.VISIBLE
                }
                else -> binding.navView.visibility = View.VISIBLE
            }
        }
    }

    private fun createDynamicShortcutMenu() {
        val args = PersistableBundle()
        args.putString("forgetpassword", "From Shortcut")

        val shortcutWebsite = ShortcutInfoCompat.Builder(this, "id1")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_home_black_24dp))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://developer.android.com/guide/topics/ui/shortcuts/creating-shortcuts")
                )
            )
            .build()
        val shortcutForgetPassword = ShortcutInfoCompat.Builder(this, "id2")
            .setShortLabel("Forget Password")
            .setLongLabel("Forget Password")
            .setExtras(args)
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_forget_password))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    "forgetPasswordFragment://forgetPassword".toUri(),
                    this,
                    MainActivity::class.java
                )
            )
            .build()

        ShortcutManagerCompat.setDynamicShortcuts(
            this,
            listOf(shortcutWebsite, shortcutForgetPassword)
        )

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}