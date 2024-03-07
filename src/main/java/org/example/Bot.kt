package org.example

import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

object Bot {
  private var config: Dotenv? = null

  @Throws(Exception::class)
  @JvmStatic
  fun main(args: Array<String>) {
    setConfig()
    val token = token

    val api =
        JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(Listener())
            .setActivity(Activity.playing("Filtering Spam Messages"))
            .build()

    api.awaitReady()
  }

  private fun setConfig() {
    config = Dotenv.configure().load()
  }

  private val token: String
    get() = config!!["BOT_TOKEN"]
}
