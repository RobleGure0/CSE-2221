import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Roble Gure
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_65_25() {
        NaturalNumber n = new NaturalNumber2(65);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(25);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_230_30() {
        NaturalNumber n = new NaturalNumber2(200);
        NaturalNumber nExpected = new NaturalNumber2(10);
        NaturalNumber m = new NaturalNumber2(30);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_57() {
        NaturalNumber n = new NaturalNumber2(57);
        NaturalNumber nExpected = new NaturalNumber2(57);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_64() {
        NaturalNumber n = new NaturalNumber2(64);
        NaturalNumber nExpected = new NaturalNumber2(64);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_7_8_10() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(8);
        NaturalNumber pExpected = new NaturalNumber2(8);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(10);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_12_13_11() {
        NaturalNumber n = new NaturalNumber2(12);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(13);
        NaturalNumber pExpected = new NaturalNumber2(13);
        NaturalNumber m = new NaturalNumber2(11);
        NaturalNumber mExpected = new NaturalNumber2(11);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    @Test
    public void isWitnessToCompositeness_10_30() {
        NaturalNumber w = new NaturalNumber2(10);
        NaturalNumber n = new NaturalNumber2(30);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isWitnessToCompositeness_5_10() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isWitnessToCompositeness_7_11() {
        NaturalNumber w = new NaturalNumber2(7);
        NaturalNumber n = new NaturalNumber2(11);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isWitnessToCompositeness_3_13() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(13);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    /*
     * Tests of isPrime2
     */

    @Test
    public void isPrime2_23() {
        NaturalNumber n = new NaturalNumber2(23);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void isPrime2_13() {
        NaturalNumber n = new NaturalNumber2(13);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void isPrime2_25() {
        NaturalNumber n = new NaturalNumber2(25);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(false, result);
    }

    @Test
    public void isPrime2_27() {
        NaturalNumber n = new NaturalNumber2(27);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(false, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    @Test
    public void generateNextLikelyPrime_13() {
        NaturalNumber n = new NaturalNumber2(13);
        CryptoUtilities.generateNextLikelyPrime(n);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void generateNextLikelyPrime_7() {
        NaturalNumber n = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void generateNextLikelyPrime_353() {
        NaturalNumber n = new NaturalNumber2(353);
        CryptoUtilities.generateNextLikelyPrime(n);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void generateNextLikelyPrime_5761() {
        NaturalNumber n = new NaturalNumber2(5761);
        CryptoUtilities.generateNextLikelyPrime(n);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

}
