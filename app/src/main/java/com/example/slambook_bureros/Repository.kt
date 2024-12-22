package com.example.slambook_bureros

object Repository {

    private val userInfo = mutableListOf<UserInfo>()

    @Synchronized
    fun add(slambook: UserInfo) {
        userInfo.add(slambook)
    }


    @Synchronized
    fun get(): List<UserInfo> = userInfo.toList()

    @Synchronized
    fun delete(slambook: UserInfo) {
        if (userInfo.contains(slambook)) {
            userInfo.remove(slambook)
        } else {
            throw IllegalArgumentException("The item to be deleted does not exist in the repository.")
        }
    }
}
