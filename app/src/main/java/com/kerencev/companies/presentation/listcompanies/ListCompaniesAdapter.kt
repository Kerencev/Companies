package com.kerencev.companies.presentation.listcompanies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kerencev.companies.data.remote.dto.CompanyDto
import com.kerencev.companies.databinding.ItemRvCompanyBinding
import com.kerencev.companies.presentation.base.loadCircle

class ListCompaniesAdapter(private val clickListener: OnListItemClickListener) :
    ListAdapter<CompanyDto, ListCompaniesAdapter.CompanyViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCompanyBinding.inflate(inflater, parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CompanyViewHolder(private val binding: ItemRvCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(companyDto: CompanyDto) {
            with(binding) {
                itemRvImage.loadCircle(companyDto.img)
                itemRvTitle.text = companyDto.name
                itemRvCard.setOnClickListener {
                    clickListener.onItemClick(companyDto)
                }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: CompanyDto)
    }

    class ItemCallback : DiffUtil.ItemCallback<CompanyDto>() {

        override fun areItemsTheSame(oldItem: CompanyDto, newItem: CompanyDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CompanyDto, newItem: CompanyDto): Boolean {
            return oldItem == newItem
        }
    }
}