package org.example.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ExclamListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        // don't respond on bot
        if (event.author.isBot) return

        val message = event.message
        val content = message.contentRaw

        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip
        // discord formatting)
        if (content == "!ping") {
            val channel = event.channel
            channel
                .sendMessage(replyToPing())
                .queue() // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }
}
