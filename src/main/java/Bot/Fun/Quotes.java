package Bot.Fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Quotes extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase("!quote")||args[0].equalsIgnoreCase("!q")){

            try {

                URL url = new URL("https://zenquotes.io/api/random");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                //Check if connect is made
                ;

                // 200 OK


                    StringBuilder informationString = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    //Close the scanner
                    scanner.close();

                    System.out.println(informationString);


                    //JSON simple library Setup with Maven is used to convert strings to JSON
                    JSONParser parse = new JSONParser();
                    JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                    //Get the first JSON object in the JSON array
                    System.out.println(dataObject.get(0));

                    JSONObject countryData = (JSONObject) dataObject.get(0);

                    System.out.println(countryData.get("woeid"));
 EmbedBuilder em = new EmbedBuilder();

                  em.addField((String) countryData.get("a")+": ",(String) countryData.get("q"),true);

event.getChannel().sendMessageEmbeds(em.build()).queue();


            }catch (Exception e){
               event.getChannel().sendMessage("A Error Occurred").queue();
               e.printStackTrace();
            }
                }//for loop ends
        }

    }

