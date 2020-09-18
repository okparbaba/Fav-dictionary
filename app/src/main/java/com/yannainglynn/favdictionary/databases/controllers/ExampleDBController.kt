package com.yannainglynn.favdictionary.databases.controllers

import android.content.Context
import android.database.Cursor
import com.yannainglynn.favdictionary.databases.contracts.ExampleEntries

class ExampleDBController(context: Context) : AppDBController(context) {
    fun search(words:String): Cursor {
        return search(ExampleEntries.TABLE_NAME,words)
    }

}