package de.keksgauner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayerFetcher {

    private final static String urlMojang = "https://api.mojang.com/user/profiles/";

    public static String getPlayerOf(UUID playerUUID) throws IOException {

        final URL url = new URL(urlMojang + playerUUID + "/names");
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode() == 400) {
            return null;
        }

        final InputStream inputStream = connection.getInputStream();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        final JsonElement jsonElement = new JsonParser().parse(bufferedReader);
        String playerName = "Unknown";

        try {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            playerName = jsonObject.get("name").getAsString();

        } catch (Exception ignored) {
        }
        return playerName;
    }
}