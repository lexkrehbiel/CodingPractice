package CodeJam;

import java.util.Scanner;

public class OversizedPancakeFlipper {

    /*
    * Oversized Pancake Flipper by Google Code Jam:
    *   https://code.google.com/codejam/contest/3264486/dashboard
    *
    * Solution
    *   While l+k < n, Always flip the pancakes l through l+k
    *       where
    *           l is the leftmost '-' pancake
    *           k is the size of the flipper
    *           n is the number of pancakes
    *
    *   If all pancakes are + after this, output the flip count
    *   If < k pancakes are '-', output impossible
    *
    * */

    public static void main(String[] args) {

        // initialize input
        Scanner in = new Scanner(System.in);

        // take in number of test cases
        int T = in.nextInt();

        // iterate through test cases
        for(int j = 0; j<T; j++){

            // take in the character array of pancakes
            char[] chars = in.next().toCharArray();

            // take in the size of the flipper
            int k = in.nextInt();

            // convert the character array to booleans
            // where + is true and - is false
            boolean[] happy = new boolean[chars.length];
            for(int i = 0; i<chars.length; i++){
                happy[i] = chars[i] == '+';
            }

            // track the leftmost '-'
            int l = 0;

            // track the number of flips
            int count = 0;

            // note that solution is unknown
            // (unnecessary - just to prevent warnings)
            String solution = "unknown";

            // while a valid flip is possible
            // (we are k pancakes from the end of the pancakes)
            while(l+k < happy.length){

                // move forward toward the leftmost '-'
                while(l < happy.length && happy[l]) l++;

                // if all were found to be happy,
                // (we found no '-' before the end of the pancakes)
                // give the count
                if(l==happy.length){
                    solution = Integer.toString(count);
                }

                // if there's a number less than k left to be flipped,
                // (we're almost at the end of the pancakes, and can't flip k)
                // say impossible
                else if(l > happy.length-k) {
                    solution = "IMPOSSIBLE";
                }

                // flip k pancakes starting at l
                else {

                    for(int i = l; i<l+k; i++){
                        happy[i] = !happy[i];
                    }

                    // note the flip
                    count++;

                }
            }

            // output the solution
            System.out.println("Case #"+(j+1)+": "+solution);
        }


    }
}
