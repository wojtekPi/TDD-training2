
public class StringCalculator {

    public int Add(String input) {

        int sum = 0;

        String []A = input.split(",");

        if(input.equals("")) {
            return 0;
        }

        for (int i=0;i < A.length; i++) {
            sum += Integer.parseInt(A[i]);
        }
            return sum+1;

    }
}
