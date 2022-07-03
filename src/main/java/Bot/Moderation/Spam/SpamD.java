package Bot.Moderation.Spam;

import Bot.Moderation.Moderation;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class SpamD extends Moderation {

    private static String[] bannedWords;
     private static int  BannedWordcount = 0;
    public static void addBannedWord(String bannedWord){
        bannedWords[BannedWordcount] = bannedWord;
        BannedWordcount++;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        for (int i = 0; i < bannedWords.length; i++) {
            if (bannedWords[i].equalsIgnoreCase(message)) {
                flagMessage(e.getAuthor(), e.getGuild(), e.getChannel(), "Spam B (banned word)", e.getMessage());
                 return;
            }
        }
    }
}
