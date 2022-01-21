package com.example.tiptimeandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptimeandroid.R.*
import com.example.tiptimeandroid.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    // lateinit initializes something new, a promise you will initialize code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if(cost == null ){
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            id.option_eightTeen_percent -> 0.18
            id.option_twenty_percent -> 0.20
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }


        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(string.tip_amount, formattedTip)

    }
}



