package pl.theliver.cinemabackend.application.repositories

import pl.theliver.cinemabackend.domain.User

interface UserRepository {
    fun saveUser(user: User)
    fun getAllUsers(): List<User>
    fun getUserById(id: String): User
    fun getUserByUsername(username: String): User?
}