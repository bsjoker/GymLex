package ru.bakaystas.dot

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_section.*
import ru.bakaystas.dot.adapters.RVAdapter
import ru.bakaystas.dot.models.VisitorModel
import ru.bakaystas.dot.viewmodels.VisitorViewModel

class ActiveActivity : AppCompatActivity() {
    private var listVisitors: ArrayList<VisitorModel> = ArrayList()
    lateinit var myAdapter: RVAdapter
    private val visitorsVM: VisitorViewModel by viewModels {
        InjectorUtils.provideVisitorViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        visitorsVM.setActive(true)

        visitorsVM.activeVisitors.observe(this, Observer { visitorsList ->
            visitorsList?.let {
                listVisitors.clear()
                listVisitors.addAll(it)
                myAdapter.notifyDataSetChanged()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = RVAdapter(listVisitors)
        recyclerView.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_active,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_active -> {
            visitorsVM.setActive(true)
            toolbar.title = "Active visitors"
            true
        }

        R.id.action_inactive -> {
            visitorsVM.setActive(false)
            toolbar.title = "Inactive visitors"
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
