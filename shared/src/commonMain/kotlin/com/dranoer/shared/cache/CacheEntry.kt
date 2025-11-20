package com.dranoer.shared.cache

import com.dranoer.shared.currentTimeMillis

const val DEFAULT_TTL_MS: Long = 60 * 1000L

data class CacheEntry(
    val value: Any,
    val savedAtMillis: Long
) {
    fun isStale(
        ttlMs: Long = DEFAULT_TTL_MS,
        now: Long = currentTimeMillis()
    ): Boolean = now - savedAtMillis > ttlMs
}