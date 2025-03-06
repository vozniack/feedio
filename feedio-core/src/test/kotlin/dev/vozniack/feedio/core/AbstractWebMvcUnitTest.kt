package dev.vozniack.feedio.core

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ActiveProfiles(profiles = ["unit-test"])
abstract class AbstractWebMvcUnitTest(context: WebApplicationContext) {

    protected var mockMvc: MockMvc = MockMvcBuilders.webAppContextSetup(context).build()
}
