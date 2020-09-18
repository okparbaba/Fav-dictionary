package com.yannainglynn.favdictionary.databases.controllers

import android.content.Context
import android.database.Cursor
import com.yannainglynn.favdictionary.databases.contracts.ContentEntries

class ContentDBController(context: Context) : AppDBController(context) {
    fun getWords(): Cursor {
        return selectAll(ContentEntries.TABLE_NAME)
    }
    fun search(words:String):Cursor{
        return search(ContentEntries.TABLE_NAME,words)
    }

}