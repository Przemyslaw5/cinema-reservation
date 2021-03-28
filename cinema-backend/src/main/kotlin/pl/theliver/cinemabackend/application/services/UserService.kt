package pl.theliver.cinemabackend.application.services

import org.springframework.stereotype.Service
import pl.theliver.cinemabackend.application.repositories.UserRepository
import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl.UserRepositoryJpa
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {

    fun addUser(user: User) = userRepository.saveUser(user)

    fun getAllUsers() = userRepository.getAllUsers()

    fun getUserById(id: String) = userRepository.getUserById(id)

    fun getUserByUsername(username: String) = userRepository.getUserByUsername(username)

    fun createUserIfNotExist(newUser: User): Boolean {
        val user = getUserByUsername(newUser.username)
        if (user == null) {
            addUser(newUser)
        }
        return user == null;
    }
}