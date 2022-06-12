package Moderation.checks;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static Moderation.Moderation.flag;
import static Moderation.Moderation.flagtype;

public class SpamD extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
if(e.getAuthor().isBot())
    return;
        String message = e.getMessage().getContentRaw().toLowerCase();

        if (message.contains("https")||message.contains("www.")){
            if (message.contains("dis")) {
                if (message.contains("nitro")||message.contains("steam")) {
                    flag(flagtype, e.getAuthor(), e.getGuild(), e.getChannel(), "Spam D", e.getMessage());
                    e.getMessage().delete().queue();
                }
            }
        }

    }
}
