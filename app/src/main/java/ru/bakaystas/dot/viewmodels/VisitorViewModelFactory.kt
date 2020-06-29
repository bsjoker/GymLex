package ru.bakaystas.dot.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bakaystas.dot.database.VisitorRepo

class VisitorViewModelFactory (
    private val repository: VisitorRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VisitorViewModel(repository) as T

    }
}