package com.tencalculus.billcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tencalculus.billcalculator.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var phase = 1
        var cycle = 2
        super.onViewCreated(view, savedInstanceState)

        _binding?.calculateButton?.setOnClickListener {
            val units = _binding?.unitsInputLayout?.editText?.text.toString().trimEnd().toIntOrNull()

            if (_binding!!.radioButton1Phase.isChecked) phase = 1
            else if (_binding!!.radioButton3Phase.isChecked) phase = 3

            if (_binding!!.radioButton1Month.isChecked) cycle = 1
            else if (_binding!!.radioButton2Month.isChecked) cycle = 2

            if (units != null) {
                homeFragmentViewModel.calculateBill(units, phase, cycle)
            }
        }
        homeFragmentViewModel.finalAmount.observe(viewLifecycleOwner, Observer {
            val finalBillAmount = String.format("%.2f", it).toDouble()
            _binding?.amountTextView?.text = "₹ $finalBillAmount"

//            context?.let { it1 ->
//                MaterialAlertDialogBuilder(it1)
//                        .setTitle("Bill")
//                        .setMessage("₹ $finalBill")
//                        .setPositiveButton("OK"){
//                            dialog, which ->  dialog.dismiss()
//                        }
//                        .show()
//            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}