package com.yannainglynn.favdictionary.databases

import android.content.Context
import com.yannainglynn.favdictionary.lib.AssetsSQLite

class AppAssetsSQLite(context: Context):
        AssetsSQLite(context, DATABASE_NAME) {

    companion object {
        const val DATABASE_NAME = "favdb.db"
    }
}