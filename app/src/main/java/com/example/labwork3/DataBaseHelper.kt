package com.example.labwork3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "data"
        private const val TABLE_CONTACTS = "passwordTable"
        private const val KEY_PASSWORD = "password"
        private const val KEY_TIME = "time"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_CONTACTS ($KEY_PASSWORD TEXT, $KEY_TIME TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    fun clear(){
        val db = this.writableDatabase
        db.delete(TABLE_CONTACTS, null , null)
    }

    fun addData(data: String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_PASSWORD, data)
        contentValues.put(KEY_TIME, getTime())
        db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getData(): List<WriteModel> {
        val db = this.readableDatabase
        val modelList: ArrayList<WriteModel> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            do {
                val pass = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                val time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
                val model = WriteModel(password = pass, time = time)
                modelList.add(model)
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return modelList
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(): String{
        val formatter = SimpleDateFormat("hh:mm:ss a")
        return formatter.format(Date())
    }
}