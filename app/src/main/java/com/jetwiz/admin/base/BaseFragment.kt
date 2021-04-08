package com.jetwiz.admin.base

import androidx.fragment.app.Fragment
import com.jetwiz.admin.utils.U_Coroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseFragment:Fragment() {
    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + U_Coroutine.getErrorHandler()
    )
}