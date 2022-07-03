package Bot.Moderation.Config;

import Bot.Moderation.Moderation;
import Bot.Moderation.Spam.SpamA;
import Bot.Moderation.Spam.SpamB;
import Bot.Moderation.Spam.SpamC;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class Toggle extends Moderation {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase("!toggle")){
            if (args[1].equalsIgnoreCase(null)) {
                e.getChannel().sendMessage("you need to provide a check to toggle to view the list of checks do !checks").queue();
            }else {

                switch (args[1].toLowerCase()){
                    case "spama":
                        SpamA.setIsToggled();
                        break;
                    case "spamb":
                        SpamB.setIsToggled();
                        break;
                    case "spamc":
                        SpamC.setIsToggled();
                        break;
                    default:
                        e.getChannel().sendMessage("you need to provide a valid  check to toggle. To view the list of checks do !checks").queue();
                }


            }

        }




    }
}
