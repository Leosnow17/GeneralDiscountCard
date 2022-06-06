package com.example.cardholderapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.generaldiscountcard.db.DbManager


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val dbManager = DbManager(this)
        val edLogin = findViewById<EditText>(R.id.editTextNameText)
        val edPass = findViewById<EditText>(R.id.editTextTextPasswordAddress)

        onMigrateToLogin()

        val registrationButton: Button = findViewById(R.id.buttonRegistration)
        registrationButton.setOnClickListener {
            dbManager.openDb()
            var isReg = false
            val userList = dbManager.readDataTableUser()
            //Log.e("reg", "$userList")
            for(user in userList){
                if (edLogin.text.toString() == user.first){
                    isReg = true
                    val toastMessage = "Такой логин уже существует, пожалуйста, войдите"
                    Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
                    break
                }
                else{
                    if (edLogin.text.toString().isBlank() || edPass.text.toString().isEmpty()) {
                        isReg = true
                        val toastMessage = "Введенные данные не могут быть пустыми или содержать только пробелы"
                        Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
                        break
                    }
                }
            }
            if (!isReg) {
                dbManager.insertToTableUser(edLogin.text.toString(), edPass.text.toString())
                dbManager.closeDb()
                val intent = Intent(this, CardsActivity::class.java)
                startActivity(intent)
            }
            dbManager.closeDb()
            // Действие на нажатие на кнопку Зарегистрироваться
        }
    }

    private fun onMigrateToLogin() {
        val loginText: TextView = findViewById(R.id.textViewLogin)
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // Действие на нажатие на текст Войти
        }
    }
}