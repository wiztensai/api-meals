package com.jetwiz.admin.network

import com.jetwiz.admin.model.ModelCategories
import com.jetwiz.admin.model.ModelMeals
import retrofit2.http.*

interface ServiceMeals {
    @GET("categories.php")
    suspend fun getCategories(): ModelCategories

    @GET("filter.php")
    suspend fun getListMealsByCategory(
        @Query("c") category: String
    ): ModelMeals

    @GET("lookup.php")
    suspend fun getDetailMeal(
        @Query("i") mealId: Int
    ): ModelMeals
}
