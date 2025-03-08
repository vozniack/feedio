package dev.vozniack.feedio.core.domain.repository

import dev.vozniack.feedio.core.domain.entity.User
import java.util.UUID
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, UUID>, JpaSpecificationExecutor<User> {

    fun findByEmail(email: String): User?
}
