import java.io.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;



public class wordCount {
    /**
     * A method to count the words in the file and store them in a hashmap with
     * the word as the key and their frequency as the value, then sort them as a
     * list and print them out
     * @param filename the file to be read from
     */
    public static void count(String filename) {
        Map<String, Integer> wordList = new HashMap<>();
        try {
            Scanner fileReader = new Scanner(new File(filename));
            while (fileReader.hasNext()) {
                String word = fileReader.next();
                String replaced = word.replaceAll("[^a-zA-Z ]", "");
                String lowercase = replaced.toLowerCase();
                if (!wordList.containsKey(lowercase)) {
                    wordList.put(lowercase, 1);
                } else {
                    wordList.replace(lowercase, wordList.get(lowercase) + 1);
                }
            }
            Set<Entry<String,Integer>> entries = wordList.entrySet();
            Comparator<Entry<String, Integer>> valComparator = (entry1, entry2) -> {
                Integer int1 = entry1.getValue();
                Integer int2 = entry2.getValue();
                return int2 - int1;
            };
            ArrayList<Entry<String, Integer>> entryList = new ArrayList<>(entries);
            entryList.sort(valComparator);

            String output = "src/test/java/output.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            for (Entry<String,Integer> entry : entryList) {
                writer.write( entry.getValue() + " " + entry.getKey() + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Make sure that " + filename
                    + " is in the current directory!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


