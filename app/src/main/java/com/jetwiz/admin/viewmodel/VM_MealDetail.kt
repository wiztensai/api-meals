package com.jetwiz.admin.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetwiz.admin.model.*
import com.jetwiz.admin.repository.RepoMeals
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.utils.U_Prefs
import timber.log.Timber

class VM_MealDetail(context: Context) : ViewModel() {

    private var prefs: U_Prefs
    private var repoMeals:RepoMeals

    var dataMealDetail = MutableLiveData<DataMeals>()
        private set


    init {
        prefs = U_Prefs(context)
        repoMeals = RepoMeals(context)
    }

    suspend fun getDetailMeal(mealId: Int) {
        try {
            val res = repoMeals.getDetailMeal(mealId)

            if (res.networkState.status == NetworkState.FAILED.status) {
                val msg = res.networkState.msg
                dataMealDetail.value = DataMeals(ModelMeals(), NetworkState.error(msg))
            } else {
                dataMealDetail.value = DataMeals(res.modelMeals, NetworkState.LOADED)
            }

        } catch (t: Throwable) {
            Timber.e(t)
            dataMealDetail.value = DataMeals(ModelMeals(), NetworkState.error(t.message))
        }
    }

    fun getRecipes(mealsItem: MealsItem):String {
        val recipes = validateRecipes(mealsItem)

        var recipeString = ""
        for (i in recipes) {
            recipeString += "- ${i.keys.single()} (${i.values.single()}) \n"
        }

        return recipeString
    }

    private fun validateRecipes(mealsItem: MealsItem):MutableList<Map<String,String>> {
        val recipes = mutableListOf<Map<String, String>>()
        checkNullEmptyIngredient(mealsItem.strIngredient1, mealsItem.strMeasure1)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient2, mealsItem.strMeasure2)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient3, mealsItem.strMeasure3)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient4, mealsItem.strMeasure4)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient5, mealsItem.strMeasure5)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient6, mealsItem.strMeasure6)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient7, mealsItem.strMeasure7)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient8, mealsItem.strMeasure8)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient9, mealsItem.strMeasure9)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient10, mealsItem.strMeasure10)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient11, mealsItem.strMeasure11)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient12, mealsItem.strMeasure12)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient13, mealsItem.strMeasure13)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient14, mealsItem.strMeasure14)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient15, mealsItem.strMeasure15)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient16, mealsItem.strMeasure16)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient17, mealsItem.strMeasure17)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient18, mealsItem.strMeasure18)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient19, mealsItem.strMeasure19)?.let {
            recipes.add(it)
        }

        checkNullEmptyIngredient(mealsItem.strIngredient20, mealsItem.strMeasure20)?.let {
            recipes.add(it)
        }

        return recipes
    }

    private fun checkNullEmptyIngredient(ingredient:String?, measure:String?):Map<String,String>? {
        if (!ingredient.isNullOrEmpty() && !measure.isNullOrEmpty()) {
            return mapOf(ingredient to measure)
        } else return null
    }

    class VMFactory constructor(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM_MealDetail::class.java)) {
                VM_MealDetail(application) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}