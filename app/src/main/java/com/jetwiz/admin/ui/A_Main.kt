package com.jetwiz.admin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.jetwiz.admin.R
import com.jetwiz.admin.databinding.AMainBinding

class A_Main: AppCompatActivity() {

    lateinit var bind:AMainBinding
    lateinit var navController:NavController

    companion object {
        lateinit var instance: A_Main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this

        bind = AMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun addBackButtonListener() {
        supportActionBar?.let {actionbar ->
            val navLabel = navController.currentDestination!!.label!!

            if (!navLabel.equals("F_Categories")) {
                actionbar.setDisplayHomeAsUpEnabled(true)
                actionbar.setDisplayHomeAsUpEnabled(true)
            } else {
                actionbar.setDisplayHomeAsUpEnabled(false)
                actionbar.setDisplayHomeAsUpEnabled(false)
            }

            if (navLabel.equals("F_Categories")) {
                actionbar.title = "Categories"
            }
            if(navLabel.equals("F_Meals")) {
                actionbar.title = "Categories by Meal"
            }
            if(navLabel.equals("F_MealDetail")) {
                actionbar.title = "Meal Detail"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}