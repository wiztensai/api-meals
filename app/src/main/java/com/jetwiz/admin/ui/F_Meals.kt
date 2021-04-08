package com.jetwiz.admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetwiz.admin.R
import com.jetwiz.admin.adapter.AdapterMeals
import com.jetwiz.admin.base.BaseFragment
import com.jetwiz.admin.databinding.FMealsBinding
import com.jetwiz.admin.model.MealsItem
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.viewmodel.VM_Meals
import kotlinx.coroutines.launch

class F_Meals:BaseFragment() {

    lateinit var bind: FMealsBinding
    private lateinit var viewmodel: VM_Meals

    companion object {
        const val BUNDLE_CATEG = "category"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FMealsBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        A_Main.instance.addBackButtonListener()

        viewmodel = ViewModelProviders.of(this, VM_Meals.VMFactory(requireActivity().application)).get(VM_Meals::class.java)
        bind.rvCategories.layoutManager = LinearLayoutManager(requireActivity())

        coroutineScope.launch {
            arguments?.getString(BUNDLE_CATEG, "").let {
                if (it != null && it.isNotEmpty()) {
                    viewmodel.getMealsByCategory(it)
                }
            }
        }

        onObserve()
    }

    private fun onObserve() {
        viewmodel.dataMeals.observe(viewLifecycleOwner, Observer {
            if (it.networkState == NetworkState.LOADED) {
                bind.rvCategories.adapter = AdapterMeals(it.modelMeals, { mealsItem: MealsItem ->
                    coroutineScope.launch {
                        val bundle = Bundle()
                        bundle.putInt(F_MealDetail.BUNDLE_MEALID_INT, mealsItem.idMeal)
                        findNavController().navigate(R.id.nav_f_MealDetail, bundle)
                    }
                })
            } else {
                DF_Info(it.networkState.msg!!).show(childFragmentManager, null)
            }
        })
    }
}