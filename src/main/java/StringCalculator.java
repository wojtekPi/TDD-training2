import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String input) {
        if(input.isEmpty()) {
            return 0;
        }
        int result = 0;
        String regex = "//(.)\n([0-9]+\\1)*[0-9]+|([0-9][, ]*)*[0-9]+|([0-9]+\n)[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);


        if(!matcher.matches()) {
            return 69;
        }

        String regex2 = "[0-9]+";
        Pattern pattern1 = Pattern.compile(regex2);
        Matcher matcher1 = pattern1.matcher(input);


        while(matcher1.find()) {
            result += Integer.parseInt(matcher1.group());
        }
        return result;
    }
}
