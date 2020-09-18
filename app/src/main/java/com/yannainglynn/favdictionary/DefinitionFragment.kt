package com.yannainglynn.favdictionary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.yannainglynn.favdictionary.databases.contracts.ExampleEntries
import com.yannainglynn.favdictionary.databases.controllers.ExampleDBController
import com.yannainglynn.favdictionary.ext.Holder
import kotlinx.android.synthetic.main.fragment_definition.*
import java.util.*

class DefinitionFragment : Fragment(){
    private lateinit var controller: ExampleDBController
    private lateinit var tts:TextToSpeech
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definition, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = ExampleDBController(activity!!)
        tvWord?.text = "${Holder.TITLE}"
        tvPhonetic?.text = "[${Holder.CAT}, ${Holder.PHO}]"
        val defi = Html.fromHtml("<font color=#ea2d44>A. </font>${Holder.DEF}")
        tvDefi?.text = defi
        val word = Holder.TITLE?.trim()?.replace("\'","")
        val cursor = controller.search("${ExampleEntries.EXAMPLE} LIKE '%${word}%'")
        if (cursor.moveToFirst()) {
            do {
                val data = cursor.getString(cursor.getColumnIndex(ExampleEntries.EXAMPLE))
                val t =  data.replace("\\n", System.getProperty("line.separator"))
                val w = t.replace("\\r","")
                tvUsage.text = Html.fromHtml("<font color=#ea2d44>1. </font>$w")
                // do what ever you want here
            } while (cursor.moveToNext())
        }
       tts = TextToSpeech(activity, TextToSpeech.OnInitListener { p0 ->
           if (p0!=TextToSpeech.ERROR){
               tts.language = Locale.UK
           }
       })
        cursor.close()
        ivBack?.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }
        ivTalk?.setOnClickListener {
            tts.speak(Holder.TITLE,TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

}