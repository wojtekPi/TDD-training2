import static org.assertj.core.util.Strings.isEmpty;

public class StringCalculator {

    private final String delimiter = "1\\n2,3";

    public int Add(String input){

        String[] numbers = input.split(delimiter);

        if(isEmpty(input)){
            return 0;
        }

        //        if(input.equals("1")){
//            return 1;
//        }
//        if(input.equals("2")){
//            return 2;
//        }
//
//        return 0;

        if(input.length() == 1){
            return stringToInt(input);
        } else {
            return Integer.parseInt(numbers[0])+ Integer.parseInt(numbers[1]);
        }





    }

    private boolean isEmpty(String input){
        return input.isEmpty();
    }


    private int stringToInt(String input){
        return Integer.parseInt(input);
    }

}
