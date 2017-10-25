package lambdas;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class NumberValidatorTest {

    private static final String MENU_PROMPT = "Please choose:\n(1) Validate number\n(2) Exit\n";
    private static final String NOT_A_VALID_MENU_OPTION = "Not a valid menu option!\n";
    private static final String NUMBER_PROMPT = "Please enter a number:\n";
    private static final String NOT_A_NUMBER = "Not a number!\n";
    private String writtenString;
    private final Consumer<String> writer = (s) -> writtenString += s;

    @Before
    public void setUp() {
        writtenString = "";
    }

    @Test
    public void promptsForInput() {
        Supplier<String> reader = () -> "1";

        NumberValidator numberValidator = new NumberValidator(reader, writer);

        int userInput = numberValidator.promptUntilValidNumberEntered();

        assertThat(userInput, is(1));
    }

    @Test
    public void readsInputAsInteger() {
        Supplier<String> reader = () -> "1";

        NumberValidator numberValidator = new NumberValidator(reader, writer);

        numberValidator.promptUntilValidNumberEntered();

        assertThat(writtenString, is(NUMBER_PROMPT));
    }

    private Supplier<String> readerWithValues(List<String> values) {
        List<String> numbers = new ArrayList<>(values);
        return () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
    }

    @Test
    public void readsUntilNumericInputProvided() {
        Supplier<String> reader = readerWithValues(Arrays.asList("A", "3"));
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        int userInput = numberValidator.promptUntilValidNumberEntered();

        assertThat(userInput, is(3));
    }

    @Test
    public void readsUntilNumberInRangeIsProvided() {
        List<String> numbers = new ArrayList<>(Arrays.asList("A", "3"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        int userInput = numberValidator.promptUntilValidNumberEntered();

        assertThat(userInput, is(3));
    }

    @Test
    public void displaysErrorMessageBeforeReprompt() {
        List<String> numbers = new ArrayList<>(Arrays.asList("A", "3"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        numberValidator.promptUntilValidNumberEntered();
        assertEquals(NUMBER_PROMPT + NOT_A_NUMBER + NUMBER_PROMPT, writtenString);
    }

    @Test
    public void promptsForMenuChoice() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        int userInput = numberValidator.promptUntilValidMenuChoice();

        assertThat(userInput, is(1));
    }

    @Test
    public void displaysErrorMessageForInvalidMenuChoice() {
        List<String> numbers = new ArrayList<>(Arrays.asList("3", "1"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        numberValidator.promptUntilValidMenuChoice();
        assertEquals(writtenString,MENU_PROMPT + NOT_A_VALID_MENU_OPTION + MENU_PROMPT);
    }

    @Test
    public void printsUserPromptsForValidInput() throws Exception {
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "1"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        numberValidator.runApp(numberValidator);
        assertEquals(writtenString,MENU_PROMPT + NUMBER_PROMPT);
    }

    @Test(expected = RuntimeException.class)
    public void exceptionWhenWritingMenuPrompt() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        Consumer<String> writerThatThrowsException = (s) -> {throw new RuntimeException("Welp"); };
        NumberValidator numberValidator = new NumberValidator(reader, writerThatThrowsException);

        numberValidator.promptUntilValidMenuChoice();
    }

    @Test(expected = RuntimeException.class)
    public void exceptionWhenWritingNumberPrompt() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1"));
        Supplier<String> reader = () -> {
            String c = numbers.get(0);
            numbers.remove(0);
            return c;
        };
        Consumer<String> writerThatThrowsException = (s) -> {throw new RuntimeException("Welp"); };
        NumberValidator numberValidator = new NumberValidator(reader, writerThatThrowsException);

        numberValidator.promptUntilValidNumberEntered();
    }

    @Test(expected = RuntimeException.class)
    public void exceptionsWhenReadingInput() {
        Supplier<String> reader = () -> { throw new RuntimeException("We are doomed"); };
        NumberValidator numberValidator = new NumberValidator(reader, writer);

        numberValidator.promptUntilValidNumberEntered();
    }


    class USB {
        String getVersion() {
            return "version";
        }
    }

    class Soundcard {

        private USB usb;

        public USB getUSB() {
            return usb;
        }
    }

    class Computer {

        private Soundcard soundcard;

        public Soundcard getSoundcard() {
            return soundcard;
        }
    }

    public static String getVersion(Optional<Computer> computer) {
        return computer.map(Computer::getSoundcard)
                        .map(Soundcard::getUSB)
                        .map(USB::getVersion)
                        .orElse("UNKNOWN");
    }

    @Test
    public void someStuffWithOptional() {

    }
}
