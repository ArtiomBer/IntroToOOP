//Artiom Berengard

import java.io.File;
import java.io.IOException;

/**
 * This class is in charge of creating a database of hypernyms
 * and running the main method, that will create a new file in a given
 * path. This file will contain all the hypernyms and their hyponyms.
 */
public class CreateHypernymDatabase {
    /**
     * The main method will create a new file, called hypernym_db
     * and has all the hypernyms with its hyponyms, sorted as needed.
     * @param args has two arguments:
     *             The first one is in charge of the given path to the corpus.
     *             The second one is in charge of the output path of the file.
     */
    public static void main(String[] args) {
        Relations relations = new Relations();
        Patterns patterns = new Patterns();
        Runner runner = new Runner(relations, patterns.getPatternList());
        File corpusDirectoryPath = new File(args[0]);
        File[] fileList = corpusDirectoryPath.listFiles();
        File outputFilePath = new File(args[1]);
        try {
            runner.iterateThroughFiles(fileList);
            relations.createAndWriteToFile(outputFilePath);
        } catch (IOException exception) {
            System.out.println("Invalid arguments.");
        }
    }
}
