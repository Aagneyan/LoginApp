package com.example.loginapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):SQLiteOpenHelper(context, dbname, factory, version){

    companion object{
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table ss(stringid integer primary key autoincrement , lmao varchar(100)) ")
        p0?.execSQL("create table user(id integer primary key autoincrement , name varchar(30) , username varchar(30) , password varchar(30)) ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser(name:String, uname:String , password:String){
        val sen = "Hello my frand how u doing"
        val db:SQLiteDatabase = writableDatabase
        val values : ContentValues = ContentValues()
        val values2 : ContentValues = ContentValues()
        values.put("name",name)
        values.put("username",uname)
        values.put("password",password)
        values2.put("lmao",sen)
        db.insert("user" , null, values)
        db.insert("ss",null,values2)
        db.close()
    }

    fun userPresent(usname:String, passw:String) : Boolean{
        val db:SQLiteDatabase = writableDatabase
        val query = "select  * from user where username = '$usname' and password = '$passw'"
        val cursor = db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        else{
            return true
        }
        db.close()
        cursor.close()
    }
    fun exctactWord() : String{
        val db2:SQLiteDatabase = writableDatabase
        var query = "select * from ss"
        val cursor = db2.rawQuery(query , null)
        var theSentence = cursor.toString()
        val i = theSentence.indexOf(' ')
        var word = theSentence.substring(0 , i)
        return word
    }
}