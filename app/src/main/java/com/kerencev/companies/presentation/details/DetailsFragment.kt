package com.kerencev.companies.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.kerencev.companies.R
import com.kerencev.companies.data.remote.dto.CompanyDetailsDto
import com.kerencev.companies.databinding.FragmentDetailsBinding
import com.kerencev.companies.di.DETAILS_VIEW_MODEL
import com.kerencev.companies.presentation.base.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.util.*

class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, CompanyDetailsDto>(FragmentDetailsBinding::inflate) {

    private val viewModel: BaseViewModel<CompanyDetailsDto> by viewModel(named(DETAILS_VIEW_MODEL))
    private var companyId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        companyId = arguments?.getString(ARG_KEY)
        if (savedInstanceState == null) {
            viewModel.getData(companyId)
        }
        viewModel.liveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showSuccess(data: List<CompanyDetailsDto>) {
        val companyDetailsDto = data.first()
        with(binding) {
            errorInfo.makeGone()
            progress.makeGone()
            contentContainer.makeVisible()
            title.text = companyDetailsDto.name
            img.loadCircle(companyDetailsDto.img)
            description.text = companyDetailsDto.description
            url.text =
                if (companyDetailsDto.www.isEmpty()) {
                    "${url.text} ${resources.getString(R.string.no_data)}"
                } else {
                    "${url.text} ${companyDetailsDto.www}"
                }
            phone.text =
                if (companyDetailsDto.phone.isEmpty()) {
                    "${phone.text} ${resources.getString(R.string.no_data)}"
                } else {
                    "${phone.text} ${companyDetailsDto.phone}"
                }

            if (companyDetailsDto.lat == 0.0 || companyDetailsDto.lon == 0.0) {
                address.text = "${address.text} ${resources.getString(R.string.no_data)}"
                btnRoute.makeGone()
                return
            }

            address.text = "${address.text} ${
                getAddress(
                    lon = companyDetailsDto.lon,
                    lat = companyDetailsDto.lat
                )
            }"
            btnRoute.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("geo:${companyDetailsDto.lat}, ${companyDetailsDto.lon}")
                startActivity(intent)
            }
        }
    }

    override fun showLoading() {
        with(binding) {
            contentContainer.makeGone()
            errorInfo.makeGone()
            progress.makeVisible()
        }
    }

    override fun showError() {
        with(binding) {
            contentContainer.makeGone()
            progress.makeGone()
            errorInfo.makeVisible()
            errorInfo.setOnClickListener { viewModel.getData(companyId) }
        }
    }

    private fun getAddress(lon: Double, lat: Double): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address> =
            geocoder.getFromLocation(lat, lon, 1)
        return addresses.first().getAddressLine(0)
    }

    companion object {

        private const val ARG_KEY = "ARG_KEY"

        fun newInstance(id: String): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, id)
                }
            }
        }
    }
}