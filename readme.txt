Programming Assignment 6: WordNet

/* *****************************************************************************
 *  Please take a moment now to fill out the mid-semester survey:
 *  https://forms.gle/diTbj5r4o4xXbJm89
 *
 *  If you're working with a partner, please do this separately.
 *
 *  Type your initials below to confirm that you've completed the survey.
 **************************************************************************** */



/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */

To store the information in synsets.txt, we used 2 different HashMaps. The
first HashMap stored the nouns as keys, and the values being a stack of
integers representing the possible ids that the noun has. The second hashmap
stores the ids as keys and the values being complete synsets as strings. We
made this choice because HashMaps are relatively efficient,can go through
keys quickly, and objects in WordNet need to have an id and a string associated
with them, so a hashmap was the best choice because you can have a key and
value for an object.

/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */

To store the information in hypernyms.txt, we used a digraph with the size
being the amount of synsets in synsets.txt We made this choice because to find
the shortest common ancestor it is easy to do with a digraph, because you can
do a breadth first search on the digraph of synsets/hypernyms.

/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify
 *  your answer.
 **************************************************************************** */

Description: The algorithm we use in the constructor of ShortestCommonAncestor
checks if the given digraph G is null, if so: throw an IllegalArgumentException.
We then create a DirectedCycle object and check if the graph G has a cycle,
if so: throw an IllegalArgumentException. We then check the amount of vertices
with an outdegree of zero (roots) and if there are more than 1: throw an
IllegalArgumentException. Finally if there is no IllegalArgumentException
thrown, then we create a copy of G, and store it as this.Digraph.


Order of growth of running time: O(E + V)


/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify your
 *  answers.
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description: We use a helper method called scaCalculations to calculate the
closest node between two points and the distance to that node. The way the
helper method works is by first checking for exceptions if the two given
points are not in the digraph. It then creates two BreadthFirstDirectedPaths
objects, one for the first search node, and one for the second. We then use
those objects to find all the common ancestors between the two nodes and add
those to a stack. We then loop through the stack and find the distance for
each ancestor to both search nodes. We have a variable keeping track of the
shortest node and shortest distance and update it accordingly. We then return
an int[] array of size 2, with the shortest distance as index 0 and the
shortest node as index 1. We index the return value of the helper method as
index 0 for length(), and index 1 for ancestor(). We create a helper method
for the subset calculations, and do something very similar, but check for
more exceptions and pass the two search subsets as parameters when creating
the BreadthFirstDirectedPaths objects.


                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                0(E + V)              O(E + V)

ancestor()              0(E + V)              O(E + V)

lengthSubset()          0(E + V)              O(E + V)

ancestorSubset()        0(E + V)              O(E + V)



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

None.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */

Professor Han's office hours and Darius's office hours.

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

None.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

Dillon Remuck: Helped implement code and conceptualize ideas for the code.
Abigail Wilson: Helped implement code and conceptualize ideas for the code.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

We both enjoyed the project.
