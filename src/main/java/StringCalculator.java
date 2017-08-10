
public class StringCalculator {
    public int Add(String input) throws NegativeException {
        if(input.length()==1){
            return Integer.parseInt(input);
        }
        if(input.length()==0){
            return 0;
        }
        String[] pattern = input.split("[,.\\s+;:]");
        int result = 0;

        for(int i =0; i < pattern.length; i++){
            result += Integer.parseInt(pattern[i]);
            if(result < 0) {

                throw new NegativeException("negatives not allowed: " + pattern[i]);
            }
        }
        return result;
    }
}
