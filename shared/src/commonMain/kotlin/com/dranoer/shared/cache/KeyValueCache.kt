package com.dranoer.shared.cache

interface KeyValueCache {
    suspend fun put(key: String, value: Any)
    suspend fun get(key: String): CacheEntry?
}