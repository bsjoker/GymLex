package ru.heathalphaapp.jdls

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_section.*
import ru.heathalphaapp.jdls.adapters.RVAdapter
import ru.heathalphaapp.jdls.models.VisitorModel
import ru.heathalphaapp.jdls.viewmodels.VisitorViewModel

class SectionActivity : AppCompatActivity() {
    private var listOfVisitors: ArrayList<VisitorModel> = ArrayList()
    lateinit var myAdapter: RVAdapter
    private val visitorsVM: VisitorViewModel by viewModels {
        InjectorUtils.provideVisitorViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        visitorsVM.clearSectionName()
        visitorsVM.visitors.observe(this, Observer { visitorsList ->
            visitorsList?.let {
                listOfVisitors.clear()
                listOfVisitors.addAll(it)
                myAdapter.notifyDataSetChanged()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = RVAdapter(listOfVisitors)
        recyclerView.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_section,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_all_vizitors -> {
            visitorsVM.clearSectionName()
            toolbar.title = "All visitors"
            true
        }

        R.id.action_fitnes -> {
            visitorsVM.setSectionName("fitnes")
            toolbar.title = "Fitnes"
            true
        }

        R.id.action_gym -> {
            visitorsVM.setSectionName("gym")
            toolbar.title = "Gym"
            true
        }

        R.id.action_yoga -> {
            visitorsVM.setSectionName("yoga")
            toolbar.title = "Yoga"
            true
        }

        android.R.id.home ->{
            finish()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
