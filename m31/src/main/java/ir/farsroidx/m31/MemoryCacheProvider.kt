@file:Suppress("unused")

package ir.farsroidx.m31

import org.koin.core.module.Module
import org.koin.dsl.module

internal class MemoryCacheProvider(
    private val maxCacheSize: Int,
    private val expirationDate: AndromedaExpiration?
) : AndromedaProvider {

    override fun getKoinModule(): Module = module {

        single<MemoryCacheRepository> {
            MemoryCacheRepositoryImpl(maxCacheSize, expirationDate)
        }
    }
}