package Moderation;

import Moderation.flags.Flagtype;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;


public class Moderation {
    public static void setFlagtype(Flagtype flagtype) {
        Moderation.flagtype = flagtype;
    }

    //todo make it so spam A is run before spam B
    //todo add more actions for different mode
    public static Flagtype flagtype = Flagtype.normal;
    private int threshold;
    private boolean trustfactor;
    private boolean ignoreprotected;
    public static double messagesFlagged = 0;


    public Moderation(Flagtype flag, int threshold, boolean trustfactor) {
    }


    public static void flag(Flagtype f, User u, Guild guild, Channel c, String flagname, Message message) {
        if (f == Flagtype.none) {
            return;
        }
        if (f == Flagtype.Action) {


        }
        if (f == Flagtype.normal) {
            String l = u.getAsMention();
            EmbedBuilder em = new EmbedBuilder()
                    .setTitle("Moderation ")
                    .setColor(Color.RED).setFooter("Moderation id: "+String.valueOf(messagesFlagged++))
                    .addField("User :", u.getAsMention(), true)
                    .addField("Flagtype :", flagname, true)
                    .addField("Message", message.getContentRaw(), true);

            guild.getTextChannelById(c.getId()).sendMessageEmbeds(em.build()).queue();


        }
        if (f == Flagtype.Silent) {

        }
    }

    public void Ban(User user, Guild guild, int days) {
        guild.ban(user, days);

    }

    public void Kick(User user, Guild guild) {
        guild.kick((Member) user);
    }

    public void mute(Guild guild, User user) {
        guild.mute((Member) user, true);
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean isTrustfactor() {
        return trustfactor;
    }

    public boolean isIgnoreprotected() {
        return ignoreprotected;
    }
}
