package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
  private static Dotenv config;

  public static void main(String[] args) throws Exception {
    setConfig();
    String token = getToken();

    JDA api =
        JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(new Listener())
            .setActivity(Activity.playing("Filtering Spam Messages"))
            .build();

    api.awaitReady();
  }

  private static void setConfig() {
    config = Dotenv.configure().load();
  }

  public static Dotenv getConfig() {
    return config;
  }

  public static String getToken() {
    return config.get("BOT_TOKEN");
  }
}
