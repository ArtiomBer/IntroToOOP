//Artiom Berengard

import java.io.FileWriter;
import java.util.TreeMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * This class is in charge of the hypernym and hyponyms relations.
 * This class also writes to a new file and searches for lemmas.
 */
public class Relations {
    private Map<String, Map<String, Integer>> relations;

    /**
     * This is a constructor method.
     */
    public Relations() {
        this.relations = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * This method searches all the possible hypernyms of the input lemma
     * sorts the supposed output in a decreasing way and print them to
     * the user's screen.
     * @param lemma is the given lemma to search for.
     */
    public void searchLemma(String lemma) {
        String result = this.relations.entrySet().stream()
                .filter(appear -> appear.getValue().containsKey(lemma))
                .sorted((sort1, sort2) -> sort2.getValue().get(lemma) - sort1.getValue().get(lemma))
                .map(value -> String.format("%s: (%d)", value.getKey(), value.getValue().get(lemma)))
                .collect(Collectors.joining("\n"));
        if (result.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
        } else {
            System.out.println(result);
        }
    }

    /**
     * This method is in charge of creating a new file in a destined path
     * and writing to it.
     * We write exactly as described in the task, in a decreasing sorted way,
     * alphabetical and in lower case letters and only if there are at least
     * three hyponyms to a given hypernym.
     * @param file is the given path to the output file.
     * @throws IOException as always when using files, throws exception.
     */
    public void createAndWriteToFile(File file) throws IOException {
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(relations.entrySet().stream()
                .filter(numOfHypo -> numOfHypo.getValue().size() >= 3)
                .map(hyperName -> hyperName.getKey().toLowerCase() + ": " + hyperName.getValue()
                        .entrySet().stream().sorted((sort1, sort2) -> sort2.getValue() - sort1.getValue())
                        .map(value -> String.format("%s (%d)", value.getKey().toLowerCase(),
                                value.getValue())).collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n")));
        fileWriter.close();

    }

    /**
     * This method is in charge of adding a new relation to the database.
     * First we check if the relation is already in the database, and then we
     * add the new hyponym to its hypernym.
     * @param hypernym is the given hypernym.
     * @param hyponym is the given hyponym.
     */
    public void addRelation(String hypernym, String hyponym) {
        if (!relations.containsKey(hypernym)) {
            relations.put(hypernym,
                    new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        }
        Map<String, Integer> numOfOccurrences = relations.get(hypernym);
        numOfOccurrences.put(hyponym,
                numOfOccurrences.getOrDefault(hyponym, 0) + 1);
    }
}
