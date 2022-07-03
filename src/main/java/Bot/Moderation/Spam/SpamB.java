package Bot.Moderation.Spam;

import Bot.Moderation.Moderation;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SpamB extends Moderation {

    private static final int amountNeeded = 12;
    private static boolean isToggled = true;



    public static void setIsToggled(){
        isToggled = !isToggled;
    }

    // this checks if the word is equal to the other 3 words in front of it
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot() || !isToggled)
            return;


        String[] args = e.getMessage().getContentRaw().split(" ");

        if (amountNeeded > args.length)
            return;


        int flagneeded = Math.round(args.length / 2);
        int amount = 0;


        for (int i = 0; i < args.length; i++) {
            String l = args[i];

            if (l.equalsIgnoreCase(args[i + 1]) && l.equalsIgnoreCase(args[i + 2]) && l.equalsIgnoreCase(args[i + 3])) {
                amount += Math.round(flagneeded / 3);
            }
            if (l.equalsIgnoreCase(args[i + 1]) || l.equalsIgnoreCase(args[i + 2]) || l.equalsIgnoreCase(args[i + 3])) {
                amount++;
            }

            if (amount >= flagneeded) {
                flagMessage(e.getAuthor(), e.getGuild(), e.getChannel(), "Spam B", e.getMessage());
                return;
            }


        }


    }
}