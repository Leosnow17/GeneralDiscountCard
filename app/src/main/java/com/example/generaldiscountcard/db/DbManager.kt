package com.example.generaldiscountcard.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = dbHelper.writableDatabase
    }
    fun insertToTableUser(login: String, password: String){
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME_LOGIN, login)
            put(DbNameClass.COLUMN_NAME_PASSWORD, password)
        }
        db?.insert(DbNameClass.TABLE_NAME_1, null, values)
    }
    fun readDataTableUser() : ArrayList<Pair<String, String>>{
        val dataList = ArrayList<Pair<String, String>>()
        val cursor = db?.query(DbNameClass.TABLE_NAME_1, null, null, null,
            null, null, null)
        while (cursor?.moveToNext()!!){
            val loginData = cursor.getString(cursor.getColumnIndexOrThrow(DbNameClass.COLUMN_NAME_LOGIN))
            val passData = cursor.getString(cursor.getColumnIndexOrThrow(DbNameClass.COLUMN_NAME_PASSWORD))
            dataList.add(Pair(loginData.toString(), passData.toString()))
        }
         cursor.close()
         return dataList
    }
    fun closeDb(){
        dbHelper.close()
    }
}