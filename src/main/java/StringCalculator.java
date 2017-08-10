public class StringCalculator {

    public int Add(String input) {
        if (input.equals("1")) {
            return 1;
        }
        if (input.equals("")) {
            return 0;
        }
        input = input.replace("\n",",");
        String[] words = input.split(",");
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            result += Integer.parseInt(words[i]);
        }
        return result;
    }
}
