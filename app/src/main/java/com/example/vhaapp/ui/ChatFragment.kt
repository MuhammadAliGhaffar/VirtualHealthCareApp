package com.example.vhaapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.vhaapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChatFragment : Fragment() {

    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var chipGroup: ChipGroup
    lateinit var sendButton: ImageButton
    private val list = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        chipGroup = view.findViewById(R.id.chipGroup)
        sendButton = view.findViewById(R.id.sendButton)

        val languages = resources.getStringArray(R.array.Languages)
        autoCompleteTextView.threshold = 1

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, languages)
        autoCompleteTextView.setAdapter(adapter)


        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(context,adapterView.adapter.getItem(i).toString(),Toast.LENGTH_SHORT).show()
        }


        sendButton.setOnClickListener {
            if (autoCompleteTextView.text.isEmpty() || autoCompleteTextView.text.isBlank()){
                Toast.makeText(context,"Enter some symtoms",Toast.LENGTH_SHORT).show()
            }else{
                list.add(autoCompleteTextView.text.toString())
                addChipToGroup(autoCompleteTextView.text.toString())
                autoCompleteTextView.setText("")
            }
        }



//        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                val text = s.toString()
//                if (text.isNotEmpty() && (text.last().toString() == ","))
//                    autoCompleteTextView.setText("")
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!s.isNullOrEmpty()) {
//                    if (s.length > 1 && s.last().toString() == ",") {
//                        val text = s.toString().replace(",", "")
//                        addChipToGroup(text)
//                    }
//                }
//            }
//        })
        view.findViewById<Button>(R.id.testButton).setOnClickListener {
            Toast.makeText(context,list.toString()+" Size = ${list.size}",Toast.LENGTH_SHORT).show()
        }
    }

    private fun addChipToGroup(personName: String) {
        val chip = Chip(context)
        chip.text = personName
        chip.chipIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.logo) }
        chip.isChipIconVisible = true
        chip.isCloseIconVisible = true
        chip.isClickable = true
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip as View)
            Toast.makeText(context, "${chip.text} has been removed", Toast.LENGTH_SHORT).show()
            list.remove(chip.text)
        }
    }


}