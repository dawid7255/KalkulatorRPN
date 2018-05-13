package com.example.dawid.kalkulatorrpn

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var Stos: Deque<Double> = ArrayDeque<Double>()
    private var historia: Deque<Deque<Double>> = ArrayDeque<Deque<Double>>()
    private var buffor=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun PokazEkran(Cofaj: Boolean): String {
        var ekran = ""
        var i = Stos.count()

        Stos.forEach() {
            ekran += i.toString() + ":   "  + String.format("%." + 2 +"f",it) + "\n"
            i--
        }
        if(!Cofaj){
            if(historia.count() > 20)
            {
                historia.removeLast()
            }
            var tmp : Deque<Double> = ArrayDeque<Double>(Stos)
            historia.push(tmp)
        }
        return ekran
    }

    private fun OdswiezStos(Cofaj  : Boolean = false){
        TextViewBuffor.text = buffor
        ilosc.text = "Count: "+(Stos.count()).toString()
        EkranStos.text = PokazEkran(Cofaj)
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
            if(buffor.isNullOrEmpty()){
                buffor += "0."
            }
            else{
                buffor += "."
            }
        }
        TextViewBuffor.text = buffor
    }

    fun Enter(v: View){
        if(buffor.isNullOrEmpty())
        {
            if(Stos.count() == 0)
            {
                OdswiezStos()
                return
            }
            var ostatni = Stos.first()
            Stos.push(ostatni)
            OdswiezStos()
            return
        }

        if(buffor=="."){
            buffor=""
            OdswiezStos()
            return
        }

        var liczba = buffor.toDoubleOrNull()
        if(liczba == null) {
            throw NullPointerException()
        }
        Stos.push(liczba)
        buffor = ""
        OdswiezStos()
    }

    fun UsunZStosu(v: View){
        if((Stos.count())>0) {
            Stos.removeFirst()
            OdswiezStos()
        }
    }

    fun AC(v: View){
        if((Stos.count())>0) {
            Stos.clear()
            buffor = ""
            OdswiezStos()
        }
        if((Stos.count())==0) {
            buffor = ""
            OdswiezStos()
        }
    }

    fun Usun(v: View){
        if(buffor.length == 0)
            return
        buffor = buffor.dropLast(1)
        TextViewBuffor.text = buffor
    }

    fun Cofnij(v: View){
        if((historia.count())>1) {
            historia.removeFirst()
            var tmp: Deque<Double> = ArrayDeque<Double>(historia.first())
            Stos = tmp
            OdswiezStos(true)
        }
        else{
            BrakHistori()
        }
    }

    fun Zamien(v: View){
        if((Stos.count())>1) {
            var ostatni = Stos.first()
            Stos.removeFirst()

            var przedostatni = Stos.first()
            Stos.removeFirst()

            Stos.push(ostatni)
            Stos.push(przedostatni)
            OdswiezStos()
        }
        else{
            MaloLiczb()
        }
    }

    fun Znak(v: View){
        if (Stos.count()<1){
            MaloLiczb()
        }
        if((Stos.count())>0) {
            var liczba = Stos.first()
            Stos.removeFirst()
            liczba = liczba * -1
            Stos.push(liczba)
            OdswiezStos()
        }
    }

    fun Suma(v: View){
        if (Stos.count()<2){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        Stos.removeFirst()
        var przedostatni = Stos.first()
        Stos.removeFirst()
        Stos.push(przedostatni + ostatni)
        OdswiezStos()
    }

    fun Odejmowanie(v: View){
        if (Stos.count()<2){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        Stos.removeFirst()
        var przedostatni = Stos.first()
        Stos.removeFirst()
        Stos.push(przedostatni - ostatni)
        OdswiezStos()
    }

    fun Mnozenie(v: View){
        if (Stos.count()<2){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        Stos.removeFirst()
        var przedostatni = Stos.first()
        Stos.removeFirst()
        Stos.push(przedostatni * ostatni)
        OdswiezStos()
    }

    fun Dzielenie(v: View){
        if (Stos.count()<2){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        Stos.removeFirst()
        var przedostatni = Stos.first()
        Stos.removeFirst()
        Stos.push(przedostatni / ostatni)
        OdswiezStos()
    }

    fun Potega(v: View){
        if (Stos.count()<2){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        Stos.removeFirst()
        var przedostatni = Stos.first()
        Stos.removeFirst()
        Stos.push(przedostatni.pow(ostatni))
        OdswiezStos()
    }

    fun Pierwiastek(v: View){
        if (Stos.count()<1){
            MaloLiczb()
            return
        }
        var ostatni = Stos.first()
        if (ostatni<0){
            UjemnaLiczba()
            return
        }
        Stos.removeFirst()
        Stos.push(sqrt(ostatni))
        OdswiezStos()
    }

    fun UjemnaLiczba() {
        Toast.makeText(applicationContext, "The number is negative!",Toast.LENGTH_SHORT).show()
    }
    fun MaloLiczb() {
        Toast.makeText(applicationContext, "Not enough numbers on the stack!",Toast.LENGTH_SHORT).show()
    }
    fun BrakHistori() {
        Toast.makeText(applicationContext, "No history!",Toast.LENGTH_SHORT).show()
        /*val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Error!")
        dialogBuilder.setMessage("No history!")
        dialogBuilder.create().show()*/
    }

    fun kolorPomaranczowy(){
        calyekran.setBackgroundColor(Color.parseColor("#F7BE45"))
    }
    fun kolorSzary(){
        calyekran.setBackgroundColor(Color.parseColor("#ACA899"))
    }
    fun kolorOryginalny(){
        calyekran.setBackgroundColor(Color.parseColor("#222222"))
    }
}