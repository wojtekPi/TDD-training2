
public class StringCalculator {
    public int Add(String input){
        String[] pattern = input.split(",");
        int result = 0;

        for(int i =0; i < pattern.length; i++){
            result += Integer.parseInt(pattern[i]);
        }
        if(input.length()==1){
            return Integer.parseInt(input);
        }
        if(input.length()==0){
            return 0;
        }
        return result;
    }
}
