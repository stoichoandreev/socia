package com.sniper.social.api.okhttp

import org.junit.Test

import org.junit.Assert.*

class DefaultOkHttpConfigTest {

    private val tested = DefaultOkHttpConfig()

    @Test
    fun `test DefaultOkHttpConfig has correct connectTimeout`() {
        //when
        val result = tested.connectTimeout
        //test
        assertEquals(result , DefaultOkHttpConfig.CONNECTION_TIMEOUT)
    }

    @Test
    fun `test DefaultOkHttpConfig has correct readTimeout`() {
        //when
        val result = tested.readTimeout
        //test
        assertEquals(result , DefaultOkHttpConfig.READ_TIMEOUT)
    }

    @Test
    fun `test DefaultOkHttpConfig has correct writeTimeout`() {
        //when
        val result = tested.writeTimeout
        //test
        assertEquals(result , DefaultOkHttpConfig.WRITE_TIMEOUT)
    }

}
