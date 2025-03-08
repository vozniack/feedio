package dev.vozniack.feedio.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
class FeedioCoreApplication

fun main(args: Array<String>) {
	runApplication<FeedioCoreApplication>(*args)
}
