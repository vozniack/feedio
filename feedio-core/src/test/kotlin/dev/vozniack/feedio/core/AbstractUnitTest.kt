package dev.vozniack.feedio.core

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(profiles = ["unit-test"])
abstract class AbstractUnitTest
