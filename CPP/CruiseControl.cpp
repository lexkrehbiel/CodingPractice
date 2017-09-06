
/*// # Cruise Control by Google Code Jam:
    https://code.google.com/codejam/contest/8294486/dashboard

Solution:
    For every horse, determine the time at which it will pass D : [t = (D-Ki)/Si]
    Save the longest of these long_t
    Make Annie's speed such that she arrives at the exact same time :  D/long_t
    O(n) time, O(1) space
*/

#include <iostream>
#include <iomanip>      // std::setprecision

using namespace std;

int main() {

    // input # test cases
    int T; cin >> T;

    // for every test case
    for(int t = 1; t<=T; t++){

      // input distance
      double D; cin >> D;

      // input # horses
      double N; cin >> N;

      // save the longest time taken for any horse to reach D
      double longestTime = 0;

      // for every horse
      for(int n = 0; n<N; n++){

        // input horse's initial position and speed
        double K; cin >> K;
        double S; cin >> S;

        // determine when this horse will pass D
        double timeForThisHorse = (D-K)/S;

        // save this as the longest time if it's longer than
        // any time seen
        if(timeForThisHorse > longestTime){
          longestTime = timeForThisHorse;
        }
      }

      // output the speed Annie would have to go to arrive at the same time as the last horse
      cout << "Case #" << t << ": " << setprecision(10) << D/(float)longestTime << "\n";
    }

    return 0;
}
