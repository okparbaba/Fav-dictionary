package com.yannainglynn.favdictionary.databases.controllers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.yannainglynn.favdictionary.databases.AppAssetsSQLite

open class AppDBController(context: Context) {
    private val sqLiteDatabase: SQLiteDatabase

    init {
        val appAssetsSQLite = AppAssetsSQLite(context)
        sqLiteDatabase = appAssetsSQLite.writableDatabase!!
    }

    fun selectAll(tableName: String): Cursor {
        return sqLiteDatabase.query(tableName, null, null, null,
                null, null, null, null)
    }

    fun search(tableName: String,search:String): Cursor {
        return sqLiteDatabase.query(tableName, null, search, null,
                null, null, null, null)
    }


    fun close() {
        sqLiteDatabase.close()
    }
}
