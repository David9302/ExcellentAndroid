package com.it.excellent.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import java.util.LinkedList

object Utils {

    private const val PERMISSION_ACTIVITY_CLASS_NAME = "com.blankj.utilcode.util.PermissionUtils\$PermissionActivity"

    private var sApplication: Application? = null

    private val ACTIVITY_LIFECYCLE = ActivityLifecycleImpl()

    init {
        try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val currentActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread")
            val currentActivityThread = currentActivityThreadMethod.invoke(null)
            val getApplicationMethod = activityThreadClass.getMethod("getApplication")
            sApplication = getApplicationMethod.invoke(currentActivityThread) as Application
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public fun init(context: Context?) {
        if (context == null) {
            init(getApplicationByReflect())
            return
        }
        init(context.applicationContext as Application)
    }

    fun init(app: Application?) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = getApplicationByReflect()
            } else {
                sApplication = app
            }
            sApplication!!.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE)
        } else {
            if (app != null && app.javaClass != sApplication!!.javaClass) {
                sApplication!!.unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE)
            }
        }
    }

    @JvmStatic
    fun getApp(): Application {
        if (sApplication != null) {
            return sApplication!!
        }

        val app = getApplicationByReflect()
        init(app)
        return app
    }


    @SuppressLint("PrivateApi")
    private fun getApplicationByReflect(): Application {
        try {
            val activityThread = Class.forName("android.app.ActivityThread")
            val thread = activityThread.getMethod("currentActivityThread").invoke(null)
            val app = activityThread.getMethod("getApplication").invoke(thread)?: throw NullPointerException("u should init first")
            return app as Application
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw NullPointerException("u should init first")
    }

    interface OnAppStatusChangedListener {
        fun onForeground()
        fun onBackground()
    }

    interface OnActivityDestroyedListener {
        fun onActivityDestroyed(activity: Activity)
    }

    private class ActivityLifecycleImpl : Application.ActivityLifecycleCallbacks {

        val mActivityList = LinkedList<Activity>()
        val mStatusListenerMap = mutableMapOf<Any, OnAppStatusChangedListener>()
        val mDestroyedListenerMap = mutableMapOf<Activity, MutableSet<OnActivityDestroyedListener>>()

        private var mForegroundCount = 0
        private var mConfigCount = 0
        private var mIsBackground = false

        private fun setTopActivity(activity: Activity) {
            if (PERMISSION_ACTIVITY_CLASS_NAME.equals(activity.javaClass.name)) {
                return
            }
            if (mActivityList.contains(activity)) {
                if (!mActivityList.last.equals(activity)) {
                    mActivityList.remove(activity)
                    mActivityList.addLast(activity)
                }
            } else {
                mActivityList.addLast(activity)
            }
        }

        private fun fixSoftInputLeaks(activity: Activity?) {
            if (activity == null) {
                return
            }

            val imm: InputMethodManager? = Utils.getApp().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?: null
            if (imm == null) return

            val leakViews = arrayOf("mLastSrvView", "mCurRootView", "mServedView", "mNextServedView")
            for (leakView in leakViews) {
                val leakViewField = InputMethodManager::class.java.getDeclaredField(leakView)
                if (leakViewField == null) {
                    continue
                }


            }
        }

        fun addOnAppStatusChangedListener(
            `object`: Any?,
            listener: OnAppStatusChangedListener?
        ) {
            mStatusListenerMap[`object`!!] = listener!!
        }

        fun removeOnAppStatusChangedListener(`object`: Any?) {
            mStatusListenerMap.remove(`object`)
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            TODO("Not yet implemented")
        }

        override fun onActivityStarted(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityResumed(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityPaused(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivityStopped(activity: Activity) {
            TODO("Not yet implemented")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            TODO("Not yet implemented")
        }

        override fun onActivityDestroyed(activity: Activity) {
            TODO("Not yet implemented")
        }

    }
}