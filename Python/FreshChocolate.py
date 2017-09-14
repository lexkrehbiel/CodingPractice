#
# Fresh Chocolate by Google Code Jam:
#     https://code.google.com/codejam/contest/5314486/dashboard
#
# Solution:
#   Generate a count of number of groups per modulo value of groupSize % P : [0,P)
#   Group these counts in the maximum number of groups that generate a 0 modulo value


import math

# input number of test cases
T = input()

# if necessary, this could have been generated in the code
# however, I decided to hard code it, since it's so short.
groups = {2:[[0],[1,1]],3:[[0],[2,1],[1,1,1]],4:[[0],[3,1],[2,2],[2,1,1],[1,1,1,1]]}

# for every test case
for t in range(T):

    # input number of groups, number of pieces
    [N,P] = map( int, raw_input().split() )

    # take in the group sizes
    groupSizes = map( int, raw_input().split() )

    # store lists of the modulos of group numbers
    moduloCounts = [0 for n in range(P)]

    # count the number groups found for each modulo value
    for size in groupSizes:
        mod = size % P
        moduloCounts[mod] +=  1

    # track the groups counted
    count = 0

    # eliminate groups that make an exact match
    for gt in groups[P]:
        divisors = [len([g for g in gt if g == i]) for i in range(0,P)]
        # print(["ON: ",gt])
        # print(["DIVS:",divisors])
        values = [int(moduloCounts[g]/divisors[g]) for g in gt]
        # print(["MODS: ",moduloCounts])
        found = min(values)
        count += found
        for g in gt:
            moduloCounts[g] -= found
        # print(["FOUND: ",found])
        # print(["MODS: ",moduloCounts])

    # Count every P'th leftover
    leftovers = sum(moduloCounts)
    count += leftovers/P
    if leftovers % P > 0:
        count += 1


    print("Case #"+str(t+1)+": "+str(count))
