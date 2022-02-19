package com.example.calculadora_eah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

private val TAG = "MainActivity"
private val TEXT = "TEXT_OUTPUT"
private val TEXT2= "TEXT_INPUT"

class MainActivity : AppCompatActivity() {

    private var userInput: TextView?= null
    private var userResult: TextView?= null
    private var oper: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userResult= findViewById<TextView>(R.id.mainText)
        userInput=findViewById<TextView>(R.id.textOperation)

        userResult?.text=""
        userInput?.text=""

        val b0: Button=findViewById<Button>(R.id.btn0)
        val b1: Button=findViewById<Button>(R.id.btn1)
        val b2: Button=findViewById<Button>(R.id.btn2)
        val b3: Button=findViewById<Button>(R.id.btn3)
        val b4: Button=findViewById<Button>(R.id.btn4)
        val b5: Button=findViewById<Button>(R.id.btn5)
        val b6: Button=findViewById<Button>(R.id.btn6)
        val b7: Button=findViewById<Button>(R.id.btn7)
        val b8: Button=findViewById<Button>(R.id.btn8)
        val b9: Button=findViewById<Button>(R.id.btn9)

        val bMas: Button=findViewById<Button>(R.id.btnSum)
        val bMenos: Button=findViewById<Button>(R.id.btnRestar)
        val bEntre: Button=findViewById<Button>(R.id.btnDivide)
        val bPor: Button=findViewById<Button>(R.id.btnMult)

        val bRes: Button=findViewById<Button>(R.id.btnResult)
        val bClear: Button=findViewById<Button>(R.id.btnClear)
        val bDec: Button=findViewById<Button>(R.id.btnDecimal)

        b0?.setOnClickListener{Numeritos("0")}
        b1?.setOnClickListener{Numeritos("1")}
        b2?.setOnClickListener{Numeritos("2")}
        b3?.setOnClickListener{Numeritos("3")}
        b4?.setOnClickListener{Numeritos("4")}
        b5?.setOnClickListener{Numeritos("5")}
        b6?.setOnClickListener{Numeritos("6")}
        b7?.setOnClickListener{Numeritos("7")}
        b8?.setOnClickListener{Numeritos("8")}
        b9?.setOnClickListener{Numeritos("9")}

        bMas?.setOnClickListener{Operandos("+")}
        bMenos?.setOnClickListener{Operandos("-")}
        bPor?.setOnClickListener{Operandos("*")}
        bEntre?.setOnClickListener{Operandos("/")}
        bDec?.setOnClickListener{Operandos(".")}

        bRes?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?){
                userInput?.text=""
                userInput?.append(userResult?.text)
                val separado:List<String> = Operacion(userInput?.text.toString())
                userResult?.append(separado[0])
                userResult?.text=""
                if (separado.size<2) {
                    userResult?.append(userInput?.text)
                }else if(oper==1){
                    userResult?.append(((separado[0].toDouble())+(separado[1].toDouble())).toString())
                }else if(oper==2){
                    userResult?.append(((separado[0].toDouble())-(separado[1].toDouble())).toString())
                }else if(oper==3){
                    userResult?.append(((separado[0].toDouble())*(separado[1].toDouble())).toString())
                }else if(oper==4){
                    if (separado[1]=="0"){
                        userResult?.append("ERROR!")
                    }else {
                        userResult?.append(((separado[0].toDouble())/(separado[1].toDouble())).toString())
                    }
                }
            }
        })

        bClear?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?){
                userInput?.text=""
                userResult?.text=""
            }
        })

    }

    private fun Operacion(todo: String): List<String> {
        val arreglo: List<String> = todo.split("+", "*", "/", "-")
        return arreglo
    }

    private fun Numeritos(coso: String){
        if (userResult?.text=="ERROR!"){
            userResult?.text=""
        }
        userResult?.append(coso)
    }

    private fun Operandos(coso: String){
        userResult?.append(coso)
        if (coso=="+"){
            oper=1
        }else if(coso=="-"){
            oper=2
        }else if(coso=="*"){
            oper=3
        }else if(coso=="/"){
            oper=4
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        userResult?.text=savedInstanceState.getString(TEXT)
        userInput?.text=savedInstanceState.getString(TEXT2)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT, userResult?.text.toString())
        outState.putString(TEXT2, userInput?.text.toString())
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

