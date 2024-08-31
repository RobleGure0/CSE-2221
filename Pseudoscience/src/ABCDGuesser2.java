import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double switcher = 0;
        double returner = 0;
        String estimate = "";
        double estimateDouble = -1;

        //loops until user enters a positive real number
        while (switcher == 0) {
            out.println(
                    "Enter a positive real number that you are approximating: ");
            estimate = in.nextLine();

            // check if estimate can be turned into double
            if (FormatChecker.canParseDouble(estimate)) {
                estimateDouble = Double.parseDouble(estimate);

                // if new double is positive exit loop
                if (estimateDouble >= 0) {
                    returner = estimateDouble;
                    switcher = 1;
                    // corresponding output statements
                } else {
                    out.println("Try again. Enter a positive real number.");
                }
            } else {
                out.println("Invalid input. You must enter a real number.");
            }
        }

        return returner;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double switcher = 0;
        double returner = 0;
        String estimate = "";
        double estimateDouble = -1;

        //loops until user enters a positive real number that is not equal to one
        while (switcher == 0) {
            out.println("Enter a positive real number not equal to 1: ");
            estimate = in.nextLine();

            if (FormatChecker.canParseDouble(estimate)) {
                estimateDouble = Double.parseDouble(estimate);

                // if new double is positive and not one exit loop
                if (estimateDouble != 1.0 && estimateDouble >= 0) {
                    returner = estimateDouble;
                    switcher = 1;
                } else {
                    out.println(
                            "Try again. Enter a positive real number not equal to 1");
                }
            } else {
                out.println("Invalid input. You must enter a real number.");
            }
        }

        return returner;
    }

    /**
     * Calculates the final result.
     *
     * @param out
     *            printing info to console
     * @param mu
     *            value being approximated to
     * @param bestGuess
     *            best guess for estimating
     */

    private static void calculateResult(SimpleWriter out, double mu,
            double bestGuess) {
        // static method to calculate result
        out.println("Best guess is: " + bestGuess);
        final double Onehundred = 100.0;
        double relativeError = (Math.abs(mu - bestGuess) / mu) * Onehundred;
        out.println("Relative error is: " + relativeError + "%");
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

        // all of the exponents used
        final double nums[] = { -5.0, -4.0, 3.0, -2.0, -1.0, (-1 / 2.0),
                (-1 / 3.0), (-1 / 4.0), 0.0, (1 / 2.0), (1 / 3.0), (1 / 4.0),
                1.0, 2.0, 3.0, 4.0, 5.0 };
        double mu = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double bestGuess = -1;
        final double Four = 4.0;
        final double Three = 3;
        double[] abcd = new double[(int) Four];

        // nested for loops to find best combo for de jager formula
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    for (int l = 0; l < nums.length; l++) {
                        // calculate current guess using de jager formula
                        double currentGuess = Math.pow(w, nums[i])
                                * Math.pow(x, nums[j]) * Math.pow(y, nums[k])
                                * Math.pow(z, nums[l]);
                        // update best guess if current guess is a better fit
                        if (Math.abs(mu - currentGuess) < (mu - bestGuess)) {
                            bestGuess = currentGuess;
                            abcd[0] = nums[i];
                            abcd[1] = nums[j];
                            abcd[2] = nums[k];
                            abcd[(int) Three] = nums[l];

                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        // print exponents used
        for (int m = 0; m < Four; m++) {
            out.println(abcd[m]);
        }
        // call on calculateResult method
        calculateResult(out, mu, bestGuess);

        in.close();
        out.close();

    }

}
