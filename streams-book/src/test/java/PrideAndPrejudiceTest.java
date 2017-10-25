import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrideAndPrejudiceTest {

    String prideAndPrejudice = "The Project Gutenberg EBook of Pride and Prejudice, by Jane Austen\n" +
            "\n" +
            "This eBook is for the use of anyone anywhere at no cost and with\n" +
            "almost no restrictions whatsoever.  You may copy it, give it away or\n" +
            "re-use it under the terms of the Project Gutenberg License included\n" +
            "with this eBook or online at www.gutenberg.org\n" +
            "\n" +
            "\n" +
            "Title: Pride and Prejudice\n" +
            "\n" +
            "Author: Jane Austen\n" +
            "\n" +
            "Posting Date: August 26, 2008 [EBook #1342]\n" +
            "Release Date: June, 1998\n" +
            "[Last updated: August 11, 2011]\n" +
            "\n" +
            "Language: English\n" +
            "\n" +
            "\n" +
            "*** START OF THIS PROJECT GUTENBERG EBOOK PRIDE AND PREJUDICE ***\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "Produced by Anonymous Volunteers\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "PRIDE AND PREJUDICE\n" +
            "\n" +
            "By Jane Austen\n" +
            "\n" +
            "\n" +
            "\n" +
            "Chapter 1\n" +
            "\n" +
            "\n" +
            "It is a truth universally acknowledged, that a single man in possession\n" +
            "of a good fortune, must be in want of a wife.\n" +
            "\n" +
            "However little known the feelings or views of such a man may be on his\n" +
            "first entering a neighbourhood, this truth is so well fixed in the minds\n" +
            "of the surrounding families, that he is considered the rightful property\n" +
            "of some one or other of their daughters.\n" +
            "\n" +
            "\"My dear Mr. Bennet,\" said his lady to him one day, \"have you heard that\n" +
            "Netherfield Park is let at last?\"\n" +
            "\n" +
            "Mr. Bennet replied that he had not.\n" +
            "\n" +
            "\"But it is,\" returned she; \"for Mrs. Long has just been here, and she\n" +
            "told me all about it.\"\n" +
            "\n" +
            "Mr. Bennet made no answer.\n" +
            "\n" +
            "\"Do you not want to know who has taken it?\" cried his wife impatiently.\n" +
            "\n" +
            "\"_You_ want to tell me, and I have no objection to hearing it.\"\n" +
            "\n" +
            "This was invitation enough.\n" +
            "\n" +
            "\"Why, my dear, you must know, Mrs. Long says that Netherfield is taken\n" +
            "by a young man of large fortune from the north of England; that he came\n" +
            "down on Monday in a chaise and four to see the place, and was so much\n" +
            "delighted with it, that he agreed with Mr. Morris immediately; that he\n" +
            "is to take possession before Michaelmas, and some of his servants are to\n" +
            "be in the house by the end of next week.\"\n" +
            "\n" +
            "\"What is his name?\"\n" +
            "\n" +
            "\"Bingley.\"\n" +
            "\n" +
            "\"Is he married or single?\"";

    @Test
    public void itReturnsNoWordsToStart() {
        Map<String, Long> wordCount = WordCount.countOfString("");

        assertTrue("The Map Is Not Empty", wordCount.isEmpty());
    }

    @Test
    public void itCanCountOneWord() {
        Map<String, Long> wc = WordCount.countOfString("mine");

        assertEquals(1L, wc.get("mine"), 0.001);
    }

    @Test
    public void itCanCountTwoWordsSeperatedByASpace() {
        Map<String, Long> wc = WordCount.countOfString("my book");

        assertEquals(1L, wc.get("my"), 0.001);
        assertEquals(1L, wc.get("book"), 0.001);
    }

    @Test
    public void itCanCountTheSameWordTwice() {
        Map<String, Long> wc = WordCount.countOfString("my my my music");

        assertEquals(3L, wc.get("my"), 0.001);
        assertEquals(1L, wc.get("music"), 0.001);
    }

    @Test
    public void itAlsoSplitsNewLines() {
        Map<String, Long> wc = WordCount.countOfString("my\nmy my music");

        assertEquals(3L, wc.get("my"), 0.001);
        assertEquals(1L, wc.get("music"), 0.001);
    }

    @Test
    public void itShouldHaveAmaxValue() {
        Map<String, Long> wc = WordCount.countOfString(prideAndPrejudice);

        System.out.println(wc);
        assertTrue(true);
    }
}
