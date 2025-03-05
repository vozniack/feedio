package dev.vozniack.feedio.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeedioCoreApplication

fun main(args: Array<String>) {
	runApplication<FeedioCoreApplication>(*args)
}
