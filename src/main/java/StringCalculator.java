import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int Add(String input){

        if (input==null) return 0;
        if (input.length()==0)
            return 0;

        /*
        int number = 0;
        if (!input.contains(",") && input.length()!=0) {
            number = Integer.parseInt(input);
            return number;
        }
        int sum = 0;
        String tab[] = input.split(",");
        for (int i =0; i<tab.length; i++){
            number = Integer.parseInt(tab[i]);
            sum += number;
        }
        return sum;

        */
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
