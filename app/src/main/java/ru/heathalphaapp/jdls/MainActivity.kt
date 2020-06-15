package ru.heathalphaapp.jdls

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ru.heathalphaapp.jdls.database.AppDatabase
import ru.heathalphaapp.jdls.models.VisitorModel
import ru.heathalphaapp.jdls.viewmodels.VisitorViewModel
import ru.heathalphaapp.jdls.viewmodels.VisitorViewModelFactory

class MainActivity : AppCompatActivity() {

    private val visitorsVM: VisitorViewModel by viewModels() {
        InjectorUtils.provideVisitorViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = "Иванов", age = 18, section = "gym", active = false)
        )
        AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = "Волкова", age = 18, section = "fitnes", active = false)
        )
        AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = "Смирнов", age = 30, section = "gym", active = true)
        )
        AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = "Иванов", age = 35, section = "gym", active = false)
        )
        AppDatabase.insertData(
            AppDatabase.getInstance(this),
            VisitorModel(fio = "Симова", age = 27, section = "fitnes", active = true)
        )

//        visitorsVM.insertVisitor(visitor = VisitorModel(id = 1, fio = "Иванов", age = 18, section = "gym", active = false))
//        visitorsVM.insertVisitor(visitor = VisitorModel(id = 2, fio = "Волкова", age = 18, section = "gym", active = false))
//        visitorsVM.insertVisitor(visitor = VisitorModel(id = 3, fio = "Смирнов", age = 30, section = "gym", active = true))
//        visitorsVM.insertVisitor(visitor = VisitorModel(id = 4, fio = "Иванов", age = 35, section = "gym", active = false))
//        visitorsVM.insertVisitor(visitor = VisitorModel(id = 5, fio = "Симова", age = 27, section = "fitnes", active = true))

        visitorsVM.visitors.observe(this, Observer { visitorsList ->
            visitorsList?.let {
                it.forEach {
                    Log.d("TAG_MainActiviry", it.fio)
                }
                Log.d("TAG_MainActiviry", "scdcdv")
            }
        })

        //visitorsVM.getVisitorsBySections("fitnes")
        btnGetSecFitnes.setOnClickListener { visitorsVM.setSectionName("fitnes") }
        btnGetSecGym.setOnClickListener { visitorsVM.setSectionName("gym") }
        btnGetSecAll.setOnClickListener { visitorsVM.clearSectionName() }
        //btnGetSec.setOnClickListener { visitorsVM.insertVisitor(visitor = VisitorModel(id = 6, fio = "Домов", age = 25, section = "fitnes", active = true)) }
    }
}
