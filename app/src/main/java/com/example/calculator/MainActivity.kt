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

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var lastnumerical =false
    var staterror =false
    var lastdot=false

    private lateinit var expression:Expression
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun onOperatorclick(view: View) {
        if(!staterror && lastnumerical){
            binding.datatext.append((view as Button).text)
            lastdot=false
            lastnumerical=false
            onequal()
        }
    }


    fun onDigitClick(view: View) {

        if(staterror){
            binding.datatext.text=(view as Button).text
            staterror=false

        }
        else{
            binding.datatext.append((view as Button).text)
        }
        lastnumerical=true
        onequal()
    }


    fun Onbackclick(view: View) {
        binding.datatext.text=binding.datatext.text.toString().dropLast(1)
        try {
            val lastcharacter=binding.datatext.text.toString().last()

            if(lastcharacter.isDigit()){
                onequal()
            }
        }catch (e:Exception){
            binding.resulttext.text=""
            binding.resulttext.visibility=View.GONE
            Log.e("last char error",e.toString())
        }
    }


    fun onClearClick(view: View) {
        binding.datatext.text=""
        lastnumerical=false
    }


    fun Onallclearclick(view: View) {
        binding.datatext.text=""
        binding.resulttext.text=""
        staterror=false
        lastdot=false
        lastnumerical=false
        binding.resulttext.visibility=View.GONE
    }


    fun OnEqaulclick(view: View) {
        onequal()
        binding.resulttext.text=binding.resulttext.text.toString().drop(1)
    }

    fun onequal(){

        if(lastnumerical && ! staterror){
            val text =binding.datatext.text.toString()

            expression  = ExpressionBuilder(text).build()

            try {
                val result=expression.evaluate()
                binding.resulttext.visibility=View.VISIBLE

                binding.resulttext.text= "="+result.toString()
            }
            catch (ex:ArithmeticException){
                Log.e("evaluate error",ex.toString())
                binding.resulttext.text="Error"
                staterror=true
                lastnumerical=false
            }
        }
    }
}