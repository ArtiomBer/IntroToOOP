//Artiom Berengard

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * This class is in charge of running through the given corpus
 * files and iterating through its texts and lines in order to find
 * relations and them to the database.
 */
public class Runner {
    private  Relations relations;
    private  List<Patterns> patternList;

    /**
     * This method is the constructor.
     * @param relations is the given relations.
     * @param patternList is the given pattern list.
     */
    public Runner(Relations relations, List<Patterns> patternList) {
        this.relations = relations;
        this.patternList = patternList;
    }

    /**
     * This method is in charge of iterating through the files of the
     * corpus and for each file we use the iterateThroughLines method,
     * that is described below.
     * @param files are the given files from the corpus.
     * @throws IOException as always when working with files, we may
     * throw an exception.
     */
    public void iterateThroughFiles(File[] files) throws IOException {
        for (File file : files) {
            iterateThroughLines(file);
        }
    }

    /**
     *This method is in charge of iterating through the lines of the
     * file and for each line we use the iterateThroughSentences method,
     * that is described below.
     * @param file is the given file from the corpus files list.
     * @throws IOException as always when working with files, we may
     * throw an exception.
     */
    public void iterateThroughLines(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        for (String line = bufferedReader.readLine();
             line != null;
             line = bufferedReader.readLine()) {
            iterateThroughSentences(line);
        }
    }
    /**
     * This method is in charge of iterating through the given line and
     * creating a sentence list, that will be iterated in order to find all
     * the noun phrases and then create, and add, all the relations that we
     * find between a hypernym and its hyponyms to the relations database.
     * @param line is the given line from a file.
     */
    public void iterateThroughSentences(String line) {
        for (Patterns patterns : patternList) {
            List<String> sentenceList = patterns.getListOfSentences(line);
            if (!sentenceList.isEmpty()) {
                for (String sentence : sentenceList) {
                    List<String> allNP = Patterns.getListOfNP(sentence);
                    String hyper = allNP.get(patterns.getHyperIndexInPattern());
                    allNP.remove(patterns.getHyperIndexInPattern());
                    for (String hypo : allNP) {
                        relations.addRelation(hyper, hypo);
                    }
                }
            }
        }
    }
}
