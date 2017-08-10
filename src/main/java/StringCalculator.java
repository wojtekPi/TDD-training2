import java.util.stream.Stream;

public class StringCalculator {
    public int Add(String input) {
        if (input == null) {
            return 0;
        }
        int sum = 0;
        String[] numbers = input.split(",");
        sum = Stream.of(numbers)
                .mapToInt(Integer::valueOf)
                .sum();
        return sum;
    }
}
