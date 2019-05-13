package Kodutöö8;
import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        Map<String, Long> occurrence;
        ArrayList<String> wordsWithOccurrence = new ArrayList<>();
        String input = "sisend.txt";
        String output = "väljund.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\andri\\IntelliJIDEAProjects\\Programmeerimine\\src\\Kodutöö8\\sisend.txt"));

            String line = reader.readLine();
            while (line != null) {
                if(line.contains(" ")) {
                    String parts[] = line.split(" ");
                    for (int i=0; i<=parts.length-1; i++){
                        String part = parts[i];
                        String lowerPart = part.toLowerCase();
                        words.add(lowerPart);
                    }
                } else {
                    String lowerLine = line.toLowerCase();
                    words.add(lowerLine);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        occurrence = words.stream().collect(groupingBy(Function.identity(), counting()));

        for (Map.Entry<String, Long> entry : occurrence.entrySet()) {
            wordsWithOccurrence.add(entry.getKey() + " " + entry.getValue());
        }

        List<String> wordsSortedA = wordsWithOccurrence
                .stream()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList());

        int size = wordsSortedA.size();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("C:\\Users\\andri\\IntelliJIDEAProjects\\Programmeerimine\\src\\Kodutöö8\\väljund.txt");
            for (int g = 0; g<=size-1; g++){
                fileWriter.write(wordsSortedA.get(g));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}