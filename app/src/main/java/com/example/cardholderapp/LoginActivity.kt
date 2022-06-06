package com.example.cardholderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.generaldiscountcard.db.DbManager

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dbManager = DbManager(this)
        val edLogin = findViewById<EditText>(R.id.editTextNameText)
        val edPass = findViewById<EditText>(R.id.editTextTextPasswordAddress)

        onMigrateToRegistration()

        val loginButton: Button = findViewById(R.id.buttonLogin)
        loginButton.setOnClickListener {
            dbManager.openDb()
            var isNotLog = true
            val userList = dbManager.readDataTableUser()
            for(user in userList) {
                if (edLogin.text.toString() == user.first && edPass.text.toString() == user.second) {
                    isNotLog = false
                    val intent = Intent(this, CardsActivity::class.java)
                    startActivity(intent)
                    break
                }
            }
            if(isNotLog) {
                val toastMessage = "Неверный логин или пароль, пожалуйста, попробуйте снова"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
            }
            dbManager.closeDb()
            // Действие на нажатие на кнопку Войти
        }
    }

    private fun onMigrateToRegistration() {
        val registrationText: TextView = findViewById(R.id.textViewRegistration)
        registrationText.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            // Действие на кнопку Зарегистрироваться
        }
    }
}