package com.jetwiz.admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.jetwiz.admin.base.BaseFragment
import com.jetwiz.admin.databinding.FMealDetailBinding
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.viewmodel.VM_MealDetail
import kotlinx.coroutines.launch

class F_MealDetail:BaseFragment() {

    lateinit var bind: FMealDetailBinding
    private lateinit var viewmodel: VM_MealDetail

    companion object {
        const val BUNDLE_MEALID_INT = "BUNDLE_MEALID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FMealDetailBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        A_Main.instance.addBackButtonListener()

        viewmodel = ViewModelProviders.of(this, VM_MealDetail.VMFactory(requireActivity().application)).get(VM_MealDetail::class.java)
        arguments?.getInt(BUNDLE_MEALID_INT)?.let {
            coroutineScope.launch {
                viewmodel.getDetailMeal(it)
            }
        }

        onObserve()
    }

    private fun onObserve() {
        viewmodel.dataMealDetail.observe(viewLifecycleOwner, Observer {
            if (it.networkState == NetworkState.LOADED) {
                val mealsItem = it.modelMeals.meals!!.first()!!

                Glide.with(this).load(mealsItem.strMealThumb).into(bind.imageView)
                bind.tvRecipe.setText(viewmodel.getRecipes(mealsItem))
                bind.tvCookingInstruction.setText(mealsItem.strInstructions)
            } else {
                DF_Info(it.networkState.msg!!).show(childFragmentManager, null)
            }
        })
    }
}