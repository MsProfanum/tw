package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Main {
    private static final String path = "https://jsonplaceholder.typicode.com/comments/";

    private static String get(String path) {
        StringBuilder result = new StringBuilder();

        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/json");

            if(conn.getResponseCode() == 401) {
                throw new RuntimeException("HTTP error: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String s = br.readLine();
            while(s != null) {
                result.append(s).append("\n");
                s = br.readLine();
            }

            conn.disconnect();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        for(int i = 1; i <= 200; ++i) {
            String resource = get(path + i);
            System.out.println(resource);
        }
    }
}