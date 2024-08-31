import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Create an easy to use Glossary by generating html files that include a term
 * with a definition and an index that separates the pages.
 *
 * @author Roble Gure
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
        // no code needed here
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String wordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String result = "";
        if (separators.contains(text.charAt(position))) {
            int i = position;
            while (i < text.length() && separators.contains(text.charAt(i))) {
                result += text.charAt(i);
                i++;
            }
        } else {
            int i = position;
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                result += text.charAt(i);
                i++;
            }
        }
        return result;
    }

    /**
     * Updates the definitions in the termAndDefinition map with HTML hyperlinks
     * to respective pages.
     *
     * @param termQueue
     *            queue of terms to be updated
     * @param termAndDefinition
     *            the map containing terms and their definitions
     * @requires termQueue is not null, termAndDefinition is not null, both must
     *           be initialized
     * @ensures the definitions in termAndDefinition will be updated with HTML
     *          hyperlinks to respective pages
     */
    public static void updateItemDef(Queue<String> termQueue,
            Map<String, String> termAndDefinition) {
        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add(' ');
        separators.add('\t');
        separators.add(';');
        separators.add('.');

        int queueSize = termQueue.length();
        for (int i = 0; i < queueSize; i++) {
            String term = termQueue.front();
            String definition = termAndDefinition.value(term);
            for (String currentTerm : termQueue) {
                int position = 0;
                while (position < definition.length()) {
                    String currentWord = wordOrSeparator(definition, position,
                            separators);
                    if (currentWord.equals(currentTerm)) {
                        int wordIndex = definition.indexOf(currentWord,
                                position);
                        definition = definition.substring(0, wordIndex)
                                + "<a href=\"" + currentWord + ".html\">"
                                + currentWord + "</a>" + definition.substring(
                                        wordIndex + currentWord.length());
                    }
                    position += currentWord.length();
                }
            }
            termAndDefinition.replaceValue(term, definition);
            String frontElement = termQueue.dequeue();
            termQueue.enqueue(frontElement);
        }
    }

    /**
     * Extracts terms and definitions from an input stream and stores them in a
     * map.
     *
     * @param inputStream
     *            object that provides the input stream
     * @param termDefinitionMap
     *            a map that stores the term-definition pairs
     * @return a queue containing the terms in order
     */
    private static Queue<String> extractTermsAndDefinitions(
            Map<String, String> termDefinitionMap, SimpleReader inputStream) {
        Queue<String> termList = new Queue1L<>();

        while (!inputStream.atEOS()) {
            String term = inputStream.nextLine().trim();
            String definition = "";
            boolean endOfDefinition = false;
            while (!endOfDefinition && !inputStream.atEOS()) {
                String line = inputStream.nextLine().trim();
                if (line.equals("")) {
                    endOfDefinition = true;
                } else {
                    definition += line + " ";
                }
            }
            termDefinitionMap.add(term, definition.trim());
            termList.enqueue(term);
        }
        return termList;
    }

    /**
     * Creates an HTML page for the term that is listed with its own definition
     * and links.
     *
     * @param outputFile
     *            a SimpleWriter for writing the HTML file
     * @param term
     *            the term that is defined
     * @param termAndDefinitionMap
     *            a map containing terms as keys and their definitions as values
     */
    public static void outputTags(SimpleWriter outputFile, String term,
            Map<String, String> termAndDefinitionMap) {
        outputFile.println("<html>");
        outputFile.println(" <head>");
        outputFile.println(" <title>" + term + "</title>");
        outputFile.println(" </head>");
        outputFile.println(" <body>");
        outputFile.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");
        String updatedDefinition = termAndDefinitionMap.value(term);
        outputFile
                .println("<blockquote>" + updatedDefinition + "</blockquote>");
        outputFile.println("<hr>");
        outputFile
                .println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        outputFile.println(" </body>");
        outputFile.println("</html>");
    }

    /**
     * Generates an HTML title page with an index of terms.
     *
     * @param outputWriter
     *            a SimpleWriter where the terms will be added
     * @param termQueue
     *            a queue of terms that is defined
     */
    public static void generateTitlePage(SimpleWriter outputWriter,
            Queue<String> termQueue) {
        outputWriter.println("<html>");
        outputWriter.println("<head>");
        outputWriter.println("<title>Glossary</title>");
        outputWriter.println("</head>");
        outputWriter.println("<body>");
        outputWriter.println("<h1>Glossary</h1>");
        outputWriter.println("<hr>");
        outputWriter.println("<h2>Index</h2>");
        outputWriter.println("<ul>");
        for (int i = 0; i < termQueue.length(); i++) {
            outputWriter.println("<li>");
            outputWriter.println("<a href=\"" + termQueue.front() + ".html\">"
                    + termQueue.front() + "</a>");
            outputWriter.println("</li>");
            termQueue.rotate(1);
        }
        outputWriter.println("</ul>");
        outputWriter.println("</body>");
        outputWriter.println("</html");
    }

    /**
     * Main method to create a glossary page and individual term pages.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.println("Enter input file location: ");
        String inputFile = in.nextLine();
        out.println("Enter the name of a folder: ");
        String nameOfFolder = in.nextLine();
        SimpleReader input = new SimpleReader1L(inputFile);
        Map<String, String> termAndDefinition = new Map1L<>();
        Queue<String> terms = extractTermsAndDefinitions(termAndDefinition,
                input);
        updateItemDef(terms, termAndDefinition);
        SimpleWriter output = new SimpleWriter1L(nameOfFolder + "\\index.html");
        generateTitlePage(output, terms);
        output.close();
        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            SimpleWriter output1 = new SimpleWriter1L(
                    nameOfFolder + "\\" + term + ".html");
            outputTags(output1, term, termAndDefinition);
            output1.close();
        }
        in.close();
        out.close();
        input.close();
    }

}
