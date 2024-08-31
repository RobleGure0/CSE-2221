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
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     * @param e
     *            the desired level of accuracy
     */
    private static double sqrt(double x, double e) {
        final double replacementZero = 0.001;
        // handles cases for when x is close to zero for when handling
        // doubles with long values
        if (x - 0 < replacementZero) {
            return x;
        }
        // initial estimate
        double r = x;
        r = (r + x / r) / 2;
        while (Math.abs(r * r - x) / x > e * e) {
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
        out.print(
                "To what degree error would you like to solve for the root (enter the decimal form of the percent [ex: 1% = .01]): ");
        double input2 = in.nextDouble();
        // continue if user enters y
        while (userInput.equals("y")) {
            out.print("Enter a positive number: ");
            double input = in.nextDouble();
            // calculate square root
            double squareRoot = sqrt(input, input2);
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
