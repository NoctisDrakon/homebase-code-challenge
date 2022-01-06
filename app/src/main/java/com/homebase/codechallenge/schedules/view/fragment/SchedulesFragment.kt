package com.homebase.codechallenge.schedules.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.homebase.codechallenge.R
import com.homebase.codechallenge.databinding.FragmentSchedulesBinding
import com.homebase.codechallenge.schedules.view.adapter.ShiftItem
import com.homebase.codechallenge.schedules.view.viewstate.SchedulesFragmentViewState
import com.homebase.codechallenge.schedules.viewmodel.SchedulesViewModel
import com.homebase.core.base.State
import com.homebase.core.service.schedules.model.Shifts
import timber.log.Timber

class SchedulesFragment : Fragment() {

    lateinit var binding: FragmentSchedulesBinding
    lateinit var vm: SchedulesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this).get(SchedulesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedules, parent, false)
        binding.viewState = SchedulesFragmentViewState()
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        vm.getSchedules()
    }

    private fun observeViewModel() {
        vm.schedulesData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.Loading -> {
                    Timber.d("Gabino Loading...")
                }
                is State.Success -> {
                    Timber.d("Gabino SUCCESS!!!!!!!!!")
                    val shiftsWrapper: Shifts = response.getData()
                    val shifts = shiftsWrapper.shifts
                    shifts.forEach {
                        Timber.d("Gabino -> ${it.name}")
                        binding.viewState?.adapter?.addAdapter(ShiftItem(it))
                    }
                }
                is State.Error -> Timber.d("Gabino FUCK ERROR!!!! ${response.error}")
            }
        })
    }

}