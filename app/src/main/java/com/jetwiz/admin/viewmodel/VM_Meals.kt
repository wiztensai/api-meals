package com.jetwiz.admin.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetwiz.admin.model.DataCategory
import com.jetwiz.admin.model.DataMeals
import com.jetwiz.admin.model.ModelCategories
import com.jetwiz.admin.model.ModelMeals
import com.jetwiz.admin.repository.RepoMeals
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.utils.U_Prefs
import timber.log.Timber

class VM_Meals(context: Context) : ViewModel() {

    private var prefs: U_Prefs
    private var repoMeals:RepoMeals

    var dataMeals = MutableLiveData<DataMeals>()
        private set

    init {
        prefs = U_Prefs(context)
        repoMeals = RepoMeals(context)
    }

    suspend fun getMealsByCategory(category: String) {
        try {
            val res = repoMeals.getMealsByCategory(category)

            if (res.networkState.status == NetworkState.FAILED.status) {
                val msg = res.networkState.msg
                dataMeals.value = DataMeals(ModelMeals(), NetworkState.error(msg))
            } else {
                dataMeals.value = DataMeals(res.modelMeals, NetworkState.LOADED)
            }
        } catch (t: Throwable) {
            Timber.e(t)
            dataMeals.value = DataMeals(ModelMeals(), NetworkState.error(t.message))
        }
    }

    class VMFactory constructor(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM_Meals::class.java)) {
                VM_Meals(application) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}