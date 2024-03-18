package org.example

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

object Bot {
  @Throws(Exception::class)
  @JvmStatic
  fun main(args: Array<String>) {
    val api =
        JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(Listener())
            .setActivity(Activity.playing("Filtering Spam Messages"))
            .build()

    api.awaitReady()
  }

  private val token: String
      get() = System.getenv("BOT_TOKEN")
}
