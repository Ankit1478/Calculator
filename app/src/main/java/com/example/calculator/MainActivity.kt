package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.StringBuilder
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var result:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.one.setOnClickListener {
            updateString(1)
        }
        binding.two.setOnClickListener {
            updateString(2)
        }
        binding.three.setOnClickListener {
            updateString(3)
        }
        binding.four.setOnClickListener {
            updateString(4)
        }
        binding.five.setOnClickListener {
            updateString(5)
        }
        binding.six.setOnClickListener {
            updateString(6)
        }
        binding.seven.setOnClickListener {
            updateString(7)
        }
        binding.eight.setOnClickListener {
            updateString(8)
        }
        binding.nine.setOnClickListener {
            updateString(9)
        }
        binding.zero.setOnClickListener {
            updateString(0)
        }
        binding.decimal.setOnClickListener {
            result=result+"."
        }
        binding.clearbtn.setOnClickListener {
            result = ""
            binding.datatext.text = result
        }
        binding.add.setOnClickListener {
            updateString("+")
        }
        binding.subtract.setOnClickListener {
            updateString("-")
        }
        binding.multiply.setOnClickListener {
            updateString("*")
        }
        binding.divide.setOnClickListener {
            updateString("/")
        }
        binding.modulo.setOnClickListener {
            updateString("%")
        }

        binding.backcut.setOnClickListener {
            removeLastCharacter()
        }
        binding.AC.setOnClickListener {
            clearResult()
        }
        binding.equal.setOnClickListener {
            calculateResult()
        }

    }
    private fun updateString(s:Int){
        result=result+s
        binding.datatext.text=result // yaha pa jo likhnge oo scrren pe digit show hoga

    }
    private fun updateString(s:String){
        result=result+s
        binding.datatext.text=result
        binding.resulttext.visibility = View.VISIBLE
    }
    private fun removeLastCharacter() {
        if (result.isNotEmpty()) {
            result = result.substring(0, result.length - 1)
            binding.datatext.text = result
        }
    }

    private fun clearResult() {
        result = ""
        binding.datatext.text = result
        binding.resulttext.text = ""
    }
    private fun calculateResult() {
        try {
            val expression = ExpressionBuilder(result).build()
            val calculatedResult = expression.evaluate()
            binding.resulttext.text = calculatedResult.toString()
        } catch (e: Exception) {
            binding.resulttext.text = "Error"
        }
    }

}

