package Moderation.checks;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static Moderation.Moderation.flag;
import static Moderation.Moderation.flagtype;

public class SpamB extends ListenerAdapter {
    private double amount = 0;




    // this checks if the word is equal to the other 3 words in front of it
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot())
            return;
        this.amount = 0;

        String[] args = e.getMessage().getContentRaw().split(" ");


        int x = 0;
        int flagneeded = Math.round(args.length / 7);
        this.amount = 0;



        while (x != args.length) {
            System.out.println("here");
            if (args[x].equalsIgnoreCase(args[x + 1]) && args[x].equalsIgnoreCase(args[x + 2]) && args[x].equalsIgnoreCase(args[x + 3])){
                this.amount+= Math.round(flagneeded/2);
            }
            if (args[x].equalsIgnoreCase(args[x + 1]) || args[x].equalsIgnoreCase(args[x + 2]) || args[x].equalsIgnoreCase(args[x + 3])) {
                this.amount++;
            }

            if (flagneeded <= this.amount) {
                flag(flagtype, e.getAuthor(), e.getGuild(), e.getChannel(), "Spam B", e.getMessage());
                e.getMessage().delete().queue();
                return;
            }
            x++;

        }
    }
}