import java.util.*;

public class Googlements {

    public static HashMap<String, Boolean> runPerms = new HashMap<>();;

    /*
    * Googlements by Google Code Jam:
    *   https://code.google.com/codejam/contest/8304486/dashboard
    *
    * Anything where the sum of the digits is equal to the length can't have been a product of decomp
    * assuming that's the case, run all permutations.
    * store all permutations run.
    * */

    // count the potential recompositions of this googlement
    public static void doRecompositionVersions(String googlement){

      // if we already tested this googlement, don't count it
      if( runPerms.containsKey(googlement) ) return;

      // make note that we'll counted this permutation
      runPerms.put(googlement,true);

      // find the sum of the digits and the length of the googlement
      int digitSum = digitSum(googlement);
      int length = googlement.length();

      // if this could not have been the product of a decomposition,
      // it is the only version, so the count is 1
      if(digitSum > length) return;

      // count this permutation
      int totalDecompositions = 1;

      // construct what the element could decay from
      String reconstruction = reconstruct(googlement);

      // add trailing zeros
      while( reconstruction.length() < length){
        reconstruction += "0";
      }

      // generate all permutations of this string
      List<String> permutations = permutations("",reconstruction);

      // run the permutations possible from all
      for(String perm: permutations){
        doRecompositionVersions(perm);
      }

    }

    // sum the digits of a string
    public static int digitSum(String elm){

      char[] chars = elm.toCharArray();
      int total = 0;
      for(char c: chars){
        total += (c-48);
      }

      return total;

    }

    // reconstruct the element that could decompose into
    // this element given the digit counts
    public static String reconstruct(String elm){
      String ans = "";
      for(int i = 0; i<elm.length(); i++){
        String dig = Integer.toString(i+1);
        int count = elm.charAt(i)-48;
        for(int j = 0; j<count; j++){
          ans += dig;
        }
      }
      return ans;
    }

    // generate all permutations of the string
    private static List<String> permutations(String prefix, String str) {
        List<String> perms = new ArrayList<>();
        int n = str.length();
        if (n == 0) perms.add(prefix);
        else {
            for (int i = 0; i < n; i++){
              List<String> newPerms = permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
              perms.addAll(newPerms);
            }
        }
        return perms;
    }


    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        // get # test cases
        int T = in.nextInt();

        // for each test case
        for(int t = 1; t<=T; t++){

            // get the number
            String input = in.next();

            // clear the
            runPerms.clear();

            // generate the set of recompositions
            doRecompositionVersions(input);

            // output the solution
            System.out.println("Case #"+(t)+": "+runPerms.size());

        }
    }
}
