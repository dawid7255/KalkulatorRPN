package com.example.dawid.kalkulatorrpn

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var NumerStosu: Deque<Double> = ArrayDeque<Double>()
    private var historyStack: Deque<Deque<Double>> = ArrayDeque<Deque<Double>>()
    private var buffor=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun PokazLiczby(isUndo: Boolean): String {
        var text = ""
        var i = NumerStosu.count()

        NumerStosu.forEach() {
            text += i.toString() + ":   "  + String.format("%." + 2 +"f",it) + "\n"
            i--
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
        Stos.text = PokazLiczby(isUndo)
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

    fun Znak(v: View){
        var liczba = NumerStosu.first()
        NumerStosu.removeFirst()
        liczba = liczba * -1
        NumerStosu.addFirst(liczba)
        OdswiezStos()
    }

    fun Suma(v: View){
        DzialaniaNaStosie(Operacja.suma)
        OdswiezStos()
    }

    fun Odejmowanie(v: View){
        DzialaniaNaStosie(Operacja.odejmowanie)
        OdswiezStos()
    }

    fun Mnozenie(v: View){
        DzialaniaNaStosie(Operacja.mnozenie)
        OdswiezStos()
    }

    fun Dzielenie(v: View){
        DzialaniaNaStosie(Operacja.dzielenie)
        OdswiezStos()
    }

    fun Potega(v: View){
        DzialaniaNaStosie(Operacja.potega)
        OdswiezStos()
    }

    fun Pierwiastek(v: View){
        DzialaniaNaStosie(Operacja.pierwiastek)
        OdswiezStos()
    }

    fun DzialaniaNaStosie(typeOperation: Operacja){
        val Wynik : Double

        if(NumerStosu.count() < 2 || typeOperation == Operacja.pierwiastek)
        {
            return
        }
        var lastElement = NumerStosu.first()
        NumerStosu.removeFirst()

        var beforeLastElement = NumerStosu.first()
        NumerStosu.removeFirst()


        when(typeOperation){
            Operacja.suma-> {
                Wynik =  beforeLastElement + lastElement
            }
            Operacja.odejmowanie->{
                Wynik =  beforeLastElement - lastElement
            }
            Operacja.mnozenie->{
                Wynik = beforeLastElement * lastElement
            }
            Operacja.dzielenie ->{
                Wynik = beforeLastElement / lastElement
            }
            Operacja.potega ->{
                Wynik = beforeLastElement.pow(lastElement)
            }
            Operacja.pierwiastek ->{
                NumerStosu.push(beforeLastElement)
                Wynik = sqrt(lastElement)
            }
            else ->{
                Wynik = 0.0
            }
        }

        NumerStosu.push(Wynik)
    }

}

public enum class Operacja {
    suma,
    odejmowanie,
    mnozenie,
    dzielenie,
    potega,
    pierwiastek
}
