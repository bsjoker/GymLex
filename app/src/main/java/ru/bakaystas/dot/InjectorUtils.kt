/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.bakaystas.dot

import android.app.Activity
import android.content.Context

import ru.bakaystas.dot.database.AppDatabase
import ru.bakaystas.dot.database.VisitorRepo
import ru.bakaystas.dot.viewmodels.VisitorViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context): VisitorRepo {
        return VisitorRepo.getInstance(
                AppDatabase.getInstance(context.applicationContext).visitorsDao())
    }

    fun provideVisitorViewModelFactory(activity: Activity): VisitorViewModelFactory {
        return VisitorViewModelFactory(getPlantRepository(activity.applicationContext))
    }
}
