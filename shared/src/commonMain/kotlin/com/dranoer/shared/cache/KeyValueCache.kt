package com.dranoer.shared.cache

const val DEFAULT_TTL_MS: Long = 60 * 1000L

expect fun currentTimeMillis(): Long

data class CacheEntry(
    val value: Any,
    val savedAtMillis: Long
) {
    fun isStale(
        ttlMs: Long = DEFAULT_TTL_MS,
        now: Long = currentTimeMillis()
    ): Boolean = now - savedAtMillis > ttlMs
}

interface KeyValueCache {
    suspend fun put(key: String, value: Any)
    suspend fun get(key: String): CacheEntry?
}

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