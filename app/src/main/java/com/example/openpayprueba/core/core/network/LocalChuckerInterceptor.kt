package com.example.openpayprueba.core.core.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager

class LocalChuckerInterceptor {

    fun getChucker(ctx: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(ctx, true, RetentionManager.Period.ONE_DAY)
        return ChuckerInterceptor.Builder(ctx)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }
}