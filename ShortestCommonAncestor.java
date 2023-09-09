import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ShortestCommonAncestor {

    // creates Digraph
    private Digraph digraph;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        DirectedCycle dc = new DirectedCycle(G);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i) == 0) {
                counter += 1;
            }
        }
        if (counter > 1) {
            throw new IllegalArgumentException();
        }
        this.digraph = new Digraph(G);
    }

    // calculates sca and distance
    private int[] scaCalculations(int v, int w, int[] array) {
        if (v < 0 || v > digraph.V()) {
            throw new IllegalArgumentException();
        }
        if (w < 0 || w > digraph.V()) {
            throw new IllegalArgumentException();
        }
        BreadthFirstDirectedPaths vSearch = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths wSearch = new BreadthFirstDirectedPaths(digraph, w);
        Stack<Integer> commonAncestors = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (vSearch.hasPathTo(i) && wSearch.hasPathTo(i)) {
                commonAncestors.push(i);
            }
        }
        int shortest = -1;
        int shortestNode = -1;
        for (Integer i : commonAncestors) {
            int currDistance = vSearch.distTo(i) + wSearch.distTo(i);
            if (shortest == -1) {
                shortest = currDistance;
                shortestNode = i;
            }
            else {
                if (currDistance < shortest) {
                    shortest = currDistance;
                    shortestNode = i;
                }
            }
        }
        array[0] = shortest;
        array[1] = shortestNode;
        return array;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        int[] answers = new int[2];
        return scaCalculations(v, w, answers)[0];
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {
        int[] answers = new int[2];
        return scaCalculations(v, w, answers)[1];
    }

    // calculates sca and distance
    private int[] scaSubsetCalculations(Iterable<Integer> subsetA,
                                        Iterable<Integer> subsetB,
                                        int[] array) {
        if (subsetA == null || subsetB == null) {
            throw new IllegalArgumentException();
        }
        if (!subsetA.iterator().hasNext() || !subsetB.iterator().hasNext()) {
            throw new IllegalArgumentException();
        }
        for (Integer i : subsetA) {
            if (i == null) {
                throw new IllegalArgumentException();
            }
        }
        for (Integer i : subsetB) {
            if (i == null) {
                throw new IllegalArgumentException();
            }
        }
        BreadthFirstDirectedPaths subsetASearch = new
                BreadthFirstDirectedPaths(digraph, subsetA);
        BreadthFirstDirectedPaths subsetBSearch = new
                BreadthFirstDirectedPaths(digraph, subsetB);
        Stack<Integer> commonAncestors = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (subsetASearch.hasPathTo(i) && subsetBSearch.hasPathTo(i))
                commonAncestors.push(i);
        }
        int shortest = -1;
        int shortestNode = -1;
        for (Integer i : commonAncestors) {
            int currDistance = subsetASearch.distTo(i) + subsetBSearch.distTo(i);
            if (shortest == -1) {
                shortest = currDistance;
                shortestNode = i;
            }
            else {
                if (currDistance < shortest) {
                    shortest = currDistance;
                    shortestNode = i;
                }
            }
        }
        array[0] = shortest;
        array[1] = shortestNode;
        return array;
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        int[] answers = new int[2];
        return scaSubsetCalculations(subsetA, subsetB, answers)[0];
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        int[] answers = new int[2];
        return scaSubsetCalculations(subsetA, subsetB, answers)[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        for (int i = 0; i < G.V(); i++) {
            for (int j = 0; j < G.V(); j++) {
                int length = sca.length(i, j);
                int ancestor = sca.ancestor(i, j);
                StdOut.printf("node1=" + i + " node2=" + j + " length = %d, "
                                      + "ancestor = "
                                      + "%d\n", length, ancestor);
            }
        }
        Stack<Integer> test1 = new Stack<>();
        test1.push(13);
        test1.push(23);
        test1.push(24);
        Stack<Integer> test2 = new Stack<>();
        test2.push(6);
        test2.push(16);
        test2.push(17);
        StdOut.println(sca.lengthSubset(test1, test2));
        StdOut.println(sca.ancestorSubset(test1, test2));
    }
}

