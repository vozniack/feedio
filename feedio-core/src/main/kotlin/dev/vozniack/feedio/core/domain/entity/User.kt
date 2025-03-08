package dev.vozniack.feedio.core.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id @GeneratedValue
    @Column(nullable = false) val id: UUID = UUID.randomUUID(),

    @Column(nullable = false) var email: String,
    @Column(nullable = true) var password: String? = null,

    @Column(nullable = false) var firstName: String,
    @Column(nullable = false) var lastName: String,
    @Column(nullable = false) var dateOfBirth: LocalDate,
    @Column(nullable = false) var language: String,

    @Column(nullable = false) var active: Boolean = false,

    @Column(nullable = false) val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = true) var updatedAt: LocalDateTime? = null,
    @Column(nullable = true) var verifiedAt: LocalDateTime? = null,
    @Column(nullable = true) var deletedAt: LocalDateTime? = null
)
