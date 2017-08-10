
public class StringCalculator {
    public int Add(String input) {

        int sum = 0;
        for(int i = 0; i < input.length(); i++){
            if(Character.isDigit(input.charAt(i))) {
                String toParse = Character.toString(input.charAt(i));
                int num = Integer.parseInt(toParse);
                sum += num;
            }
        }
        return sum;
    }
}
