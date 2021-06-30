package dj.dynamic.card

import android.app.Application

class DynamicCardApplication : Application() {

    companion object {
        private lateinit var instance: DynamicCardApplication

        fun getInstance(): DynamicCardApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}