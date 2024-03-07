package org.example;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    // don't respond on bot
    if (event.getAuthor().isBot()) return;

    Message message = event.getMessage();
    String content = message.getContentRaw();
    // getContentRaw() is an atomic getter
    // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip
    // discord formatting)

    if (content.equals("!ping")) {
      MessageChannelUnion channel = event.getChannel();
      channel
          .sendMessage(replyToPing())
          .queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
    }
  }

  public String replyToPing() {
    return "Pong!";
  }
}
