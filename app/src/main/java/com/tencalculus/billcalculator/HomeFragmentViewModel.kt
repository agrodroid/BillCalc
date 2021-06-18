package com.tencalculus.billcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel: ViewModel() {

    private val _finalAmount = MutableLiveData<Double>()
    val finalAmount: LiveData<Double>
    get() = _finalAmount

    private var tempAmount = 0.0
    private var amountBeforeSubsidy = 0.0

    private val fixedCharge11 = 35
    private val fixedCharge12 = 45
    private val fixedCharge13 = 55
    private val fixedCharge14 = 70
    private val fixedCharge15 = 80

    private val fixedCharge16 = 100
    private val fixedCharge17 = 110
    private val fixedCharge18 = 120
    private val fixedCharge19 = 130
    private val fixedCharge110 = 150

    private val fixedCharge21 = 90
    private val fixedCharge22 = 100
    private val fixedCharge23 = 110
    private val fixedCharge24 = 120
    private val fixedCharge25 = 130
    private val fixedCharge26 = 150

    private val phaseOne = 6
    private val phaseThree = 15


    fun calculateBill(units: Int, phase: Int, cycle: Int){
        when (cycle) {
            1 -> {
                amountBeforeSubsidy = billAmountCalc(units, phase) + meterChargeCalc(phase)

                //for phase1 only
                _finalAmount.value = subsidyCalc(amountBeforeSubsidy, units, cycle)
            }
            2 -> {
                amountBeforeSubsidy = (billAmountCalc(units/2, phase) + meterChargeCalc(phase))*2

                //for phase1 only
                _finalAmount.value = subsidyCalc(amountBeforeSubsidy, units, cycle)
            }
        }
    }

    private fun billAmountCalc(units: Int, phase: Int): Double {
        when {
            units <= 250 && phase == 1 -> {
                when (units) {
                    in (0..50) -> tempAmount = ((3.15) * units) * (1.1) + fixedCharge11
                    in (51..100) -> tempAmount = (((3.7) * (units - 50)) + 157.5) * (1.1) + fixedCharge12
                    in (101..150) -> tempAmount = (((4.8) * (units - 100)) + 342.5) * (1.1) + fixedCharge13
                    in (151..200) -> tempAmount = (((6.4) * (units - 150)) + 582.5) * (1.1) + fixedCharge14
                    in (201..250) -> tempAmount = (((7.6) * (units - 200)) + 902.5) * (1.1) + fixedCharge15
                }
            }
            units in 251..500 && phase == 1 -> {
                when (units) {
                    in (251..300) -> tempAmount = ((5.8) * units) * (1.1) + fixedCharge16
                    in (301..350) -> tempAmount = ((6.6) * units) * (1.1) + fixedCharge17
                    in (351..400) -> tempAmount = ((6.9) * units) * (1.1) + fixedCharge18
                    in (401..500) -> tempAmount = ((7.1) * units) * (1.1) + fixedCharge19
                }
            }
            units <= 250 && phase == 3 -> {
                when (units) {
                    in (0..50) -> tempAmount = ((3.15) * units) * (1.1) + fixedCharge21
                    in (51..100) -> tempAmount = (((3.7) * (units - 50)) + 157.5) * (1.1) + fixedCharge21
                    in (101..150) -> tempAmount = (((4.8) * (units - 100)) + 342.5) * (1.1) + fixedCharge22
                    in (151..200) -> tempAmount = (((6.4) * (units - 150)) + 582.5) * (1.1) + fixedCharge22
                    in (201..250) -> tempAmount = (((7.6) * (units - 200)) + 902.5) * (1.1) + fixedCharge22
                }
            }
            units in 251..500 && phase == 3 -> {
                when (units) {
                    in (251..300) -> tempAmount = ((5.8) * units) * (1.1) + fixedCharge23
                    in (301..350) -> tempAmount = ((6.6) * units) * (1.1) + fixedCharge23
                    in (351..400) -> tempAmount = ((6.9) * units) * (1.1) + fixedCharge24
                    in (401..500) -> tempAmount = ((7.1) * units) * (1.1) + fixedCharge25
                }
            }
            units>500 && phase ==1 -> {
                tempAmount = ((7.9) * units) * (1.1) + fixedCharge110
            }
            units>500 && phase == 3 -> {
                tempAmount = ((7.9) * units) * (1.1) + fixedCharge26
            }
        }
        return tempAmount
//        when {
//            units>500 && phase ==1 -> {
//                amount1 = ((7.9) * units) * (1.1) + fixedCharge110
//            }

//        when {
//            units <= 250 && phase == 3 -> {
//                when (units) {
//                    in (0..50) -> amount1 = ((3.15) * units) * (1.1) + fixedCharge21
//                    in (51..100) -> amount1 = (((3.7) * (units - 50)) + 157.5) * (1.1) + fixedCharge21
//                    in (101..150) -> amount1 = (((4.8) * (units - 100)) + 342.5) * (1.1) + fixedCharge22
//                    in (151..200) -> amount1 = (((6.4) * (units - 150)) + 582.5) * (1.1) + fixedCharge22
//                    in (201..250) -> amount1 = (((7.6) * (units - 200)) + 902.5) * (1.1) + fixedCharge22
//                }
//            }
//            units > 250 && phase == 3 -> {
//                when (units) {
//                    in (251..300) -> amount1 = ((5.8) * units) * (1.1) + fixedCharge23
//                    in (301..350) -> amount1 = ((6.6) * units) * (1.1) + fixedCharge23
//                    in (351..400) -> amount1 = ((6.9) * units) * (1.1) + fixedCharge24
//                    in (401..500) -> amount1 = ((7.1) * units) * (1.1) + fixedCharge25
//                }
//            }
//        }
        //}

//        when {
//            units <= 250 && phase == 3 -> {
//                when (units) {
//                    in (0..50) -> amount1 = ((3.15) * units) * (1.1) + fixedCharge21
//                    in (51..100) -> amount1 = (((3.7) * (units - 50)) + 157.5) * (1.1) + fixedCharge21
//                    in (101..150) -> amount1 = (((4.8) * (units - 100)) + 342.5) * (1.1) + fixedCharge22
//                    in (151..200) -> amount1 = (((6.4) * (units - 150)) + 582.5) * (1.1) + fixedCharge22
//                    in (201..250) -> amount1 = (((7.6) * (units - 200)) + 902.5) * (1.1) + fixedCharge22
//                }
//            }
//            units > 250 && phase == 3 -> {
//                when (units) {
//                    in (251..300) -> amount1 = ((5.8) * units) * (1.1) + fixedCharge23
//                    in (301..350) -> amount1 = ((6.6) * units) * (1.1) + fixedCharge23
//                    in (351..400) -> amount1 = ((6.9) * units) * (1.1) + fixedCharge24
//                    in (401..500) -> amount1 = ((7.1) * units) * (1.1) + fixedCharge25
//                }
//            }
//        }
//        when {
//            units>500 && phase == 3 -> {
//                amount1 = ((7.9) * units) * (1.1) + fixedCharge26
//            }
//        }

    }
    private fun meterChargeCalc(phase: Int): Double {
        var meterCharge = 0.0
        when (phase) {
            1 -> {
                val cess = (0.01) * phaseOne
                val stateGst = (0.09) * phaseOne
                val centralGst = (0.09) * phaseOne
                meterCharge = (cess + stateGst + centralGst)
            }
            3 -> {
                val cess = (0.01) * phaseThree
                val stateGst = (0.09) * phaseThree
                val centralGst = (0.09) * phaseThree
                meterCharge = (cess + stateGst + centralGst)
            }
        }
        return meterCharge
//        when (phase) {
//            3 -> {
//                val cess = (0.01) * phaseThree
//                val stateGst = (0.09) * phaseThree
//                val centralGst = (0.09) * phaseThree
//                meterCharge = (cess + stateGst + centralGst)
//            }
//        }

    }
    private fun subsidyCalc(billBeforeSubsidy: Double, units: Int, cycle: Int): Double{
        var billAfterSubsidy = 0.0
        when (cycle) {
            1 -> {
                if (units in 20..26){
                    billAfterSubsidy = billBeforeSubsidy - ((1.5) * (units - 20)) - 20 - 20
                }else if (units in 40..120){
                    billAfterSubsidy = billBeforeSubsidy - ((0.5) * (units - 40)) - 20 - 14
                }else{
                    billAfterSubsidy = billBeforeSubsidy
                }
            }
            2 -> {
                if (units in 40..52){
                    billAfterSubsidy = billBeforeSubsidy - ((1.5) * (units - 40)) - 40 - 40
                }else if (units in 80..240){
                    billAfterSubsidy = billBeforeSubsidy - ((0.5) * (units - 80)) - 40 - 28
                }else{
                    billAfterSubsidy = billBeforeSubsidy
                }
            }
        }
        return billAfterSubsidy
//        when (cycle) {
//            2 -> {
//                if (units in 40..52){
//                    finalBill = meterBill - ((1.5) * (units - 40)) - 40 - 40
//                }else if (units in 80..240){
//                    finalBill = meterBill - ((0.5) * (units - 80)) - 40 - 28
//                }else{
//                    finalBill = meterBill
//                }
//            }
//        }

    }
}
