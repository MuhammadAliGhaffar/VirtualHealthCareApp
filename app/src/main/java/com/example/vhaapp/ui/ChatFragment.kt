package com.example.vhaapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vhaapp.R
import com.example.vhaapp.model.BriefSolution
import com.example.vhaapp.utils.KeyValueStore
import com.example.vhaapp.utils.Utils
import com.example.vhaapp.utils.hideKeyboard
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var chipGroup: ChipGroup
    lateinit var sendButton: ImageButton
    lateinit var imgBack:ImageView
    private val list = ArrayList<String>()
    private lateinit var diseaseContainer: CardView
    private lateinit var briefDiseaseContainer: CardView
    private lateinit var severityDiseaseContainer: CardView
    private lateinit var tipsAndAdviceContainer:CardView
    private lateinit var helpContainer:CardView
    private lateinit var suggestDoctorContainer:CardView
    private lateinit var suggestDoctorImageView : ImageView

    private lateinit var tempBriefSolution: BriefSolution

    private val viewModel: ChatViewModel by viewModels()

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
        imgBack = view.findViewById(R.id.imgBack)
        suggestDoctorImageView = view.findViewById(R.id.suggestDoctorImageView)
        diseaseContainer = view.findViewById(R.id.diseaseContainer)
        briefDiseaseContainer = view.findViewById(R.id.briefDiseaseContainer)
        severityDiseaseContainer = view.findViewById(R.id.severityDiseaseContainer)
        tipsAndAdviceContainer = view.findViewById(R.id.tipsAndAdviceContainer)
        helpContainer = view.findViewById(R.id.helpContainer)
        suggestDoctorContainer = view.findViewById(R.id.suggestDoctorContainer)

        val languages = Utils.returnSynonymsList()
        autoCompleteTextView.threshold = 1

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, languages
        )
        autoCompleteTextView.setAdapter(adapter)


        autoCompleteTextView.setOnItemClickListener { adapterView, _, i, _ ->
            Toast.makeText(context, adapterView.adapter.getItem(i).toString(), Toast.LENGTH_SHORT)
                .show()
        }

        sendButton.setOnClickListener {
            if (list.size > 2) {
                //post symptoms
                viewModel.predictDisease(list, KeyValueStore.getPatientDetails().patient_id.toString()) { isSuccessful, diseaseName ->
                    if (isSuccessful) {
                        view.findViewById<TextView>(R.id.diseaseTextView).text =
                            resources.getString(R.string.disease, diseaseName)
                        viewModel.briefSolution(diseaseName) { _, _, breifSolutionObject ->
                            view.findViewById<TextView>(R.id.severityDiseaseTextView).text =
                                resources.getString(
                                    R.string.severity,
                                    breifSolutionObject.disease_severity
                                )
                            view.findViewById<TextView>(R.id.briefDiseaseTextView).text =
                                breifSolutionObject.disease_description
                            view.findViewById<TextView>(R.id.tipsAndAdviceTextView).text =
                                breifSolutionObject.tip_disease
                            view.findViewById<TextView>(R.id.helpTextView).text = breifSolutionObject.tip_description

                            if (breifSolutionObject.doctor_gender == "Female"){
                                suggestDoctorImageView.setImageDrawable(resources.getDrawable(R.drawable.female_doctor))
                            }else {
                                suggestDoctorImageView.setImageDrawable(resources.getDrawable(R.drawable.male_doctor))
                            }
                            view.findViewById<TextView>(R.id.suggestDoctorTextView).text = breifSolutionObject.doctor_fname

                            tempBriefSolution = breifSolutionObject
                            //Visibility On
                            severityDiseaseContainer.visibility = View.VISIBLE
                            diseaseContainer.visibility = View.VISIBLE
                            briefDiseaseContainer.visibility = View.VISIBLE
                            tipsAndAdviceContainer.visibility = View.VISIBLE
                            helpContainer.visibility = View.VISIBLE
                            suggestDoctorContainer.visibility = View.VISIBLE
                            hideKeyboard()
                        }
                    } else {

                    }
                }

                //empty list and clear chip again
                chipGroup.removeAllViews()
                list.clear()
            } else {
                Toast.makeText(context, "Enter minimum 3 symptoms atleast", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //Book Appointment
        view.findViewById<Button>(R.id.suggestDoctorButton).setOnClickListener {
            val bundle = Bundle()
            bundle.putString("current_company",tempBriefSolution.current_company)
            bundle.putString("designation",tempBriefSolution.designation)
            bundle.putString("disease_description",tempBriefSolution.disease_description)
            bundle.putInt("disease_id",tempBriefSolution.disease_id)
            bundle.putString("disease_name",tempBriefSolution.disease_name)
            bundle.putString("disease_severity",tempBriefSolution.disease_severity)
            bundle.putString("disease_specialization",tempBriefSolution.disease_specialization)
            bundle.putString("doctor_fname",tempBriefSolution.doctor_fname)
            bundle.putString("doctor_gender",tempBriefSolution.doctor_gender)
            bundle.putInt("doctor_id",tempBriefSolution.doctor_id)
            bundle.putString("doctor_lname",tempBriefSolution.doctor_lname)
            bundle.putString("doctor_username",tempBriefSolution.doctor_username)
            bundle.putString("email",tempBriefSolution.email)
            bundle.putString("qualification",tempBriefSolution.qualification)
            bundle.putString("specialization",tempBriefSolution.specialization)
            bundle.putString("tip_description",tempBriefSolution.tip_description)
            bundle.putString("tip_disease",tempBriefSolution.tip_disease)
            bundle.putInt("tip_id",tempBriefSolution.tip_id)
            bundle.putString("year_of_experience",tempBriefSolution.year_of_experience)

            Log.d("AliTag",tempBriefSolution.toString())
            findNavController().navigate(R.id.action_chatFragment_to_doctorAppointmentDetailsFragment,bundle)

        }

        autoCompleteTextView.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(context, adapter.getItem(i).toString(), Toast.LENGTH_SHORT).show()
            list.add(adapter.getItem(i).toString())
            addChipToGroup(adapter.getItem(i).toString())
            autoCompleteTextView.setText("")
        }

        imgBack.setOnClickListener {
            findNavController().navigateUp()
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
            list.remove(chip.text)
        }
    }


}