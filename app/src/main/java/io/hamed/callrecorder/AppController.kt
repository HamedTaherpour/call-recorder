package io.hamed.callrecorder

import android.app.Application
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump


/**
 * Author: Hamed Taherpour
 * *
 * Created: 10/23/2019
 */
class AppController : Application() {

    companion object {
        lateinit var instance: AppController

        fun getAppContext(): AppController {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        loadFont()
    }

    private fun loadFont() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(resources.getString(R.string.font))
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }
}