package com.jetwiz.admin.repository

import android.content.Context
import com.jetwiz.admin.model.DataCategory
import com.jetwiz.admin.model.DataMeals
import com.jetwiz.admin.model.ModelCategories
import com.jetwiz.admin.model.ModelMeals
import com.jetwiz.admin.network.ServiceMeals
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.utils.U_Api
import com.jetwiz.admin.utils.U_Prefs
import timber.log.Timber

class RepoMeals(var context: Context) {

    private var prefs: U_Prefs

    init {
        prefs = U_Prefs(context)
    }

    suspend fun getCategories():DataCategory {
        val apiService = U_Api.retrofit.create(ServiceMeals::class.java)
        try {
            val res = apiService.getCategories()
            return DataCategory(res, NetworkState.LOADED)
        }catch (e:Throwable) {
            Timber.e(e)
            return DataCategory(ModelCategories(), NetworkState.error(e.message))
        }
    }

    suspend fun getMealsByCategory(category: String): DataMeals {
        val apiService = U_Api.retrofit.create(ServiceMeals::class.java)
        try {
            val res = apiService.getListMealsByCategory(category)
            return DataMeals(res, NetworkState.LOADED)
        }catch (e:Throwable) {
            Timber.e(e)
            return DataMeals(ModelMeals(), NetworkState.error(e.message))
        }
    }

    suspend fun getDetailMeal(mealId: Int): DataMeals {
        val apiService = U_Api.retrofit.create(ServiceMeals::class.java)
        try {
            val res = apiService.getDetailMeal(mealId)
            return DataMeals(res, NetworkState.LOADED)
        }catch (e:Throwable) {
            Timber.e(e)
            return DataMeals(ModelMeals(), NetworkState.error(e.message))
        }
    }
}