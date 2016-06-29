/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 *
 * @author Borui Wang
 */
public class KdTree {

    private Node root;
    
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    public KdTree() // construct an empty set of points
    {

    }

    public boolean isEmpty() // is the set empty?
    {
        return size() == 0;
    }

    public int size() // number of points in the set
    {
        return size(root);
    }
    private int size(Node n){
        if(n == null)
            return 0;
        else
            return size(n.lb) + 1 + size(n.rt);
    }
    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null) {
            throw new java.lang.NullPointerException("insert() has null argument");
        }
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null) {
            throw new java.lang.NullPointerException("contains() has null argument");
        }
        return false;
    }

    public void draw() // draw all points to standard draw
    {

    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if (rect == null) {
            throw new java.lang.NullPointerException("range() has null argument");
        }

        return null;
    }

    public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null) {
            throw new java.lang.NullPointerException("nearest() has null argument");
        }
        if (isEmpty()) {
            return null;
        }

        return null;
    }

    public static void main(String[] args) // unit testing of the methods (optional)
    {
        String filename = "C:\\Users\\boruiwang\\Desktop\\Borui Wang\\Interviews\\Coursera\\kdtree-testing\\kdtree\\circle4.txt";
        In in = new In(filename);
        // initialize the two data structures with point from standard input
        KdTree kd = new KdTree();
    }
}
