//Artiom Berengard

import java.io.File;
import java.io.IOException;

/**
 * This class will search all the possible hypernyms of a lemma
 * and print them.
 */
public class DiscoverHypernym {
    /**
     * The main method is in charge of iterating through the database
     * and print the possible hypernyms of the input lemma, via the
     * searchLemma method.
     * @param args are the arguments we receive:
     *             The first one is the path to the corpus.
     *             The second one is the given lemma.
     */
    public static void main(String[] args) {
        Relations relations = new Relations();
        Patterns patterns = new Patterns();
        Runner runner = new Runner(relations, patterns.getPatternList());
        File corpusDirectoryPath = new File(args[0]);
        File[] fileList = corpusDirectoryPath.listFiles();
        String givenLemma = args[1];
        try {
            runner.iterateThroughFiles(fileList);
        } catch (IOException exception) {
            System.out.println("Invalid arguments.");
        }
        relations.searchLemma(givenLemma);
    }
}
