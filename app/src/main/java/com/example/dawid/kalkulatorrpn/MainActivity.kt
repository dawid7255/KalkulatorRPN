package com.example.dawid.kalkulatorrpn

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var NumerStosu: Deque<Double> = ArrayDeque<Double>()
    private var historyStack: Deque<Deque<Double>> = ArrayDeque<Deque<Double>>()
    private var kalk = Kalku()
    private var buffor=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun GetStack(isUndo: Boolean): String {
        var text = ""
        var i = NumerStosu.count()

        NumerStosu.forEach() {
            text += i.toString() + ":   "  + String.format("%." + 2 +"f",it) + "\n"
            i++
        }
        if(!isUndo){
            if(historyStack.count() > 10)
            {
                historyStack.removeLast()
            }
            var tmp : Deque<Double> = ArrayDeque<Double>(NumerStosu)
            historyStack.push(tmp)
        }
        return text
    }

    private fun OdswiezStos(isUndo  : Boolean = false){
        TextViewBuffor.text = buffor
        Stos.text = GetStack(isUndo)
    }

    fun Jeden(v: View){
        buffor += "1"
        TextViewBuffor.text = buffor
    }

    fun Dwa(v: View){
        buffor += "2"
        TextViewBuffor.text = buffor
    }

    fun Trzy(v: View){
        buffor += "3"
        TextViewBuffor.text = buffor
    }

    fun Cztery(v: View){
        buffor += "4"
        TextViewBuffor.text = buffor
    }

    fun Piec(v: View){
        buffor += "5"
        TextViewBuffor.text = buffor
    }

    fun Szesc(v: View){
        buffor += "6"
        TextViewBuffor.text = buffor
    }

    fun Siedem(v: View){
        buffor += "7"
        TextViewBuffor.text = buffor
    }

    fun Osiem(v: View){
        buffor += "8"
        TextViewBuffor.text = buffor
    }

    fun Dziewiec(v: View){
        buffor += "9"
        TextViewBuffor.text = buffor
    }

    fun Zero(v: View){
        buffor +="0"
        TextViewBuffor.text = buffor
    }

    fun Kropka(v: View){
        if(!buffor.contains("."))
        {
            buffor += "."
        }
        TextViewBuffor.text = buffor
    }

    fun Enter(v: View){
        if(buffor.isNullOrEmpty())
        {
            if(NumerStosu.count() == 0)
            {
                OdswiezStos()
                return
            }
            var ostatni = NumerStosu.first()
            NumerStosu.push(ostatni)
            OdswiezStos()
            return
        }

        var liczba = buffor.toDoubleOrNull()
        if(liczba == null) {
            throw NullPointerException()
        }
        NumerStosu.push(liczba)
        buffor = ""
        OdswiezStos()
    }

    fun UsunZStosu(v: View){
        NumerStosu.removeFirst()
        OdswiezStos()
    }

    fun AC(v: View){
        NumerStosu.clear()
        buffor=""
        OdswiezStos()
    }

    fun Usun(v: View){
        if(buffor.length == 0)
            return
        buffor = buffor.dropLast(1)
        TextViewBuffor.text = buffor
    }

    fun Cofnij(v: View){
        historyStack.removeFirst()
        var tmp : Deque<Double> = ArrayDeque<Double>(historyStack.first())
        NumerStosu = tmp
        OdswiezStos(true)
    }

    fun Zamien(v: View){
        var ostatni = NumerStosu.first()
        NumerStosu.removeFirst()

        var przedostatni = NumerStosu.first()
        NumerStosu.removeFirst()

        NumerStosu.push(ostatni)
        NumerStosu.push(przedostatni)
        OdswiezStos()
    }

    fun ChangeSign(v: View){
        //calculator.ChangeSignLastElemt()
        OdswiezStos()
    }

    fun Suma(v: View){
        //calculator.OperationOnStack(Operacje.suma)
        OdswiezStos()
    }

    fun Odejmowanie(v: View){
        //calculator.OperationOnStack(Operacje.odejmowanie)
        OdswiezStos()
    }

    fun Mnozenie(v: View){
        //calculator.OperationOnStack(Operacje.mnozenie)
        OdswiezStos()
    }

    fun Dzielenie(v: View){
        //calculator.OperationOnStack(Operacje.dzielenie)
        OdswiezStos()
    }

    fun Potega(v: View){
        //calculator.OperationOnStack(Operacje.potega)
        OdswiezStos()
    }

    fun Pierwiastek(v: View){
        //calculator.OperationOnStack(Operacje.pierwiastek)
        OdswiezStos()
    }
}


public enum class Operacje {
    suma,
    odejmowanie,
    mnozenie,
    dzielenie,
    potega,
    pierwiastek
}

public class Kalku : Serializable
{
    private var numbersInStack: Deque<Double> = ArrayDeque<Double>()
    private var historyStack: Deque<Deque<Double>> = ArrayDeque<Deque<Double>>()
    private var precission = 2




    fun OperationOnStack(typeOperation: Operacje){
        val resultSum : Double

        if(numbersInStack.count() < 2 || typeOperation == Operacje.pierwiastek)
        {
            return
        }
        var lastElement = numbersInStack.first()
        numbersInStack.removeFirst()

        var beforeLastElement = numbersInStack.first()
        numbersInStack.removeFirst()


        when(typeOperation){
            Operacje.suma-> {
                resultSum =  beforeLastElement + lastElement
            }
            Operacje.odejmowanie->{
                resultSum =  beforeLastElement - lastElement
            }
            Operacje.mnozenie->{
                resultSum = beforeLastElement * lastElement
            }
            Operacje.dzielenie ->{
                resultSum = beforeLastElement / lastElement
            }
            Operacje.potega ->{
                resultSum = beforeLastElement.pow(lastElement)
            }
            Operacje.pierwiastek ->{
                numbersInStack.push(beforeLastElement)
                resultSum = sqrt(lastElement)
            }
            else ->{
                resultSum = 0.0
            }
        }

        numbersInStack.push(resultSum)
    }

    fun ChangeSignLastElemt(){
        var numberStack = numbersInStack.last()
        numbersInStack.removeLast()
        numberStack = numberStack * -1
        numbersInStack.addLast(numberStack)
    }
}
