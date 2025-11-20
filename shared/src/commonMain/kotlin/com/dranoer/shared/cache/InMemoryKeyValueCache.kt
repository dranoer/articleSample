package com.dranoer.shared.cache

import com.dranoer.shared.currentTimeMillis

/**
 * A basic, non-persistent in-memory cache
 */
class InMemoryKeyValueCache : KeyValueCache {

    private val store = mutableMapOf<String, CacheEntry>()

    override suspend fun put(key: String, value: Any) {
        store[key] = CacheEntry(
            value = value,
            savedAtMillis = currentTimeMillis()
        )
    }

    override suspend fun get(key: String): CacheEntry? = store[key]
}