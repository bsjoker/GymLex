package ru.heathalphaapp.jdls.viewmodels

import android.util.Log
import androidx.lifecycle.*
import ru.heathalphaapp.jdls.database.AppDatabase
import ru.heathalphaapp.jdls.database.VisitorRepo
import ru.heathalphaapp.jdls.models.VisitorModel

class VisitorViewModel internal constructor(
    repository: VisitorRepo
) : ViewModel() {
    val sectionValue: MutableLiveData<String> = MutableLiveData()
    val activeValue: MutableLiveData<Boolean> = MutableLiveData()
    val repo = repository

    val visitors: LiveData<List<VisitorModel>> = getSavedSectionName().switchMap {
        if (it == NO_SECTION) {
            repository.getAllVisitors()
        } else {
            repository.getVisitorsBySection(it)
        }
    }

    val activeVisitors: LiveData<List<VisitorModel>> = getSavedActive().switchMap {
        repository.getVisitorsByActive(it)
    }

    fun insertVisitor(visitor: VisitorModel) {
        repo.insertVisitor(visitor)
        Log.d("TAG_VM", "Add in insert")
    }

    fun setSectionName(section: String) {
        sectionValue.value = section
    }

    fun clearSectionName() {
        sectionValue.value = NO_SECTION
    }

    fun setActive(isActive: Boolean) {
        activeValue.value = isActive
    }

    private fun getSavedSectionName(): MutableLiveData<String> {
        return sectionValue
    }

    private fun getSavedActive(): MutableLiveData<Boolean> {
        return activeValue
    }

    companion object {
        private const val NO_SECTION = ""
        private const val SECTION_STATE_KEY = "SECTION_STATE_KEY"
    }
}