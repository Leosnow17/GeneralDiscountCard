package com.example.generaldiscountcard.db

import android.provider.BaseColumns

object DbNameClass {
    const val TABLE_NAME_1 = "user"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "GeneralDiscountCardDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME_1 (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_LOGIN TEXT,$COLUMN_NAME_PASSWORD TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_1"

}