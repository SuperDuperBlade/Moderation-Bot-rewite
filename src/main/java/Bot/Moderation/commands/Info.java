package Bot.Moderation.commands;

import Bot.Moderation.Moderation;
import com.sun.tools.javac.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;



public class Info extends Moderation {

    public static long startTime;
    private long messages = 0;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
messages++;
        EmbedBuilder eb = new EmbedBuilder();
        String args[] = e.getMessage().getContentRaw().split(" ");
        Message mes = e.getMessage();
      if(args[0].equalsIgnoreCase("!info")){

          EmbedBuilder em = new EmbedBuilder()
                  .setTitle("Info")
                  .addField("Spam A :", "checks the length of the word", true)
                  .addField("Spam B :", "checks if the words in the sentence are similar", true)
                  .addField("Spam C :", "checks if the last sentance is the same as the current one ", true)
                  .addField("Spam D : ", "trys to see if the user has sent a nitro scam link", true);
          e.getChannel().sendMessageEmbeds(em.build()).queue();

        }

      if (args[0].equalsIgnoreCase("!stats")){
          DecimalFormat df = new DecimalFormat();
          df.setMaximumFractionDigits(2);
          double news = Double.parseDouble(df.format((messagesFlagged *100)/messages));
          EmbedBuilder embedBuilder = new EmbedBuilder();

          Main main = new Main();
          long milliseconds  = System.currentTimeMillis()-startTime;
          int seconds = (int) (milliseconds / 1000) % 60 ;
          int minutes = (int) ((milliseconds / (1000*60)) % 60);
          int hours   = (int) ((milliseconds / (1000*60*60)) % 24);

          embedBuilder.setTitle("Stats")
                  .addField("Messages Monitored: ", String.valueOf(Math.round(messages)),true)
                  .addField("Messages flaged: ",Math.round(messagesFlagged) + " (" +news +"%)",true)
                  .setFooter("Running for "+hours+" hours "+minutes+" min "+seconds+" seconds");
          e.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
      }
    }



}
