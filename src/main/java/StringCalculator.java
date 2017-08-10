import org.assertj.core.internal.Characters;

public class StringCalculator {
    public int Add(String input) {
        if (input.equals("")) {
            return 0;
        } else if (input.equals("1")) {
            return 1;
        }

        String[] words = input.split(",");
        int result = 0;

        for (int i = 0; i < words.length; i++) {
            result += Integer.parseInt(words[i]);
        }
        return result;

    }
}
