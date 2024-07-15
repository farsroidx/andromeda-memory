@file:Suppress("unused")

package ir.farsroidx.m31

// TODO: MemoryCache =============================================================== MemoryCache ===

val Andromeda.memoryCache: MemoryCacheRepository by andromedaInjector()

fun andromedaMemoryCacheProvider(
    maxCacheSize: Int, expirationDate: AndromedaExpiration? = null
): AndromedaProvider {

    if (maxCacheSize <= 0) {
        throw IllegalArgumentException("The value of maxCacheSize cannot be zero or less.")
    }

    if (expirationDate != null && expirationDate.value <= 0) {
        throw IllegalArgumentException("The value of 'AndromedaExpiration.value' cannot be zero or less.")
    }

    return MemoryCacheProvider(maxCacheSize, expirationDate)
}