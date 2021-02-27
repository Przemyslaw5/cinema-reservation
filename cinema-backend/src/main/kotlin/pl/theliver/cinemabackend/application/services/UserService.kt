package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.UserRepositoryJpa

@Service
class UserService(
    private val userRepositoryJpa: UserRepositoryJpa
) {

    fun addUser(user: User) {
        userRepositoryJpa.saveUser(user)
    }

    fun getAllUsers() = userRepositoryJpa.getAllUsers()

    fun getUserById(id: String) = userRepositoryJpa.getUserById(id)

    fun getUserByUsername(username: String) = userRepositoryJpa.getUserByUsername(username)
}