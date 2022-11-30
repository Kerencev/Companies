package com.kerencev.companies.presentation.listcompanies

import android.os.Bundle
import android.view.View
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.databinding.FragmentListCompaniesBinding
import com.kerencev.companies.di.COMPANIES_VIEW_MODEL
import com.kerencev.companies.presentation.base.BaseFragment
import com.kerencev.companies.presentation.base.BaseViewModel
import com.kerencev.companies.presentation.base.makeGone
import com.kerencev.companies.presentation.base.makeVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ListCompaniesFragment :
    BaseFragment<FragmentListCompaniesBinding, CompanyDto>(
        FragmentListCompaniesBinding::inflate
    ) {

    private val viewModel: BaseViewModel<ListCompaniesState<CompanyDto>> by viewModel(named(COMPANIES_VIEW_MODEL))
    private val adapter: ListCompaniesAdapter by lazy {
        ListCompaniesAdapter(object : ListCompaniesAdapter.OnListItemClickListener {
            override fun onItemClick(data: CompanyDto) {
                TODO("open details fragment")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun showSuccess(data: List<CompanyDto>) {
        with(binding) {
            recyclerView.makeVisible()
            errorInfo.makeGone()
            progress.makeGone()
            adapter.submitList(data)
        }
    }

    override fun showLoading() {
        with(binding) {
            recyclerView.makeGone()
            errorInfo.makeGone()
            progress.makeVisible()
        }
    }

    override fun showError() {
        with(binding) {
            recyclerView.makeGone()
            progress.makeGone()
            errorInfo.makeVisible()
            btnReload.setOnClickListener { viewModel.getData() }
        }
    }
}