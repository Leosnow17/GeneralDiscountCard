package com.example.cardholderapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        val mApp = applicationContext as ContextApp
        val allCards = mApp.getAllCards()
        val addButton: Button = findViewById(R.id.addButton)
        val scrollView: ScrollView = findViewById(R.id.scrollView)
        val linear: LinearLayout = scrollView.findViewById(R.id.linear)

        redrawCards(linear)

        addButton.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, CreateCardActivity::class.java)
            startActivity(intent)
        })
    }

    fun redrawCards(linear: LinearLayout){

        val mApp = applicationContext as ContextApp
        val allCards = mApp.getAllCards()

        linear.removeAllViews()

        for(card in allCards)
        {
            if (card.parent != null) {
                (card.parent as ViewGroup).removeView(card)
            }

            card.findViewById<ImageView>(R.id.image_delete_card).setOnClickListener {
                allCards.remove(card)
                redrawCards(linear)
            }

            //card.setOnClickListener {
                //var a = card.findViewById<TextView>(R.id.textView_number).text.toString().toInt()
            //}

            linear.addView(card)
        }
    }
}