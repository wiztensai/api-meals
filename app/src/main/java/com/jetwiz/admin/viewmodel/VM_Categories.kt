package com.jetwiz.admin.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetwiz.admin.model.DataCategory
import com.jetwiz.admin.model.ModelCategories
import com.jetwiz.admin.repository.RepoMeals
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.utils.U_Prefs
import timber.log.Timber

class VM_Categories(context: Context) : ViewModel() {

    private var prefs: U_Prefs
    private var repoMeals:RepoMeals
    var dataCategory = MutableLiveData<DataCategory>()
        private set

    init {
        prefs = U_Prefs(context)
        repoMeals = RepoMeals(context)
    }

    suspend fun getCategories() {
        try {
            val res = repoMeals.getCategories()

            if (res.networkState.status == NetworkState.FAILED.status) {
                val msg = res.networkState.msg
                dataCategory.value = DataCategory(ModelCategories(), NetworkState.error(msg))
            } else {
                dataCategory.value = DataCategory(res.modelCategories, NetworkState.LOADED)
            }

        } catch (t: Throwable) {
            Timber.e(t)
            dataCategory.value = DataCategory(ModelCategories(), NetworkState.error(t.message))
        }
    }

    class VMFactory constructor(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM_Categories::class.java)) {
                VM_Categories(application) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}