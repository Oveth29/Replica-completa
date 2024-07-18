package com.example.replicaroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.replicaroom.adapter.CityViewModel
import com.example.replicaroom.adapter.CityViewModelFactory
import com.example.replicaroom.dao.DatabaseBuilder
import com.example.replicaroom.entity.City
import com.example.replicaroom.databinding.FragmentListCityBinding

class EditCityFragment : Fragment() {

    private lateinit var binding: EditCityFragment

    private val cityViewModel: CityViewModel by viewModels {
        CityViewModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = EditCityFragment.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments ?: return
        var cityId = arguments.getInt("city_id")

        showData(cityId)

        binding.btnSave.setOnClickListener {
            val updateCity = City(
                id= binding.tvIdCity.text.toString().toInt(),
                name = binding.tfName.editText?.text.toString(),
                description = binding.tfDescription.editText?.text.toString(),
                population = binding.tfPopulation.editText?.text.toString().toInt()
            )

            cityViewModel.updateCity(updateCity)
            Toast.makeText(context, "Registro actualizado", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }

    private fun showData(cityId: Int) {
        cityViewModel.getCity(cityId).observe(viewLifecycleOwner, Observer {
                city-> city?.let {
            binding.tvIdCity.text = it.id.toString()
            binding.tfName.editText?.setText(it.name)
            binding.tfDescription.editText?.setText(it.description)
            binding.tfPopulation.editText?.setText(it.population.toString())
        }
        })
    }

}