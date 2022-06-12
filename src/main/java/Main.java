import Events.Message;
import Moderation.Config;
import Moderation.checks.SpamA;
import Moderation.checks.SpamB;
import Moderation.checks.SpamC;
import Moderation.checks.SpamD;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


public class Main extends ListenerAdapter {


    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("")

                .addEventListeners(new Message())
                .addEventListeners(new Config())
                .addEventListeners(new SpamA())
                .addEventListeners(new SpamB())
                .addEventListeners(new SpamC())
                .addEventListeners(new SpamD())
                .build();

       Config.starttime = System.currentTimeMillis();



    }




}
