package com.jetwiz.admin.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.DialogFragment
import com.jetwiz.admin.utils.U_Coroutine
import com.jetwiz.admin.utils.U_DpPxConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseDialogFragment: DialogFragment() {
    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + U_Coroutine.getErrorHandler()
    )

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.getWindow()!!.setLayout(Constraints.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.WRAP_CONTENT) // full width dialog
            val back = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(back, U_DpPxConverter.dpToPixel(16, it.context))
            it.window!!.setBackgroundDrawable(inset)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    }
}