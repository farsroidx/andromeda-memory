@file:Suppress("unused")

package ir.farsroidx.m31

interface MemoryCacheRepository {

    fun <T: Any> put(key: String, value: T?)

    fun <T: Any> get(key: String, alternate: T): T

    fun <T: Any> get(key: String): T?

    fun containsKey(key: String): Boolean

    fun containsKey(vararg keys: String): Boolean

    fun containsKey(keys: List<String>): Boolean

    fun remove(key: String)

    fun remove(vararg keys: String)

    fun remove(keys: List<String>)

    fun clear()

    fun createCount(): Int

    fun putCount(): Int

    fun hitCount(): Int

    fun missCount(): Int

    fun evictionCount(): Int

    fun resize(newCacheSize: Int)

    fun trimToSize(newCacheSize: Int)

    fun getRecords(): Map<String, Any?>
}