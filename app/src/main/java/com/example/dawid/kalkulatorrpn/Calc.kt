/*
package com.example.dawid.kalkulatorrpn

import android.os.Environment
import java.io.Serializable
import java.util.*
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

public  class Calc : Serializable
{
    private var numbersInStack: Deque<Double> = ArrayDeque<Double>()
    private var historyStack: Deque<Deque<Double>> = ArrayDeque<Deque<Double>>()
    private var precission = 2

    fun SetPrecission(precission: String){
        this.precission = precission.toInt();
    }

    fun GetCountStack() : String {
        return numbersInStack.count().toString()
    }

    fun AddToStack(element: String){
        if(element.isNullOrEmpty())
        {
            if(numbersInStack.count() == 0)
            {
                return
            }
            var lastElement = numbersInStack.first()
            numbersInStack.push(lastElement)
            return
        }

        var numberToAddStack = element.toDoubleOrNull()
        if(numberToAddStack == null)
        {
            throw NullPointerException()
        }
        numbersInStack.push(numberToAddStack)
    }

    fun DropWithStack(){
        numbersInStack.removeFirst()
    }

    fun ClearStack(){
        numbersInStack.clear()
    }

    fun SwapStack(){
        var lastElement = numbersInStack.first()
        numbersInStack.removeFirst()

        var beforeLastElement = numbersInStack.first()
        numbersInStack.removeFirst()

        numbersInStack.push(lastElement)
        numbersInStack.push(beforeLastElement)
    }

    fun GetStack(isUndo: Boolean): String {
        var text = ""
        var i = numbersInStack.count()

        numbersInStack.forEach() {
            text += i.toString() + "    "  + String.format("%." + precission +"f",it) + "\n"
            i--
        }
        if(!isUndo){
            addHistory()
        }
        return  text
    }

    fun OperationOnStack(typeOperation: Operacje){
        val resultSum : Double

        if(numbersInStack.count() < 2 || typeOperation == Operacje.Sqrt)
        {
            return
        }
        var lastElement = numbersInStack.first()
        numbersInStack.removeFirst()

        var beforeLastElement = numbersInStack.first()
        numbersInStack.removeFirst()


        when(typeOperation){
            Operation.Sum -> {
                resultSum =  beforeLastElement + lastElement
            }
            Operation.Minus ->{
                resultSum =  beforeLastElement - lastElement
            }
            Operation.Multiple ->{
                resultSum = beforeLastElement * lastElement
            }
            Operation.Devided ->{
                resultSum = beforeLastElement / lastElement
            }
            Operation.Power ->{
                resultSum = beforeLastElement.pow(lastElement)
            }
            Operation.Sqrt ->{
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


    fun UndoOperation(){
        historyStack.removeFirst()
        var tmp : Deque<Double> = ArrayDeque<Double>(historyStack.first())
        numbersInStack = tmp
    }


    private fun addHistory(){
        if(historyStack.count() > 10)
        {
            historyStack.removeLast()
        }
        var tmp : Deque<Double> = ArrayDeque<Double>(numbersInStack)
        historyStack.push(tmp)
    }




}*/
