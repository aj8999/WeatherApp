package com.wheatherapp


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.wheatherapp.Adapters.CityAdapter
import com.wheatherapp.databinding.FragmentCityBinding
import com.wheatherapp.helper.AddreesData
import com.wheatherapp.helper.AddressDb


class FragmentCity : Fragment() {
    private lateinit var binding: FragmentCityBinding
    private var mDatabase: AddressDb? = null
    private lateinit var cityadapter: CityAdapter
    var arraylistcity: ArrayList<AddreesData> = ArrayList<AddreesData>()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var locationRequest: LocationRequest? = null
    val REQUEST_CHECK_LOCATION_PERMISSIONG = 1001
    private val ACCESS_GEO_LOCATION = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    var REQUEST_LOCATION = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityBinding.inflate(layoutInflater)
        mDatabase = AddressDb(activity)



        layoutManager = LinearLayoutManager(activity)


        binding.btnAdd.setOnClickListener {
            val intent = Intent (getActivity(), SelectMapsActivity::class.java)
            getActivity()?.startActivity(intent)

        }
        checkLocationPermission()
        activity?.let { verifyLocationPermissions(it) }
        return binding.root
    }

    companion object {
        fun newInstance() = FragmentCity()

    }


    override fun onResume() {
        super.onResume()
        val addressDATA: ArrayList<AddreesData> = mDatabase!!.listData()

        if (addressDATA.size != 0) {
            binding.rvCity.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = CityAdapter(addressDATA,activity)
            }

            binding.ivNoData.setVisibility(View.GONE)
        }else binding.ivNoData.setVisibility(View.VISIBLE)
    }

    private fun checkLocationPermission() {
        locationRequest = LocationRequest.create()
        locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest!!.setInterval(3000)
        locationRequest!!.setFastestInterval(2000)
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!)
        builder.setAlwaysShow(true)
        val result = LocationServices.getSettingsClient(
            requireActivity().applicationContext
        ).checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
            } catch (e: ApiException) {
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(
                            requireActivity(),
                            REQUEST_CHECK_LOCATION_PERMISSIONG
                        )
                    } catch (sendIntentException: SendIntentException) {
                        sendIntentException.printStackTrace()
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CHECK_LOCATION_PERMISSIONG -> if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(activity, "GPS is Turned On", Toast.LENGTH_SHORT).show()

            } else if (resultCode == Activity.RESULT_CANCELED) {
                checkLocationPermission()
                Toast.makeText(activity, "GPS is Off", Toast.LENGTH_SHORT).show()
            }
        }
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