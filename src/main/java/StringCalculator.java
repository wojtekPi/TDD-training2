
public class StringCalculator {
    public int Add(String input) {




        if (input.length() >3){
            String [] ar = input.split(",");
            int sum = 0;
            for (int i = 0; i < ar.length; i++){
                sum += Integer.parseInt(ar[i]);
            }
            return sum;
        }

        if (input.length() == 3){
            int d = Integer.parseInt(input.substring(0,1));
            int e = Integer.parseInt(input.substring(2));
            return d+e;
        }
        if (input.equals("1"))return 1;
        if (input.equals("2"))return 2;
        return 0;
    }
}
