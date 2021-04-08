package com.jetwiz.admin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jetwiz.admin.base.BaseDialogFragment
import com.jetwiz.admin.databinding.DfInfoBinding

@SuppressLint("ValidFragment")
class DF_Info(var msg:String) : BaseDialogFragment() {

    lateinit var bind: DfInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DfInfoBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.tvMessage.setText(msg)
        bind.btnSubmit.setOnClickListener {
            dismiss()
        }
    }
}