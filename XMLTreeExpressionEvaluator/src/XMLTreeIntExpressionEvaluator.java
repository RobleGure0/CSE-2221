import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Roble Gure
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int result = 0;

        if (exp.label().equals("number")) {
            String value = exp.attributeValue("value");
            result = Integer.parseInt(value);
        } else {
            int operand = 0;
            if (exp.numberOfChildren() > 1) {
                operand = evaluate(exp.child(0));
                int operand2 = evaluate(exp.child(1));
                String operator = exp.label();
                if (operator.equals("plus")) {
                    result = operand + operand2;
                } else if (operator.equals("minus")) {
                    result = operand - operand2;
                } else if (operator.equals("times")) {
                    result = operand * operand2;
                } else if (operator.equals("divide")) {
                    result = operand / operand2;
                }
            } else {
                result = evaluate(exp.child(0));
            }
        }

        return result;
    }
    // Couldn't download the files so instead created my own html file and
    // played with the different operators and tested the value 20 and it worked

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
