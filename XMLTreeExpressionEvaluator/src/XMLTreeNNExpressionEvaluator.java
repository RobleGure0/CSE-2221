import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber result = new NaturalNumber2(0);

        if (exp.label().equals("number")) {
            String value = exp.attributeValue("value");
            result.setFromString(value);
        } else {
            NaturalNumber operand = result.newInstance();
            NaturalNumber operand2 = result.newInstance();
            if (exp.numberOfChildren() > 1) {
                operand.copyFrom(evaluate(exp.child(0)));
                operand2.copyFrom(evaluate(exp.child(1)));

                String operator = exp.label();
                if (operator.equals("plus")) {
                    operand.add(operand2);
                } else if (operator.equals("minus")) {
                    if (operand.compareTo(operand2) < 0) {
                        Reporter.fatalErrorToConsole(
                                "Can't subtract by a larger number.");
                    }
                    operand.subtract(operand2);
                } else if (operator.equals("times")) {
                    operand.multiply(operand2);
                } else if (operator.equals("divide")) {
                    if (operand2.isZero()) {
                        Reporter.fatalErrorToConsole("Can't divide by zero.");
                    }
                    operand.divide(operand2);
                }
                result.transferFrom(operand);
            } else {
                result.transferFrom(operand);
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
