package org.example.commands

import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandListener: ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        super.onSlashCommandInteraction(event)
        val command = event.name

        if (command == "ping") {
            event.reply(replyToPing()).queue()
        } else if (command == "secret") {
            event.reply(replyToSecret()).setEphemeral(true).queue()
        }
    }

    //Below are used only for interactive testing. Not used for actual purpose.
    //Guild Commands (updates when bot started after joining / bot joined after starting)
    override fun onGuildReady(event: GuildReadyEvent) {
        super.onGuildReady(event)
        if (event.guild.idLong == System.getenv("LAB_SERV_ID").toLong())
            event.guild.updateCommands().addCommands(commandList()).queue()
    }
}