package com.dranoer.shared.cache

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CacheTest {

    @Test
    fun `entry within TTL is not stale`() {
        val now = 1_000_000L
        val entry = CacheEntry(value = "x", savedAtMillis = now)

        assertFalse(entry.isStale(ttlMs = 10_000L, now = now + 5_000L))
    }

    @Test
    fun `entry after TTL is stale`() {
        val now = 1_000_000L
        val entry = CacheEntry(value = "x", savedAtMillis = now)

        assertTrue(entry.isStale(ttlMs = 10_000L, now = now + 11_000L))
    }
}