package com.example.replicaroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.replicaroom.adapter.CityAdapter
import com.example.replicaroom.adapter.CityViewModel
import com.example.replicaroom.adapter.CityViewModelFactory
import com.example.replicaroom.dao.DatabaseBuilder
import com.example.replicaroom.databinding.FragmentListCityBinding
import com.example.replicaroom.entity.City


class ListCityFragment : Fragment() {
    private lateinit var binding: FragmentListCityBinding

    private val cityViewModel : CityViewModel by viewModels {
        CityViewModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    private lateinit var cityListAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCityBinding.inflate(layoutInflater)
        startList()
        return binding.root
    }

    private fun startList() {

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listCityFragment_to_newCityFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityListAdapter = CityAdapter(requireContext(), emptyList(), this::onEditClick, this::onDeleteClick)
        binding.lvRegistros.adapter = cityListAdapter

        cityViewModel.allCities.observe(viewLifecycleOwner, Observer{
                cities -> cityListAdapter.updateCities(cities)
        } )

    }

    private fun onEditClick(city: City){
        val bundle = Bundle()
        bundle.putInt("city_id", city.id)
        findNavController().navigate(R.id.action_listCityFragment_to_newCityFragment, bundle)

    }

    private fun onDeleteClick(city: City){
        cityViewModel.deleteCity(city)
        Toast.makeText(context, "Registro eliminado", Toast.LENGTH_LONG).show()
    }

}
