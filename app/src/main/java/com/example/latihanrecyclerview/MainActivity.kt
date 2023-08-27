package com.example.latihanrecyclerview

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Coffee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Sebelum Pakai ViewBinding
        setContentView(R.layout.activity_main)

        rvCoffee = findViewById(R.id.rv_coffee)
        rvCoffee.setHasFixedSize(true)*/

        //Setelah Pakai ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCoffee.setHasFixedSize(true)
        list.addAll(listCoffee)
        showRecyclerList()
    }

    private val listCoffee: ArrayList<Coffee>
        get() {
            val dataName = resources.getStringArray(R.array.data_nama)
            val dataDescription = resources.getStringArray(R.array.data_deskripsi)
            val dataPhoto = resources.obtainTypedArray(R.array.data_image)
            val listCoffee = java.util.ArrayList<Coffee>()
            for (i in dataName.indices) {
                val coffee = Coffee(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listCoffee.add(coffee)
            }
            return listCoffee
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvCoffee.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvCoffee.layoutManager = LinearLayoutManager(this)
        }
        val coffeeListAdapter = CoffeeListAdapter(list)
        binding.rvCoffee.adapter = coffeeListAdapter
        coffeeListAdapter.setOnItemClickCallback(object : CoffeeListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Coffee) {
                showSelectedCoffee(data)
            }
        })
    }

    private fun showSelectedCoffee(coffee: Coffee) {
        Toast.makeText(this, "Kamu memilih " + coffee.name, Toast.LENGTH_SHORT).show()
    }
}