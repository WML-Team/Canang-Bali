package com.arisurya.jetpackpro.canangbali.ui.information.canang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityCanangBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraViewModel
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status

class CanangActivity : AppCompatActivity() {
    private lateinit var canangBinding : ActivityCanangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canangBinding = ActivityCanangBinding.inflate(layoutInflater)
        setContentView(canangBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory)[CanangViewModel::class.java]

        val adapter = CanangAdapter()

        showProgressBar(true)
        viewModel.getCanang().observe(this, {canang->
            if(canang!=null){
                when(canang.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        showProgressBar(false)
                        adapter.setCanang(canang.data)
                        adapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        })

        canangBinding.apply {
            rvCanang.layoutManager = LinearLayoutManager(this@CanangActivity)
            rvCanang.setHasFixedSize(true)
            rvCanang.adapter = adapter
        }
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            canangBinding.progressBar.visibility = View.VISIBLE
        }else{
            canangBinding.progressBar.visibility = View.GONE
        }
    }


}