@file:Suppress("unused", "UNCHECKED_CAST")

package ir.farsroidx.m31

import androidx.collection.LruCache

internal class MemoryCacheRepositoryImpl (
    maxCacheSize: Int, private val expirationDate: AndromedaExpiration?,
) : MemoryCacheRepository {

    private val memoryCache = LruCache<String, MemoryCacheModel<*>>(maxCacheSize)

    override fun <T: Any> put(key: String, value: T?) {

        if (value != null) {

            memoryCache.put( key, MemoryCacheModel( value, expirationDate.getExpDate() ) )

        } else if (containsKey(key)) { remove(key) }
    }

    override fun <T: Any> get(key: String, alternate: T): T = get(key) ?: alternate

    override fun <T: Any> get(key: String): T? {

        return if (containsKey(key)) {

            val data = memoryCache.get(key) as MemoryCacheModel<T>

            if (data.expireDate.isExpired()) { remove(key); null } else data.value

        } else null
    }

    override fun containsKey(key: String): Boolean {
        return memoryCache[key] != null
    }

    override fun containsKey(vararg keys: String) = containsKey(keys.asList())

    override fun containsKey(keys: List<String>): Boolean {

        var hasKeys = true

        keys.forEach { key -> if (memoryCache[key] == null) { hasKeys = false } }

        return hasKeys
    }

    override fun remove(key: String) {
        memoryCache.remove(key)
    }

    override fun remove(vararg keys: String) {
        remove(keys.toList())
    }

    override fun remove(keys: List<String>) {
        keys.forEach { key -> memoryCache.remove( key ) }
    }

    override fun clear() = memoryCache.evictAll()

    override fun createCount(): Int = memoryCache.createCount()

    override fun putCount(): Int = memoryCache.putCount()

    override fun hitCount(): Int = memoryCache.hitCount()

    override fun missCount(): Int = memoryCache.missCount()

    override fun evictionCount(): Int = memoryCache.evictionCount()

    override fun resize(newCacheSize: Int) = memoryCache.resize(newCacheSize)

    override fun trimToSize(newCacheSize: Int) = memoryCache.trimToSize(newCacheSize)

    override fun getRecords(): Map<String, Any?> = memoryCache.snapshot().mapValues { it.value.value }

}