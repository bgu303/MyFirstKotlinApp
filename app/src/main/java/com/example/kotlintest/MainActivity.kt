package com.example.kotlintest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlintest.ui.theme.KotlintestTheme

private const val TAG = "Mainactivity"
private const val INITIAL_TIP_PERCENTAGE = 15
class MainActivity : ComponentActivity() {
    private lateinit var baseField : EditText
    private lateinit var tipField : SeekBar
    private lateinit var tipLabel : TextView
    private lateinit var tipAmountField : TextView
    private lateinit var totalField : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseField = findViewById(R.id.baseField)
        tipField = findViewById(R.id.tipField)
        tipLabel = findViewById(R.id.tipLabel)
        tipAmountField = findViewById(R.id.tipAmountField)
        totalField = findViewById(R.id.totalField)

        tipField.progress = INITIAL_TIP_PERCENTAGE
        tipLabel.text = "$INITIAL_TIP_PERCENTAGE"

        tipField.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tipLabel.text = "$p1%"
                computeTipAndTotal()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        baseField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "AfterTextChanged $p0")
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {

        if (baseField.text.isEmpty()) {
            tipAmountField.text = ""
            totalField.text = ""
            return
        }
        val baseAmount = baseField.text.toString().toDouble()
        val tipPercent = tipField.progress

        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount

        tipAmountField.text = tipAmount.toString()
        totalField.text = totalAmount.toString()
    }
}

