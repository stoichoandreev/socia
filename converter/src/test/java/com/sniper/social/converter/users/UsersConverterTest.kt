package com.sniper.social.converter.users

import com.sniper.social.api.typicode.models.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersConverterTest{

    private val tested = UsersConverter()

    @Test
    fun `test UsersConverter converts users api response to user view model list`() {
        //given
        val user1 = User()
        val user2 = User()
        with(user1) {
            id = 1
            email = "email@em.com"
            name = "Name"
        }
        with(user2) {
            id = 2
            email = "mail@em.com"
            name = "SecondName"
        }
        val usersList = listOf(user1, user2)
        //when
        val result = tested.map(usersList)
        //test
        assertEquals(2, result.size)

        assertEquals(1, result[0].id)
        assertEquals("Name", result[0].name)

        assertEquals(2, result[1].id)
        assertEquals("SecondName", result[1].name)
    }

    @Test
    fun `test UsersConverter converts empty users api response to empty user view model list`() {
        //given
        val userList = listOf<User>()
        //when
        val result = tested.map(userList)
        //test
        assertEquals(0, result.size)
    }
}