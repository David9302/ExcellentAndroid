package com.it.excellent.domain.message

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.domain.message.Result

class DrawerCoordinateManager private constructor(): DefaultLifecycleObserver {

    companion object {
        private val S_HELPER = DrawerCoordinateManager()
        private val mTagOfSecondaryPages = arrayListOf<String>()
        private val mEnableSwipeDrawer = MutableResult<Boolean>()
        fun getInstance() = S_HELPER
    }

    private fun isNoneSecondaryPage() = mTagOfSecondaryPages.isEmpty()

    fun isEnableSwipeDrawer(): Result<Boolean> = mEnableSwipeDrawer

    fun requestToUpdateDawerMode(pageOpened: Boolean, pageName: String) {
        if (pageOpened) {
            mTagOfSecondaryPages.add(pageName)
        } else {
            mTagOfSecondaryPages.remove(pageName)
        }
        mEnableSwipeDrawer.value = isNoneSecondaryPage()
    }

    @Override
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mTagOfSecondaryPages.add(owner.javaClass.simpleName)
        mEnableSwipeDrawer.value = isNoneSecondaryPage()
    }

    @Override
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        mTagOfSecondaryPages.remove(owner.javaClass.simpleName)
        mEnableSwipeDrawer.value = isNoneSecondaryPage()
    }

}