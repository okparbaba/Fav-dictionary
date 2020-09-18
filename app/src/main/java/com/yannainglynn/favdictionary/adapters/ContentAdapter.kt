package com.yannainglynn.favdictionary.adapters

import android.content.Context
import android.database.Cursor
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yannainglynn.favdictionary.R
import com.yannainglynn.favdictionary.databases.contracts.ContentEntries
import kotlinx.android.synthetic.main.item.view.*


class ContentAdapter(
    private val context: Context?,
    private val cursor: Cursor?,
    private val text:String?,
    private val onclick: (vh: ViewHolder) -> Unit
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor?.moveToPosition(position)
        with(holder) {
            val txt = cursor?.getString(cursor.getColumnIndex(ContentEntries.WORD))
            if (text!=null){
                if (txt!=null){
                    val ft = txt.toLowerCase().replace(text,"")
                    itemView.tvWord?.text = Html.fromHtml("<font color=#ea2d44>$text</font>$ft")
                }
            } else itemView.tvWord?.text = txt
            itemView.tvExample?.text = cursor?.getString(cursor.getColumnIndex(ContentEntries.DEFINITION))
            itemView.setOnClickListener { onclick(holder) }
            PHONETIC = cursor?.getString(cursor.getColumnIndex(ContentEntries.PHONETIC))
            CATEGORY = cursor?.getString(cursor.getColumnIndex(ContentEntries.CATEGORY))
            FAVOURITE = cursor?.getString(cursor.getColumnIndex(ContentEntries.FAVOURITE))
            TITLE =cursor?.getString(cursor.getColumnIndex(ContentEntries.WORD))
        }
    }

    override fun getItemCount(): Int {
        return cursor?.count!!
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var PHONETIC: String? = ""
        var CATEGORY: String? = ""
        var FAVOURITE: String? = ""
        var TITLE:String? = ""

    }
}