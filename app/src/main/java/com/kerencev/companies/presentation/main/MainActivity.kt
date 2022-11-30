package com.kerencev.companies.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kerencev.companies.R
import com.kerencev.companies.presentation.listcompanies.ListCompaniesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ListCompaniesFragment())
                .commit()
        }
    }
}