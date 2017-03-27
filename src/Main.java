import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<String> allWordSet = new HashSet<>();
        try {
            Files.lines(new File("enable1.txt").toPath()).forEach(l -> allWordSet.add(l));
        } catch (IOException ex) {
            System.out.println("Couldn't read file!");
        }
        ArrayList<String> wordList = new ArrayList<>();

        allWordSet.stream().forEach(w -> {
            if (w.length() == 2) {
                wordList.add(w);
            }
        });

        String history = "";
        String word = "";
        for (int i = 0; i < wordList.size(); i++) {
            history = wordList.get(i);
            word = history.split(" -> ")[history.split(" -> ").length - 1];
            for (String letter : "abcdefghijklmnopqrstuvwxyz".split("")) {
                if (allWordSet.contains(word + letter)) {
                    wordList.add(history + " -> " + word + letter);
                }

                if (allWordSet.contains(letter + word)) {
                    wordList.add(history + " -> " + letter + word);
                }
            }
        }

        System.out.println("Longest word: " + word);
        System.out.println("History: " + history);
    }
}