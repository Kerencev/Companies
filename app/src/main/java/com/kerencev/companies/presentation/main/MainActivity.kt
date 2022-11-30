package com.kerencev.companies.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kerencev.companies.R
import com.kerencev.companies.presentation.details.DetailsFragment
import com.kerencev.companies.presentation.listcompanies.ListCompaniesFragment

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ListCompaniesFragment())
                .commit()
        }
    }

    override fun navigateToDetailsFragment(hideFragment: Fragment, companyId: String) {
        supportFragmentManager.beginTransaction()
            .hide(hideFragment)
            .add(R.id.fragmentContainer, DetailsFragment.newInstance(companyId))
            .addToBackStack("")
            .commit()
    }
}