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
        double r = x;
        r = (r + x / r) / 2;
        while (Math.abs(r * r - x) / x > 0.0001 * 0.0001) {
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

        out.print(
                "Do you want to calculate a square root, click y to proceed: ");
        String userInput = in.nextLine();
        if (!(userInput.equals("y"))) {
            out.print("Quitting program, goodbye.");
        }
        while (userInput.equals("y")) {
            out.print("Enter a positive number: ");
            double input = in.nextDouble();
            double squareRoot = sqrt(input);
            out.println("The square root of " + input + " is " + squareRoot);

        }
        in.close();
        out.close();
    }

}
