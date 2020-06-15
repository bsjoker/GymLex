package ru.heathalphaapp.jdls

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choose.*


class ChooseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        btnNewVisitor.setOnClickListener(this)
        btnChooseSection.setOnClickListener(this)
        btnChooseActive.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnNewVisitor -> startActivity(Intent(this, NewVisitorActivity::class.java))
            R.id.btnChooseSection -> startActivity(Intent(this, SectionActivity::class.java))
            R.id.btnChooseActive -> startActivity(Intent(this, ActiveActivity::class.java))
        }
    }
}