package com.wheatherapp

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.wheatherapp.databinding.ActivitySplashBinding
import com.wheatherapp.helper.ConnectionDetector

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    var timer: CountDownTimer? = null
    private val ACCESS_GEO_LOCATION = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    var REQUEST_LOCATION = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verifyLocationPermissions(this)
        timer = object : CountDownTimer(2000, 20) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val cd = ConnectionDetector.getInstance(this@SplashActivity)

                if (cd.isConnectionAvailable) {
                    val intent = Intent(this@SplashActivity, HomeScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    AlertDialog.Builder(this@SplashActivity)
                        .setTitle(R.string.app_name)
                        .setMessage(getString(R.string.nointernet)).setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                         recreate()
                        })
                        .show()
                }

            }
        }.start()
    }
    private fun verifyLocationPermissions(activity: Activity) {
        // Check if we have write permission
        val permission =
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.BROADCAST_SMS)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                ACCESS_GEO_LOCATION,
                REQUEST_LOCATION
            )
        }
    }
}