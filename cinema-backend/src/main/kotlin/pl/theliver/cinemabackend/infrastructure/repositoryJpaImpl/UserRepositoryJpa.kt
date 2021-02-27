package pl.theliver.cinemabackend.infrastructure.repositoryJpaImpl

import org.springframework.stereotype.Component
import pl.theliver.cinemabackend.application.repositories.UserRepository
import pl.theliver.cinemabackend.domain.User
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.RateCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.ReservationCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa.UserCrudRepositoryJpa
import pl.theliver.cinemabackend.infrastructure.model.UserEntity

@Component
class UserRepositoryJpa(
        private val userCrudRepositoryJpa: UserCrudRepositoryJpa,
        private val reservationCrudRepositoryJpa: ReservationCrudRepositoryJpa,
        private val rateCrudRepositoryJpa: RateCrudRepositoryJpa
) : UserRepository {
    override fun saveUser(user: User) {
        userCrudRepositoryJpa.save(UserEntity.fromDomain(user, reservationCrudRepositoryJpa, rateCrudRepositoryJpa))
    }

    override fun getAllUsers() = userCrudRepositoryJpa.findAll().map { it.toDomain() }

    override fun getUserById(id: String) = userCrudRepositoryJpa.findById(id).orElse(null).toDomain()

    override fun getUserByUsername(username: String): User? {
        val userEntity = userCrudRepositoryJpa.findUserByUsername(username)
        if (userEntity.isPresent) {
            return userEntity.get().toDomain()
        }
        return null
    }
}