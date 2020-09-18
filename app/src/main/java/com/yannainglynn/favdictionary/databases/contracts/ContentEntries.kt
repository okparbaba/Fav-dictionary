package com.yannainglynn.favdictionary.databases.contracts

import android.provider.BaseColumns

class ContentEntries: BaseColumns {
    companion object {
        const val TABLE_NAME = "content_table"
        const val WORD = "_word"
        const val PHONETIC = "_phonetic"
        const val CATEGORY = "_category"
        const val DEFINITION = "_definition"
        const val FAVOURITE = "_favorite"
    }
}