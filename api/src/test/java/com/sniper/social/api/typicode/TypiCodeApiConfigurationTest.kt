package com.sniper.social.api.typicode

import org.junit.Assert.*
import org.junit.Test

class TypiCodeApiConfigurationTest{

    @Test
    fun `test TypiCodeAPI configuration has correct base URL for debug builds`() {
        //given
        val tested = TypiCodeApiConfiguration(true)
        val expected = "http://jsonplaceholder.typicode.com/"
        //when
        val result = tested.baseURL
        //test
        assertEquals(expected, result)
    }
    @Test
    fun `test TypiCodeAPI configuration has correct base URL for production builds`() {
        //given
        val tested = TypiCodeApiConfiguration(false)
        val expected = "http://jsonplaceholder.typicode.com/"
        //when
        val result = tested.baseURL
        //test
        assertEquals(expected, result)
    }
}
