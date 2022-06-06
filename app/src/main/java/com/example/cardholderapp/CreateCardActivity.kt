package com.example.cardholderapp

import android.content.Intent
import android.database.MatrixCursor
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array


class CreateCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        val listName: List<String> = listOf("Пятерочка","Магнит","Заповедник","Казанова 69","Живое слово","Живика")
        val arrayAdapterName: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, listName)
        arrayAdapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerName: Spinner = findViewById(R.id.spinner_name_shop)
        spinnerName.adapter = arrayAdapterName

        val nameShop = findViewById<Spinner>(R.id.spinner_name_shop)
        val numberShop = findViewById<EditText>(R.id.editText_card_number)
        val safeButton = findViewById<Button>(R.id.button_safe)
        safeButton.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.card_item_layout, null)

            val ll = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            ll.bottomMargin = 60
            ll.gravity = 16

            view.layoutParams = ll
            view.findViewById<TextView>(R.id.textView_name).text = nameShop.selectedItem.toString()
            view.findViewById<TextView>(R.id.textView_number).text = numberShop.text

            val mApp = applicationContext as ContextApp
            mApp.addAllCards(view)

            val intent = Intent(this, CardsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.image_cross).setOnClickListener {
            val intent = Intent(this, CardsActivity::class.java)
            startActivity(intent)
        }
    }
}