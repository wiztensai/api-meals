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
import com.jetwiz.admin.adapter.AdapterCategories
import com.jetwiz.admin.base.BaseFragment
import com.jetwiz.admin.databinding.FCategoriesBinding
import com.jetwiz.admin.model.CategoriesItem
import com.jetwiz.admin.utils.NetworkState
import com.jetwiz.admin.viewmodel.VM_Categories
import com.jetwiz.admin.viewmodel.VM_Meals
import kotlinx.coroutines.launch

class F_Categories:BaseFragment() {

    lateinit var bind: FCategoriesBinding
    private lateinit var viewmodel: VM_Categories

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FCategoriesBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        A_Main.instance.addBackButtonListener()

        viewmodel = ViewModelProviders.of(this, VM_Categories.VMFactory(requireActivity().application)).get(VM_Categories::class.java)
        bind.rvCategories.layoutManager = LinearLayoutManager(requireActivity())

        coroutineScope.launch {
            viewmodel.getCategories()
        }

        onObserve()
    }

    private fun onObserve() {
        viewmodel.dataCategory.observe(viewLifecycleOwner, Observer {
            if (it.networkState == NetworkState.LOADED) {
                bind.rvCategories.adapter = AdapterCategories(it.modelCategories, { categoriesItem: CategoriesItem ->
                    val bundle = Bundle()
                    bundle.putString(F_Meals.BUNDLE_CATEG, categoriesItem.strCategory)
                    findNavController().navigate(R.id.nav_f_Meals, bundle)
                })
            } else {
                DF_Info(it.networkState.msg!!).show(childFragmentManager, null)
            }
        })
    }
}