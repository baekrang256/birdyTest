package org.example

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import org.example.commands.CommandListener
import org.example.commands.ExclamListener
import org.example.commands.commandList

object Bot {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val api =
            JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(ExclamListener(), CommandListener())
                .setActivity(Activity.playing("Filtering Spam Messages"))
                .build()

        api.updateCommands().addCommands(commandList()).queue()
        api.awaitReady()
    }

    private val token: String
        get() = System.getenv("BOT_TOKEN")
}
