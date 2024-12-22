package com.example.slambook_bureros

import java.io.Serializable

data class UserInfo (
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val hobby: String,
    val favFood: String,
    val favMovie: String,
    val favMusic: String
) : Serializable
