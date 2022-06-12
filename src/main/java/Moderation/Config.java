package Moderation;
import com.sun.tools.javac.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static Moderation.Moderation.messagesFlagged;
import static Moderation.checks.SpamA.messagelenththreshold;

public class Config extends ListenerAdapter {
    //todo make each algorithm toggleable
    private double messagess = 0;
    public static int min = 0;
    public static long starttime;



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        this.messagess++;
        //     if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {//return;
        //   }


        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase("!stats")){
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            double news = Double.parseDouble(df.format((messagesFlagged *100)/this.messagess));
            EmbedBuilder embedBuilder = new EmbedBuilder();

            Main main = new Main();
            long milliseconds  = System.currentTimeMillis()-Config.starttime;
            int seconds = (int) (milliseconds / 1000) % 60 ;
            int minutes = (int) ((milliseconds / (1000*60)) % 60);
            int hours   = (int) ((milliseconds / (1000*60*60)) % 24);

            embedBuilder.setTitle("Stats")
                    .addField("Messages Monitored: ", String.valueOf(Math.round(this.messagess)),true)
                    .addField("Messages flaged: ",Math.round(messagesFlagged) + " (" +news +"%)",true)
                            .setFooter("Running for "+hours+" hours "+minutes+" min "+seconds+" seconds");
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
                              }
        if (args[0].equalsIgnoreCase("!info")) {
            EmbedBuilder em = new EmbedBuilder()
                    .setTitle("Info")
                    .addField("Spam A :", "checks the length of the word", true)
                    .addField("Spam B :", "checks if the words in the sentence are similar", true)
                    .addField("Spam C :", "checks if the last sentance is the same as the current one ", true)
                    .addField("Spam D : ", "trys to see if the user has sent a nitro scam link", true);
            event.getChannel().sendMessageEmbeds(em.build()).queue();

        }
        if (args[0].equalsIgnoreCase("!config") && args[1].equalsIgnoreCase("amount")) {
            try {
                int a = Integer.parseInt(args[2]);
                messagelenththreshold = a;
                event.getChannel().sendMessage("threshold was set to " + a).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}