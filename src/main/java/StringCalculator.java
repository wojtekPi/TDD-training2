import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int Add(String input) throws Exception {
        if (input == null || input.length() == 0) return 0;

        int result = 0;
        String delimiters = ",\\n";
        if (input.charAt(0) == '/') {
            char c = input.charAt(2);
            if (c == '[') {
                boolean isDelimiter = true;
                StringBuilder sb = new StringBuilder().append('{');
                for (int i = 3; i < input.length(); i++) {
                    c = input.charAt(i);
                    if (c == '[') {
                        isDelimiter = true;
                        sb.append('{');
                    } else if (c == ']') {
                        isDelimiter = false;
                        sb.append('}');
                    } else if (c == '\\' && input.charAt(i + 1) == 'n' && !isDelimiter) {
                        input = input.substring(i+2);
                        break;
                    } else {
                        sb.append(c);
                    }
                }
                delimiters = sb.toString();
            } else {
                delimiters = Character.toString(c);
                input = input.substring(4);
            }
        }
        Pattern pattern = Pattern.compile("(-?\\d+)[" + delimiters + "]*");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String s = matcher.group(1);
            int number = Integer.parseInt(s);
            if (number > 1000) continue;
            if (number < 0) throw new Exception("negatives not allowed: " + number);
            result += number;
        }

        return result;
    }
}
