package Bot.Moderation.Config;

import Bot.Moderation.Moderation;
import Bot.Moderation.Spam.SpamD;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class BanWord extends Moderation {
    //todo remove banned words
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");


        if (args[0].equalsIgnoreCase("!BanWord")) {
            if (args[1].equals(null)) {
                EmbedBuilder em = new EmbedBuilder()
                        .setTitle("Moderation")
                        .addField("info: ", "Prevents users form sending messages that contain words of your choose ", true);
                e.getChannel().sendMessageEmbeds(em.build()).queue();
            } else {
                try {
                    SpamD.addBannedWord(args[1]);
                } catch (Exception ex) {
                    e.getChannel().sendMessage("A ERROR OCCURRED").queue();
                }
            }
        }
    }
}
