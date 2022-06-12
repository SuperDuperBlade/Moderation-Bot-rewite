package Moderation.checks;

import Moderation.flags.Flagtype;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static Moderation.Moderation.flag;
import static Moderation.Moderation.flagtype;

public class SpamA extends ListenerAdapter {



    //this checks the message length of the word if it is over the messagethreshhold amount

    public static int messagelenththreshold = 20;
    private int counter;


    public void setMessagelenththreshold(int messaththreold) {
        messagelenththreshold = messaththreold;
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot())
            return;


        String[] args = e.getMessage().getContentRaw().split(" ");
        if (flagtype == Flagtype.none) {
            return;
        }

        String c;
        this.counter = 0;
        for (int i = 0; i <= args.length; i++) {
                c = args[i];



                    if (c.length() >= messagelenththreshold || args.length > 200) {
                        if (e.getMessage().isWebhookMessage()) {
                            return;
                        }
                        // prevents false flags with mentions
                        if(e.getMessage().getContentRaw().length()<50&&e.getMessage().getMentions().getMentions().size()<5)
                            return;
                        // prevents false flags with links
                        if (e.getMessage().getContentRaw().contains("www.") || e.getMessage().getContentRaw().contains("https")) {
                            this.counter++;
                        }else {
                            e.getMessage().delete().queue();
                            flag(flagtype, e.getAuthor(), e.getGuild(), e.getChannel(), "Spam A", e.getMessage());
                            return;
                        }


                    }
                    if (this.counter > 4) {
                        flag(flagtype, e.getAuthor(), e.getGuild(), e.getChannel(), "Spam A", e.getMessage());
                        return;
                    }

                }
            }


        }

