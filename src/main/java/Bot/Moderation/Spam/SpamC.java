package Bot.Moderation.Spam;

import Bot.Moderation.Moderation;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;


public class SpamC extends Moderation {
    private static boolean isToggled = true;
    //this checks to see if the previous message was the same as the last one
    private Message messID;
    private  String preMessage = "";
    private  String une = "";
    private static long[] ignoredChannels;
    private static final int ignoreValue = 0;

    public static void addIgnore(long channelID){
        ignoredChannels[ignoreValue] = channelID;
    }
    public static void setIsToggled(){
        isToggled = !isToggled;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (e.getAuthor().isBot() || !isToggled)
            return;


        //compares if the current channel should be ignored
        //if the current channel is on the list of ignored channels then it will be ignored
        long currentChannel = e.getChannel().getIdLong();
        for (int i = 0;i<ignoredChannels.length;i++){
            if (ignoredChannels[i] == currentChannel)
                return;
        }

        String[] amount = e.getMessage().getContentRaw().split(" ");


        if (amount[0].contains("!") || amount[0].contains("."))
            return;

        if (this.preMessage.equalsIgnoreCase("") && e.getMessage().getContentRaw().replaceAll("\\d", "").equals("") && amount.length < 3) {

            if (this.une.equalsIgnoreCase(e.getMessage().getContentRaw())) {
                flagMessage(e.getAuthor(), e.getGuild(), e.getChannel(), "Duplicate message", e.getMessage());
            }
            return;
        }


        if (this.preMessage.equalsIgnoreCase(e.getMessage().getContentRaw().replaceAll("\\d", ""))) {
            flagMessage(e.getAuthor(), e.getGuild(), e.getChannel(), "Duplicate message", e.getMessage());
            return;
        }
        this.messID = e.getMessage();
        this.une = e.getMessage().toString();
        preMessage = e.getMessage().getContentRaw().replaceAll("\\d", "");
    }
}
