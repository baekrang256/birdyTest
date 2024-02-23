package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
  private static Dotenv config;

  public static void main(String[] args) throws Exception {
    config = Dotenv.configure().load();
    String token = config.get("BOT_TOKEN");

    JDA api =
        JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(new Listener())
            .setActivity(Activity.playing("Filtering Spam Messages"))
            .build();

    api.awaitReady();
  }

  public Dotenv getConfig() {
    return config;
  }
}
