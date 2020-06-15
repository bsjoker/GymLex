package ru.heathalphaapp.jdls.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.heathalphaapp.jdls.models.VisitorModel

class VisitorRepo(private val visitorsDao: VisitorsDao) {
    fun getAllVisitors(): LiveData<List<VisitorModel>>{
        return visitorsDao.getAllVisitors()
    }

    fun getVisitorsBySection(section:String): LiveData<List<VisitorModel>>{
        return visitorsDao.getVisitorsSection(section)
    }

    fun getVisitorsByActive(isActive:Boolean): LiveData<List<VisitorModel>>{
        return visitorsDao.getActiveVisitors(isActive)
    }

    fun insertVisitor(visitorModel: VisitorModel){
        visitorsDao.addVisitor(visitorModel)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: VisitorRepo? = null

        fun getInstance(visitorsDao: VisitorsDao) =
            instance ?: synchronized(this) {
                instance ?: VisitorRepo(visitorsDao).also { instance = it }
            }
    }
}