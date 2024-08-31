import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double epsilon = 0.01;
        // initial estimate
        double r = x;
        r = (r + x / r) / 2;
        while (Math.abs(r * r - x) / x > epsilon * epsilon) {
            // update estimate
            r = (r + x / r) / 2;
        }
        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // prompt user with display
        out.print(
                "Do you want to calculate a square root, click y to proceed: ");
        String userInput = in.nextLine();
        if (!(userInput.equals("y"))) {
            out.print("Quitting program, goodbye.");
        }
        // continue if user enters y
        while (userInput.equals("y")) {
            out.print("Enter a positive number: ");
            double input = in.nextDouble();
            // calculate square root
            double squareRoot = sqrt(input);
            out.println("The square root of " + input + " is " + squareRoot);
            out.print(
                    "Do you want to calculate a square root, click y to proceed: ");
            userInput = in.nextLine();
            if (!(userInput.equals("y"))) {
                out.print("Quitting program, goodbye.");
            }

        }

        in.close();
        out.close();
    }

}
