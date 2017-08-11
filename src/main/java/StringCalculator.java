public class StringCalculator {
    public int Add(String input) {

        int result = 0;


        if (input == null || input.equals((""))) {
            return 0;
        }

        String [] numbers = input.split("\\D");

        for(int i =0; i < numbers.length; i++){
            result += Integer.parseInt(numbers[i]);
        }
        return result;
    }
}