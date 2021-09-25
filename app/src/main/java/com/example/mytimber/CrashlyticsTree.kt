package com.example.mytimber

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        FirebaseCrashlytics.getInstance().apply {
            setCustomKey(CRASHLYTICS_KEY_PRIORITY, priority)
            setCustomKey(CRASHLYTICS_KEY_TAG, tag ?: "No Tag")
            setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)

            if (t == null)
                this.log(message) //僅在執行recordException或當機後，一併送到後台
            else
                this.recordException(t) //屬於被catch住的例外錯誤，後台會標記為不嚴重
        }
    }

    companion object {
        private val CRASHLYTICS_KEY_PRIORITY = "priority"
        private val CRASHLYTICS_KEY_TAG = "tag"
        private val CRASHLYTICS_KEY_MESSAGE = "message"
    }
}