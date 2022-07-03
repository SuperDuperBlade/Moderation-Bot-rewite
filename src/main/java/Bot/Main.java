package Bot;

import Bot.Fun.Hello;
import Bot.Fun.Quotes;
import Bot.Moderation.Config.Ignore;
import Bot.Moderation.Config.Toggle;
import Bot.Moderation.Moderation;
import Bot.Moderation.Spam.SpamA;
import Bot.Moderation.Spam.SpamB;
import Bot.Moderation.Spam.SpamC;
import Bot.Moderation.commands.Info;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter { ;
    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.createDefault("")
            //  .addEventListeners(new Moderation())
                .addEventListeners(new SpamA())
                .addEventListeners(new SpamB())
                .addEventListeners(new SpamC())
                .addEventListeners(new Ignore())
                .addEventListeners(new Toggle())
                .addEventListeners(new Hello())
                .addEventListeners(new Quotes())
                .addEventListeners(new Info())
                .build();

        jda.awaitReady();

        Info.startTime = System.currentTimeMillis();


    }

}
