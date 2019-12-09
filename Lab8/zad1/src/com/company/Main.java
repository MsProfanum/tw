package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    FileRead fileReader = new FileRead("tenk_words_dummy.txt");
        System.out.println("Number of words in the file is: " +
                "\nnormally -> " + fileReader.wordsNormal() +
                "\nusing stream and one thread -> " + fileReader.wordsStreamOneThread() +
                "\nusing stream and multiple threads -> " + fileReader.wordsStreamMultThreads());
    }
}
