package com.yannainglynn.favdictionary

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yannainglynn.favdictionary.adapters.ContentAdapter
import com.yannainglynn.favdictionary.databases.contracts.ContentEntries
import com.yannainglynn.favdictionary.databases.controllers.ContentDBController
import com.yannainglynn.favdictionary.ext.Holder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var controller: ContentDBController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = ContentDBController(this)
        //var cursor = controller.getLanguages()
        var cursor = controller.search("${ContentEntries.WORD} LIKE 'appl%'")
        var adapter = ContentAdapter(this, cursor,"appl"){
            Holder.TITLE = it.TITLE
            Holder.CAT = it.CATEGORY
            Holder.PHO = it.PHONETIC
            Holder.DEF = it.itemView.tvExample.text.toString()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,DefinitionFragment())
                .addToBackStack(null)
                .commit()
            //startActivity(Intent(this@MainActivity,DefinitionActivity::class.java))
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        //sample_text.text = stringFromJNI()
        etSearchWord?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.contains("\'")){
                    Toast.makeText(this@MainActivity, "Wrong Input", Toast.LENGTH_SHORT).show()
                }else{
                    controller.search("${ContentEntries.WORD} LIKE '%$p0%'").let { it ->
                        cursor = it
                        adapter = ContentAdapter(this@MainActivity, cursor,p0.toString()){
                            Holder.TITLE = it.TITLE
                            Holder.CAT = it.CATEGORY
                            Holder.PHO = it.PHONETIC
                            Holder.DEF = it.itemView.tvExample.text.toString()
                            //startActivity(Intent(this@MainActivity,DefinitionActivity::class.java))
                            supportFragmentManager.beginTransaction()
                                .add(R.id.fragmentContainer,DefinitionFragment())
                                .commit()
                        }
                        recyclerView?.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

    external fun stringFromJNI(): String
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
