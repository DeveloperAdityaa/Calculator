package tech.devndroid.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tech.devndroid.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    var lastDigit = false
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);

        btnClick()
        setContentView(binding.root)
    }

    private fun btnClick() {
        //digit click
        binding.btnNine.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnEight.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnSeven.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnSix.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnFive.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnFour.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnThree.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnTwo.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnOne.setOnClickListener {
            onDigitClick(it);
        }
        binding.btnZero.setOnClickListener {
            onDigitClick(it);
        }

        //operator click
        binding.btnDivide.setOnClickListener {
            onOperatorClick(it);
        }

        binding.btnMultiply.setOnClickListener {
            onOperatorClick(it);
        }

        binding.btnPlus.setOnClickListener {
            onOperatorClick(it);
        }

        binding.btnMinus.setOnClickListener {
            onOperatorClick(it);
        }
        //clear click
        binding.btnClr.setOnClickListener {
            binding.txtView.text = ""
            lastDigit = false
            lastDot = false
        }

        //dot click
        binding.btnDot.setOnClickListener {
            if (lastDigit && !lastDot) {
                lastDot = true
                lastDigit = false
                binding.txtView.append(".")
            }
        }

        //equal click
        binding.btnEqual.setOnClickListener {
            onEqualClick(it)
        }
    }


    private fun onOperatorClick(it: View?) {
        if (lastDigit && !isOperatorAdded(binding.txtView.text.toString())) {
            lastDigit = false
            lastDot = false
            binding.txtView.append((it as Button).text)
        }
    }

    private fun onDigitClick(it: View?) {
        lastDigit = true
        binding.txtView.append((it as Button).text)
    }


    private fun onEqualClick(it: View?) {
        if (lastDigit) {
            var input = binding.txtView.text.toString()
            var prefix = false
            try {
                if (input.startsWith("-")) {
                    input = input.substring(1)
                    prefix = true
                }
                var splitAt = ""
                when {
                    input.contains("-") -> splitAt = "-"
                    input.contains("+") -> splitAt = "+"
                    input.contains("*") -> splitAt = "*"
                    input.contains("/") -> splitAt = "/"
                }

                val numbers = input.split(splitAt)
                var firstNum = numbers[0] //first number
                var seconNum = numbers[1] //second number

                if (prefix) firstNum = "-" + firstNum

                var result = 0.0
                when {
                    input.contains("-") ->  result = firstNum.toDouble() - seconNum.toDouble()
                    input.contains("+") ->  result = firstNum.toDouble() + seconNum.toDouble()
                    input.contains("*") ->  result = firstNum.toDouble() * seconNum.toDouble()
                    input.contains("/") ->  result = firstNum.toDouble() / seconNum.toDouble()
                }

                binding.txtView.text = result.toString()

//                if(input.contains("-")){
//                    val numbers = input.split("-")
//                    var firstNum = numbers[0] //first number
//                    var seconNum = numbers[1] //second number
//
//                    if(prefix) firstNum = "-"+firstNum
//
//
//                    val result = firstNum.toInt() - seconNum.toInt()
//                    binding.txtView.text = result.toString()
//                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }
    }

    private fun isOperatorAdded(it: String): Boolean {
        return if (it.startsWith("-")) {
            false
        } else {
            (it.contains("/") || it.contains("*") ||
                    it.contains("+") || it.contains("-"))
        }
        return false
    }
}