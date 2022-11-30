package com.kerencev.companies.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kerencev.companies.presentation.main.Navigation

abstract class BaseFragment<T : ViewBinding, V>(
    private val inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!
    protected var activity: Navigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = requireActivity() as? Navigation
    }

    protected fun renderData(state: AppState<V>) {
        when (state) {
            is AppState.Success -> {
                showSuccess(state.data)
            }
            is AppState.Loading -> {
                showLoading()
            }
            is AppState.Error -> {
                showError()
            }
        }
    }

    abstract fun showSuccess(data: List<V>)
    abstract fun showLoading()
    abstract fun showError()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        activity = null
        super.onDestroyView()
    }
}