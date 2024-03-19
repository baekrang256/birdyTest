package org.example.commands

import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands

fun commandList(): List<CommandData> {
    val commandData = ArrayList<CommandData>()
    commandData.add(Commands.slash("ping", "Ping the bot to see if it's alive"))
    commandData.add(Commands.slash("secret", "get a secret reply from the bot"))
    return commandData
}