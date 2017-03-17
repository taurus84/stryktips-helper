import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by davidtran on 2017-03-13.
 */
public class SvenskaSpelData {

    private JSONArray draws = null;
    private final int HOME = 0, DRAW = 1, AWAY = 2;
    private String[] matches = new String[13];
    private String[][] procents = new String[13][3];
    private String[][] odds = new String[13][3];

    public SvenskaSpelData()  {
        retrieveJSONFromUrl();
        updateMatches();
    }

    private void updateMatches() {
        JSONArray events = draws.getJSONObject(0).getJSONArray("events");
        JSONObject currentDistr;
        JSONObject currentGame;
        JSONObject currentGameOdds;
        //get the games 1-13 and store it in instance variable matches
        for(int i = 0; i < events.length(); i++) {
            currentGame = events.getJSONObject(i);
            matches[i] = currentGame.getString("description");
            currentDistr = currentGame.getJSONObject("distribution");
            procents[i][HOME] = currentDistr.getString("home");
            procents[i][DRAW] = currentDistr.getString("draw");
            procents[i][AWAY] = currentDistr.getString("away");
            currentGameOdds = currentGame.getJSONObject("odds");
            if(!currentGameOdds.isNull("home")){
                odds[i][HOME] = currentGameOdds.getString("home").replace(",", ".");
                odds[i][DRAW] = currentGameOdds.getString("draw").replace(",", ".");
                odds[i][AWAY] = currentGameOdds.getString("away").replace(",", ".");
            } else {
                odds[i][HOME] = "0";
                odds[i][DRAW] = "0";
                odds[i][AWAY] = "0";
            }
        }
    }

    private void retrieveJSONFromUrl() {
        JSONObject obj = null;
        String url = "https://api.www.svenskaspel.se/external/draw/stryktipset/draws?accesskey=" + ApiKey.myApiKey();
        try {
            obj = readJsonFromUrl(url);
            //obj = readJsonFromUrl("https://api.www.svenskaspel.se/external/draw/europatipset/draws?accesskey=8d29068e-7a93-4ecc-aaea-959aee866ab1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        draws = obj.getJSONArray("draws");
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public String[] getMatches() {
        return this.matches;
    }

    public String getWeekHeader() {
        return draws.getJSONObject(0).getString("drawComment");
    }

    public int getTurnOver() {
        return Integer.parseInt(draws.getJSONObject(0).getString("turnover").split(",")[0]);
    }

    public String[][] getProcents() {
        return this.procents;
    }

    public String[][] getOdds() {
        return this.odds;
    }
    public static void main(String[] args) throws IOException {




        //String match = obj.getJSONObject("draws").getString("events");
        //  String test = arr.getJSONObject(0).getString("drawComment");

        //System.out.println(test);
    }
}