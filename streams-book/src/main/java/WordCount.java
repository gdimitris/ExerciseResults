import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount {
    public static Map<String,Long> countOfString(String book) {
        return Arrays.stream(book.split(" |\n|\\. "))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
