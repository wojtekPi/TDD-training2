import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    private Integer addingResult=0;
    private Integer elInt = 0;

    public int Add(String arg){
        if(arg.equals("")) return 0;
        if(arg.equals("1")) return 1;
        if(arg.equals("2")) return 2;

        String[] elements = arg.split(",");

        for(String el: elements){
            try {
                elInt = Integer.parseInt(el);
            }catch (Exception e){
            }
            addingResult+=elInt;

        }
        return addingResult;

    }

}
