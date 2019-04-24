package com.umbrellait.cache

import org.junit.Test

class ExampleUnitTest {

    private val dataProvider = CityDataProvider()

    @Test
    fun cacheSimple() {
        val cache = Cache(1_000, dataProvider)
        val dataFromCache = cache.get()
        println(dataFromCache)
        Thread.sleep(1_100)
        val updatedDataFromCache = cache.get()
        println(updatedDataFromCache)
        assert(dataFromCache != updatedDataFromCache)
    }

    @Test
    fun cacheWithForceInvalidate() {
        val cache = Cache(1_000, dataProvider)
        val dataFromCache = cache.get()
        println(dataFromCache)
        val updatedDataFromCache = cache.get(true)
        println(updatedDataFromCache)
        assert(dataFromCache != updatedDataFromCache)
    }

    @Test
    fun checkTimestamps() {
        val cache = Cache(1_000, dataProvider)
        cache.get()
        val dataFromCacheTimestamp = cache.lastUpdateTimestamp
        println(dataFromCacheTimestamp)
        Thread.sleep(1_100)
        cache.get()
        val updatedDataFromCacheTimestamp = cache.lastUpdateTimestamp
        println(updatedDataFromCacheTimestamp)
        assert(dataFromCacheTimestamp != updatedDataFromCacheTimestamp)
    }


}
