import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * tests of combination
     */

    @Test
    public void testCombination_water_terminal() {
        String str1 = "water";
        String str2 = "terminal";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("waterminal", combine);
    }

    @Test
    public void testCombination_sun_sunny() {
        String str1 = "sun";
        String str2 = "sunny";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("sunny", combine);
    }

    @Test
    public void testCombination_drain_gang() {
        String str1 = "drain";
        String str2 = "gang";
        int overlap = 0;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("draingang", combine);
    }

    /*
     * tests of add to set avoiding substrings
     */

    @Test
    public void testaddToSetAvoidingSubstrings_fish_beef_pork_lamb() {
        String str = "fish";
        Set<String> str1 = new Set1L<>();
        str1.add("beef");
        str1.add("pork");
        str1.add("lamb");
        Set<String> str2 = new Set1L<>();
        str2.add("lamb");
        str2.add("pork");
        str2.add("beef");
        str2.add("fish");
        StringReassembly.addToSetAvoidingSubstrings(str1, str);
        assertEquals("fish", str);
        assertEquals(str2, str1);
    }

    @Test
    public void testaddToSetAvoidingSubstrings_grass_food_toys_games() {
        String str = "grass";
        Set<String> str1 = new Set1L<>();
        str1.add("food");
        str1.add("toys");
        str1.add("games");
        Set<String> str2 = new Set1L<>();
        str2.add("games");
        str2.add("food");
        str2.add("toys");
        str2.add("grass");
        StringReassembly.addToSetAvoidingSubstrings(str1, str);
        assertEquals("grass", str);
        assertEquals(str2, str1);
    }

    /*
     * tests of lines from input
     */

    @Test
    public void testlinesFromInput() {
        SimpleReader input = new SimpleReader1L("cheer-8-2.txt");
        Set<String> expected = new Set1L<>();
        expected.add("Bucks -- Beat");
        expected.add("Go Bucks");
        expected.add("o Bucks -- B");
        expected.add("Beat Mich");
        expected.add("ichigan~");
        expected.add("Bucks");
        expected.add("Michigan~");
        Set<String> result = StringReassembly.linesFromInput(input);
        assertEquals(expected, result);
        input.close();
    }

    /*
     * test of print with line separators
     */

    @Test
    public void testPrintWithLineSeparators1() {
        String text = "127027037927327019~72~3445";
        SimpleWriter out = new SimpleWriter1L("testingforchar.txt");
        StringReassembly.printWithLineSeparators(text, out);
        out.close();
        SimpleReader in = new SimpleReader1L("testingforchar.txt");
        assertEquals("127027037927327019", in.nextLine());
        assertEquals("72", in.nextLine());
        assertEquals("3445", in.nextLine());
        in.close();
    }

}
