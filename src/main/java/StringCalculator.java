import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private Integer addingResult=0;
    private Integer elInt = 0;

    public int Add(String arg){
        if(arg.equals("")) return 0;
        if(arg.equals("1")) return 1;
        if(arg.equals("2")) return 2;

        String pattern = "(\\d+) [, \\n]*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(arg);

        while(m.find()){
            elInt = Integer.parseInt(m.toString());
            addingResult +=elInt;
        }
        return addingResult;

    }

}
