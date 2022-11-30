package com.kerencev.companies.presentation.main

import androidx.fragment.app.Fragment

interface Navigation {
    fun navigateToDetailsFragment(hideFragment: Fragment, companyId: String)
}