package com.example.cardholderapp

import android.app.Application
import android.view.View
import java.lang.reflect.Array

class ContextApp : Application() {

    private var allCards: ArrayList<View> = arrayListOf()

    fun getAllCards(): ArrayList<View> {
        return allCards
    }

    fun setAllCards(arr: ArrayList<View>) {
        allCards = arr
    }

    fun addAllCards(item: View){
        allCards.add(item)
    }
}