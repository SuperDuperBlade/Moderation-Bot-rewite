package Events;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Message extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e){
if (e.getAuthor().isBot())
    return;


        String[] args = e.getMessage().getContentRaw().split(" ");


if (args[0].equalsIgnoreCase("!ping")){
    long time = System.currentTimeMillis();
    MessageChannel channel = e.getChannel();
    channel.sendMessage("Pong!").queue(response  -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());

}
        if (args[0].equalsIgnoreCase("hello")||args[0].equalsIgnoreCase("hi")){
            e.getChannel().sendMessage("Hello!").queue();

        }
        if(args[0].equalsIgnoreCase("help")){
            e.getChannel().sendMessage("help me daddy owo").queue();
        }



    }


}
