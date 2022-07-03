package Bot.Moderation.commands;

import Bot.Moderation.Moderation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.jetbrains.annotations.NotNull;

public class Action extends Moderation {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (!PermissionUtil.checkPermission(e.getMember(), Permission.BAN_MEMBERS)){
            return;
        }
        EmbedBuilder eb = new EmbedBuilder();
        String args[] = e.getMessage().getContentRaw().split(" ");
        Message mes = e.getMessage();

        if (args[0].equalsIgnoreCase("!ban")) {
            if (args[1].equals(null)) {
                e.getChannel().sendMessage("Bans a user").queue();
                return;
            }
                //todo ban members
        }
    }
}
