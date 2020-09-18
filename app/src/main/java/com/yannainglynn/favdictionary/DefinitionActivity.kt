package com.yannainglynn.favdictionary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yannainglynn.favdictionary.databases.contracts.ExampleEntries
import com.yannainglynn.favdictionary.databases.controllers.ExampleDBController
import com.yannainglynn.favdictionary.ext.Holder
import kotlinx.android.synthetic.main.activity_definition.*

class DefinitionActivity : AppCompatActivity() {
    private lateinit var controller: ExampleDBController
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)
        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.arrow)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        controller = ExampleDBController(this)
        //var cursor = controller.getLanguages()
        tvWord.text = "${Holder.TITLE}(${Holder.CAT}, ${Holder.PHO})"
        tvDef.text = Holder.DEF
        val cursor = controller.search("${ExampleEntries.EXAMPLE} LIKE '%${Holder.TITLE?.trim()}%'")
        if (cursor.moveToFirst()) {
            do {
                val data = cursor.getString(cursor.getColumnIndex(ExampleEntries.EXAMPLE))
                val t =  data.replace("\\n", System.getProperty("line.separator"))
                val w = t.replace("\\r","")
                tvUsage.text = "Usage : \n$w"
                // do what ever you want here
            } while (cursor.moveToNext())
        }
        cursor.close()

    }


}