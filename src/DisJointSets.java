//Author: Shounak Gujarathi
//Disjoint sets are used to find the shortest path using Kruskal's algorithm and also can be used in Graphs to detect loops, in real life
// Time Complexity: if m is number of operations and n is number of nodes, TC = O(m*alpha(n)). However, value of alpha(n) is
// a slowly growing function and for most cases alpha(m)<=5. So TC = O(4m) = O(n).

import java.util.HashMap;

public class DisJointSets {
    //Node
    class Node{
        Node parent;
        long data;
        int rank;
    }

    private HashMap<Integer, Node> h = new HashMap<>();

    private void makeSet(Integer data){
        Node n = new Node();
        n.parent = n;
        n.data = data;
        n.rank = 0;
        h.put(data, n);
    }

    private Node findSet(Node n){
        Node parent = n.parent;
        if(parent.equals(n))
            return n;
        else
            n.parent = findSet(parent.parent);
        return n.parent;
    }

    private int findSet(int n){
        return (int)findSet(h.get(n)).data;
    }

    private boolean union(int n1, int n2){
        Node node1 = h.get(n1);
        Node node2 = h.get(n2);
        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);
        if(parent1.equals(parent2))
            return false;
        else{
            if(parent1.rank>=parent2.rank){
                parent1.rank = (parent1.rank == parent2.rank)?parent1.rank+1:parent1.rank;
                parent2.parent=parent1;
            }else{
                parent1.parent=parent2;
            }
            return true;
        }
    }

    public static void main(String args[]) {
        DisJointSets ds = new DisJointSets();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}
