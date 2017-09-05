#
# Ratatouille by Google Code Jam:
#     https://code.google.com/codejam/contest/5304486/dashboard#s=p1
#
# Solution:
#     Store packages according to the number of servings in each package (one list per ingredient)
#     While there is at least one unused package in each list :
#         isolate the smallest number of servings in unused packages for each ingredient (we'll call this list smallest_servings)
#         if all of smallest_nums fit within the range to create a kit, count the kit and mark all of smallest_nums used
#         otherwise, drop the smallest of smallest_nums
#     I managed this with sorted lists per ingredient and one cursor per list

import math

# input number of test cases
T = input()

# for every test case
for t in range(T):

    # input number of ingredients, number of packages
    [N,P] = map( int, raw_input().split() )

    # input amount of each ingredient for one serving
    servingAmounts = map(int, raw_input().split())

    # store lists of the number of servings in each package
    # one list per ingredient
    allServingAmounts = []

    # for each ingredient index
    for n in range(N):

        # input package amounts
        packageAmounts = map(int, raw_input().split())

        # determine the number of servings each package corresponds to by dividing
        # by the amount of this ingredient necessary for one serving
        servings = [ float(pkg_amt)/servingAmounts[n] for pkg_amt in packageAmounts]

        # sort increasingly
        servings.sort();

        # add to the list
        allServingAmounts.append(servings)

    # keep track of which package we are at in each list
    cursors = [0 for i in range(N)]

    # keep track of the number of kits made
    count = 0

    # while there is at least one valid package per ingredient
    while all( crs < P for crs in cursors) :

        # determine the smallest number of servings available for each ingredient
        # by design, this is the current cursor value for each ingredient!
        # store this with the index of that cursor, for easier manipulation later
        lowestServingNumbers = [(servingAmt[crs],i) for crs, servingAmt, i in zip(cursors, allServingAmounts, range(N))]

        # sort increasingly
        lowestServingNumbers.sort(key=lambda x: x[0])

        # determine the highest possible number of servings that can respond to the
        # smallest number of servings in this group
        highestPossible = 1.1*math.floor(lowestServingNumbers[0][0]/.9);

        # if the highest number of servings found is outside the valid range
        if lowestServingNumbers[-1][0] > highestPossible:

            # move only the lowest iterator forward
            # effectively, drop the package with the lowest number of servings
            lowestIteratorIndex = lowestServingNumbers[0][1]
            cursors[lowestIteratorIndex] = cursors[lowestIteratorIndex] + 1

        else : # we can make a package with this group!

            # move all cursors forward
            cursors = [x + 1 for x in cursors]
            # count the package created
            count = count + 1

    print("Case #"+str(t+1)+": "+str(count))
