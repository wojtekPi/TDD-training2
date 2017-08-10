import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int Add(String input) {
        if (input.length() == 0) {
            return 0;
        }

        int result = 0;

        Pattern pattern = Pattern.compile("(\\d+)[,\\n]*");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            String number = matcher.group(1);
            result += Integer.parseInt(number);
        }

        return result;
    }
}
