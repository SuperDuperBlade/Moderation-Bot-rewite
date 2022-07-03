package Bot.Moderation.Config;

import Bot.Moderation.Moderation;
import Bot.Moderation.Spam.SpamC;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ignore extends Moderation {

    //todo implement a way to remove a channel form the ignored array
     @Override
     public void onMessageReceived(MessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");


        if (args[0].equalsIgnoreCase("!ignore")){
             if (args[1].equals(null)){
                  EmbedBuilder em = new EmbedBuilder()
                          .setTitle("Moderation")
                          .addField("info: ","the channel id will be ignored by spam c",true);
                          e.getChannel().sendMessageEmbeds(em.build()).queue();
             }else {
                  try{
                       long channelID = Long.parseLong(args[1]);
                       SpamC.addIgnore(channelID);
                  }catch (Exception ex){
                       e.getChannel().sendMessage("you need to provide a interger").queue();
                  }
             }

        }



     }




     }
