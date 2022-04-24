package com.example.vhaapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.vhaapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChatFragment : Fragment() {

    lateinit var messageTo: AutoCompleteTextView
    lateinit var personList: ChipGroup
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        messageTo = view.findViewById(R.id.messageTo)
        personList = view.findViewById(R.id.personList)
        val items = arrayOf(
            "Paries,France", "PA,United States", "Parana,Brazil",
            "Padua,Italy", "Pasadena,CA,United States"
        )

        messageTo.threshold = 2
        messageTo.setAdapter(context?.let { ArrayAdapter(it,android.R.layout.select_dialog_item,items) })



        messageTo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.isNotEmpty() && (text.last().toString() == ","))
                    messageTo.setText("")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    if (s.length > 1 && s.last().toString() == ",") {
                        val text = s.toString().replace(",", "")
                        addChipToGroup(text)
                    }
                }
            }
        })
    }

    private fun addChipToGroup(personName: String) {
        val chip = Chip(context)
        chip.text = personName
        chip.chipIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.logo) }
        chip.isChipIconVisible = true
        chip.isCloseIconVisible = true
        chip.isClickable = true
        chip.isCheckable = false
        personList.addView(chip as View)
        chip.setOnCloseIconClickListener {
            personList.removeView(chip as View)
            Toast.makeText(context, "${chip.text} has been removed", Toast.LENGTH_SHORT).show()
        }
    }


}