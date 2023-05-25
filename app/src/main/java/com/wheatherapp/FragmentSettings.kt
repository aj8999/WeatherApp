package com.wheatherapp


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wheatherapp.databinding.FragmentSettingsBinding
import com.wheatherapp.helper.AddressDb


class FragmentSettings : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    var db: AddressDb? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        binding.cvAboutUs.setOnClickListener {
            val intent = Intent(getActivity(), AboutUsActivity::class.java)
            getActivity()?.startActivity(intent)

        }

        db = AddressDb(context)
        binding.cvClearData.setOnClickListener {
            val insert = db!!.deleteAll()
            Toast.makeText(context, "All Data Cleared Successfully", Toast.LENGTH_SHORT).show()

        }




        return binding.root
    }

    companion object {
        fun newInstance() = FragmentCity()

    }

}