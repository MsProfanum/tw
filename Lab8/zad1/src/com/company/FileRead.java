package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileRead {
    private String path;

    public FileRead(String path){
        this.path = path;
    }

    public int wordsNormal() throws IOException {
        int result = 0;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String[] words = null;
        String s;
        while((s=br.readLine()) != null){
            words = s.trim().split(" ");
            for(String w: words){
                if(w.length() > 0)
                    result++;
            }
        }

        return result;
    }

    public int wordsStreamMultThreads() throws IOException {
        Stream<String> pStream = Files.lines(Paths.get(path));
        long res = pStream.parallel().
                flatMap(line -> Arrays.stream(line.trim().split(" ")))
                .filter(word -> !word.isEmpty())
                .count();

        return (int) res;
    }

    public int wordsStreamOneThread() throws IOException {

        Stream<String> stream = Files.lines(Paths.get(path));
        long res = stream.flatMap(line -> Arrays.stream(line.trim().split(" ")))
                .filter(word -> !word.isEmpty())
                .count();

        return (int) res;
    }
}
