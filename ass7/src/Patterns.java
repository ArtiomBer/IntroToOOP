//Artiom Berengard

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is in charge of all the hearst patterns given in the task.
 * The class finds and aggregates hypernym relations that match the hearst
 * patterns using regular expressions, as described in the task.
 */
public class Patterns {
    private Pattern pattern;
    private int hyperIndexInPattern;
    private static String finalRegex = "<np>([^<]*)</np>";
    private List<Patterns> patternList = new ArrayList<>();

    /**
     * The constructor method.
     * @param givenPattern is the given hearst pattern.
     * @param hyperIndexInPattern is the index of the hypernym in the pattern.
     */
    public Patterns(String givenPattern, int hyperIndexInPattern) {
        this.hyperIndexInPattern = hyperIndexInPattern;
        this.pattern = Pattern.compile(givenPattern.replace("NP", finalRegex));
    }

    /**
     * The constructor method without arguments.
     * We create a partial list of patterns using the given hearst patterns
     * in the task.
     */
    public Patterns() {
        // Adding the first pattern from the task.
        this.patternList.add(new Patterns(
                "NP (, )?such as NP( (, )?NP)*( (, )?(and|or) NP)?", 0));
        // Adding the second pattern from the task.
        this.patternList.add(new Patterns(
                "such NP as NP( , NP)*( (, )?(and|or) NP)?", 0));
        // Adding the third pattern from the task.
        this.patternList.add(new Patterns(
                "NP (, )?including NP( (, )?NP)*( (, )?(and|or) NP)?", 0));
        // Adding the forth pattern from the task.
        this.patternList.add(new Patterns(
                "NP (, )?especially NP( (, )?NP)*( (, )?(and|or) NP)?", 0));
        // Adding the fifth pattern from the task.
        this.patternList.add(new Patterns(
                "NP (, )?which is ((an example|a kind|a class) of )?NP", 1));
    }

    /**
     * This method is iterating through a line and returns a list
     * of sentences in the line.
     * @param givenLine is the given line to iterate through.
     * @return the list of sentences with a matching pattern.
     */
    public List<String> getListOfSentences(String givenLine) {
        List<String> listOfSentences = new ArrayList<>();
        Matcher matcher = pattern.matcher(givenLine);
        while (matcher.find()) {
            listOfSentences.add(matcher.group());
        }
        return listOfSentences;
    }

    /**
     * A getter method.
     * @return the pattern list.
     */
    public List<Patterns> getPatternList() {
        return this.patternList;
    }

    /**
     * A getter method.
     * @return the index of the hypernym in each pattern.
     */
    public int getHyperIndexInPattern() {
        return this.hyperIndexInPattern;
    }

    /**
     * This method is in charge of getting a list of noun phrases
     * from a given text by matching them via a regex Matcher.
     * @param givenText is the given text to iterate.
     * @return the list of noun phrases.
     */
    public static List<String> getListOfNP(String givenText) {
        List<String> nounPhrasesList = new ArrayList<>();
        Matcher matcher = Pattern.compile(finalRegex).matcher(givenText);
        while (matcher.find()) {
            nounPhrasesList.add(matcher.group(1));
        }
        return nounPhrasesList;
    }
}
