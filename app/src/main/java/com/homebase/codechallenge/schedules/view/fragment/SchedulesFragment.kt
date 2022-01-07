package com.homebase.codechallenge.schedules.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.homebase.codechallenge.R
import com.homebase.codechallenge.databinding.FragmentSchedulesBinding
import com.homebase.codechallenge.extension.clear
import com.homebase.codechallenge.schedules.view.adapter.HeaderAdapter
import com.homebase.codechallenge.schedules.view.adapter.LoadingAdapter
import com.homebase.codechallenge.schedules.view.adapter.ShiftItem
import com.homebase.codechallenge.schedules.view.listener.AddScheduleListener
import com.homebase.codechallenge.schedules.view.viewstate.SchedulesFragmentViewState
import com.homebase.codechallenge.schedules.viewmodel.SchedulesViewModel
import com.homebase.core.base.State
import com.homebase.core.service.schedules.model.Shift

class SchedulesFragment : Fragment() {

    lateinit var binding: FragmentSchedulesBinding
    lateinit var vm: SchedulesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(requireActivity()).get(SchedulesViewModel::class.java)
        vm.getSchedules()
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
    }

    private fun observeViewModel() {
        vm.schedulesData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.Loading -> {
                    binding.viewState?.adapter?.clear()
                    binding.viewState?.adapter?.addAdapter(LoadingAdapter())
                }
                is State.Success -> {
                    //Clear Previous Data From Adapter
                    binding.viewState?.adapter?.clear()

                    //Get Data from response
                    val shifts = response.getData<List<Shift>>()

                    //Add Header
                    binding.viewState?.adapter?.addAdapter(HeaderAdapter(object : AddScheduleListener {
                        override fun onAddScheduleClicked() {
                            findNavController().navigate(R.id.action_schedulesFragment_to_addScheduleFragment)
                        }
                    }))

                    //Render data on screen
                    shifts.forEach { binding.viewState?.adapter?.addAdapter(ShiftItem(it)) }
                }
                is State.Error -> Toast.makeText(context, getString(R.string.error_generic), Toast.LENGTH_SHORT).show()
            }
        })
    }
}