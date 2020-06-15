package ru.heathalphaapp.jdls

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_visitor.*
import ru.heathalphaapp.jdls.database.AppDatabase
import ru.heathalphaapp.jdls.models.VisitorModel


class NewVisitorActivity : AppCompatActivity() {
    var sectionText: String = ""
    var isActive: Boolean = true
    var sections =
        arrayOf("Fitnes", "Gym", "Yoga")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_visitor)

        val spinner = findViewById<View>(R.id.spinner_section) as Spinner
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sections)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                sectionText = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        switch_button_active.setOnCheckedChangeListener { buttonView, isChecked ->
            isActive = isChecked
        }

        btn_create.setOnClickListener { AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = et_fio.text.toString(), age = et_age.text.toString().toInt(), section = sectionText, active = isActive)
        )
        finish()}
    }
}
