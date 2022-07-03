package Bot.Moderation.Spam;


import Bot.Moderation.Moderation;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SpamA extends Moderation {

    //this checks the message length of the word if it is over the messagethreshhold amount

    public static int messagelenththreshold = 35;
    private static  boolean isToggled = true;
    private int counter = 0;

    public void setMessagelenththreshold(int messaththreold) {
        messagelenththreshold = messaththreold;
    }


    public static void setIsToggled(){
        isToggled = !isToggled;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if ((e.getAuthor().isBot() || !isToggled))
            return;

        String c;
        this.counter = 0;
        String[] args = e.getMessage().getContentRaw().split(" ");
        for (int i = 0; i < args.length; i++) {

            c = args[i];
            if (c.length() > messagelenththreshold || args.length > 200) {
                if (e.getMessage().isWebhookMessage()) {
                    return;
                }
                // prevents false flags with mentions
                if (e.getMessage().getContentRaw().length() < 50 && e.getMessage().getMentions().getMentions().size() < 5 && e.getMessage().getMentions().getMentions().size() > 0)
                    return;
                // prevents false flags with links
                if (e.getMessage().getContentRaw().contains("www.") || e.getMessage().getContentRaw().contains("https")) {
                    this.counter++;
                } else {
                   flagMessage(e.getAuthor(), e.getGuild(), e.getChannel(), "Spam A", e.getMessage());

                }


            }


        }
    }


}

