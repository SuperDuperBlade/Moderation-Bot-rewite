package Bot.Moderation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Moderation extends ListenerAdapter {
    public static long messagesFlagged = 0;




//flags the user
    public static void flagMessage(User u, Guild guild, Channel c, String flagname, Message message) {
        System.out.println(guild);
        EmbedBuilder em = new EmbedBuilder()
                .setTitle("Moderation ")
                .setColor(Color.RED).setFooter("Moderation id: " + messagesFlagged + " User id: " + u.getId() + " Meessage ID: " + message.getIdLong())
                .addField("User :", u.getAsMention(), true)
                .addField("Flagtype :", flagname, true)
                .addField("Message", message.getContentRaw(), true);
        messagesFlagged++;

        guild.getTextChannelById(960813503990616084L).sendMessageEmbeds(em.build()).queue();

    }


}
