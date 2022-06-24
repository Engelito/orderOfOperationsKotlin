package com.example.orderofoperationskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var orderInput : EditText
    private lateinit var orderOutput : TextView
    // Lambda functions to be called in the map
    // Kotlin does not have a predefined exponent, so pow is needed
    val add = { a: Float, b: Float -> a + b }
    val sub = { a: Float, b: Float -> a - b }
    val mult = {a: Float, b: Float -> a * b}
    val div = {a: Float, b: Float -> a / b}
    val expo ={a: Float, b: Float -> a.pow(b)}

    // Defining the map/dictionary wiht the lambda functions
    val ops = mapOf("+" to add, "-" to sub, "*" to mult, "/" to div, "^" to expo)
    // Input text! change this to test code!
    val text:String = orderInput.toString()
    //testing
    //take two
    //take three
    //take 4


    val equation = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val convertButton2 = findViewById<Button>(R.id.convertButton)

        orderInput = findViewById<EditText>(R.id.orderInput)
        orderOutput = findViewById<TextView>(R.id.orderOutput)

        convertButton2.setOnClickListener { runEquation(equation)}
        for(item in text.split(" ")){
            equation.add(item)
        }



    }



    // Takes the input of an operator, calls the lambda function based on the key input into the function
    // Does the math based on the 1 number above and below the operator
    // returns equation
    private fun doMath(oper: String, equation: MutableList<String>): MutableList<String>{


        //Split the input text by space, puts into a list called equation


        //print(ops[oper]?.let { it(1f, 2f) })
        val myIndex = equation.indexOf(oper)
        val result = ops[oper]?.let { it(equation[myIndex - 1].toFloat(), equation[myIndex + 1].toFloat()) }

        equation.removeAt(myIndex - 1)
        equation.removeAt(myIndex -1)
        equation[myIndex -1] = result.toString()

        return equation

    }
    private fun runEquation(equation: MutableList<String>): MutableList<String>
    {

        val input_text = orderInput.text.toString()
        orderOutput.text = "${(input_text)}"

        val operators = listOf<String>("^","*","/","+","-")
        var newEquation = mutableListOf<String>()
        for(oper in operators)
        {
            while(oper in equation)
            {
                newEquation = doMath(oper, equation)
            }
        }
        //Test, remove if needed
        print(newEquation)
        return newEquation
    }








}