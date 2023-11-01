package me.time

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.ConfigurableApplicationContext

@EnableCaching
@SpringBootApplication
open class SpringMain {
    private lateinit var springContext: ConfigurableApplicationContext

    fun initializeSpring() {
        try {
            springContext = SpringApplication.run(SpringMain::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun shutdownSpring() {
        springContext.close()
    }

    companion object {

        val springMain by lazy { SpringMain() }

        fun initialize() {
            springMain.initializeSpring()
        }

        @JvmStatic
        fun shutdown() {
            springMain.shutdownSpring()
        }
    }
}