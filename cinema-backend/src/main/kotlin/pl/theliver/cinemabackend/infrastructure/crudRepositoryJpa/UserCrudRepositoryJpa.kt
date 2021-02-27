package pl.theliver.cinemabackend.infrastructure.crudRepositoryJpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.theliver.cinemabackend.infrastructure.model.UserEntity
import java.util.*

@Repository
interface UserCrudRepositoryJpa : CrudRepository<UserEntity, String> {
    fun findUserByUsername(username: String): Optional<UserEntity>
}