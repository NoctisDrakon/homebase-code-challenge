package com.homebase.codechallenge.createschedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.homebase.codechallenge.R
import com.homebase.codechallenge.databinding.FragmentAddScheduleBinding
import com.homebase.codechallenge.schedules.viewmodel.SchedulesViewModel
import com.homebase.core.service.schedules.model.Shift
import java.util.*

class AddScheduleFragment : Fragment() {

    private lateinit var binding: FragmentAddScheduleBinding
    private lateinit var vm: SchedulesViewModel

    //Shift variables

    //Start
    var startDay = ""
    var startMonth = ""
    var startYear = ""
    var startHour = ""
    var startMinute = ""

    //End
    var endDay = ""
    var endMonth = ""
    var endYear = ""
    var endHour = ""
    var endMinute = ""

    //Employee
    var employee = ""

    //Role
    var role = ""

    //Employee
    var color = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(requireActivity()).get(SchedulesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_schedule, parent, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiControls()
    }

    private fun setupUiControls() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.startDateCard.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                startDay = String.format("%02d",dayOfMonth)
                startMonth = String.format("%02d", (month + 1))
                startYear = year.toString()


                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)
                val timePicker = TimePickerDialog(requireContext(), { _, hourOfDay, minuteOfHour ->
                    startHour = String.format("%02d", hourOfDay)
                    startMinute = String.format("%02d", minuteOfHour)

                    binding.startDate.text = "$startMonth/$startDay/$startYear $startHour:$startMinute"
                }, hour, minute, false)
                timePicker.show()

            }, year, month, day)
            datePicker.show()
        }

        binding.endDateCard.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                endDay = String.format("%02d", dayOfMonth)
                endMonth = String.format("%02d", (month + 1))
                endYear = year.toString()

                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)
                val timePicker = TimePickerDialog(requireContext(), { _, hourOfDay, minuteOfHour ->
                    endHour = String.format("%02d", hourOfDay)
                    endMinute = String.format("%02d", minuteOfHour)

                    binding.endDate.text = "$endMonth/$endDay/$endYear $endHour:$endMinute"
                }, hour, minute, false)
                timePicker.show()
            }, year, month, day)
            datePicker.show()
        }

        binding.employeeCard.setOnClickListener {
            val employees = resources.getStringArray(R.array.employees)
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.form_employee)
                setSingleChoiceItems(employees, -1) { dialog, which ->
                    employee = employees[which]
                    binding.employee.text = employee
                    dialog.dismiss()
                }
                create().show()
            }
        }

        binding.roleCard.setOnClickListener {
            val roles = resources.getStringArray(R.array.roles)
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.form_role)
                setSingleChoiceItems(roles, -1) { dialog, which ->
                    role = roles[which]
                    binding.role.text = role
                    dialog.dismiss()
                }
                create().show()
            }
        }

        binding.colorCard.setOnClickListener {
            val colors = resources.getStringArray(R.array.colors)
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.form_color)
                setSingleChoiceItems(colors, -1) { dialog, which ->
                    color = colors[which]
                    binding.color.text = color
                    dialog.dismiss()
                }
                create().show()
            }
        }

        binding.saveCard.setOnClickListener{
            if(shiftIsValid())
                saveShiftAndClose()
            else
                Toast.makeText(requireContext(), R.string.error_invalid_shift, Toast.LENGTH_SHORT).show()
        }
    }

    private fun shiftIsValid(): Boolean {
        return (startDay.isNotEmpty()
                && endDay.isNotBlank()
                && employee.isNotBlank()
                && role.isNotBlank()
                && color.isNotBlank())
    }

    private fun saveShiftAndClose() {
        vm.addEntry(
            Shift(
                role = role,
                name = employee,
                startDate = "$startYear-$startMonth-$startDay $startHour:$startMinute:00 -08:00",
                endDate = "$endYear-$endMonth-$endDay $endHour:${endMinute}:00 -08:00",
                color = color
            )
        )
        Toast.makeText(requireContext(), R.string.add_shift_success, Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

}