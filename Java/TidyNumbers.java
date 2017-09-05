import java.util.Scanner;

public class TidyNumbers {

    /*
    * Tidy Numbers by Google Code jam
    *   https://code.google.com/codejam/contest/3264486/dashboard#s=p1
    *
    * Solution
    *   for array of digits N of size n:
    *   go through all the digits of N looking for digits d and r
    *       where u is the first "untidy" digit - it is greater than the digit after it
    *       and d is the last digit before d that can be decreased while keeping N[0...r] tidy
    *   if an u is found:
    *       decrease N[d] by 1
    *       replace N[d+1]...N[n-1] with '9'
    * */

    static final int ASCII_CONVERSION = 48;

    public static void main(String[] args) {

        // takes in input
        Scanner in = new Scanner(System.in);

        // number of test cases
        int T = in.nextInt();

        // for every test case
        for(int t = 0; t<T; t++){

            // take in the number as a character array
            char[] chars = in.next().toCharArray();

            // convert the character array to integers
            int[] digits = new int[chars.length];
            for(int i = 0; i<chars.length; i++){
                digits[i] = chars[i] - ASCII_CONVERSION;
            }

            // track the index of the first untidy digit
            int u = -1;

            // track the index of the last time N[d] > N[d-1]
            // (the last digit that can be decreased leaving N tidy)
            int d = 0;

            // go through all digits, or until an untidy digit is found
            for(int i=0; i<digits.length-1 && u == -1; i++){

                // if a digit is untidy, store it's index
                if(digits[i] > digits[i+1]){
                    u = i;
                }

                // if a digit is decreasable, track it
                else if(digits[i] < digits[i+1]){
                    d = i+1;
                }
            }

            // if an untidy digit is found
            if(u != -1) {

                // decrease the first decreasable digit
                digits[d]--;

                // replace all subsequent digits with 9s
                for (int i = d + 1; i < digits.length; i++) {
                    digits[i] = 9;
                }

            }

            // output the answer
            System.out.print("Case #"+(t+1)+": ");

            for(int digit: digits){

                // ignoring any leading 0's
                if(digit > 0){
                    System.out.print(digit);
                }
            }

            System.out.println();
        }


    }
}
