/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class KdTree {

    private Node root;
    private int N;

    private static class Node {

        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect, Node lb, Node rt) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }
    }

    public KdTree() // construct an empty set of points
    {
        this.root = null;
        this.N = 0;
    }

    public boolean isEmpty() // is the set empty?
    {
        return size() == 0;
    }

    public int size() // number of points in the set
    {
        return N;
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null) {
            throw new java.lang.NullPointerException("insert() has null argument");
        }
        root = insert(root, p, true);
    }

    private Node insert(Node n, Point2D p, boolean isVertical) {
        if (n == null) {
            N++;
            return new Node(p, null, null, null);
        } else if (isVertical) {
            Double nodeX = n.p.x();
            Double pX = p.x();

            int cmp = pX.compareTo(nodeX);
            if (cmp < 0) {
                n.lb = insert(n.lb, p, !isVertical);
            } else {
                n.rt = insert(n.rt, p, !isVertical);
            }

        } else {
            Double nodeY = n.p.y();
            Double pY = p.y();

            int cmp = pY.compareTo(nodeY);
            if (cmp < 0) {
                n.lb = insert(n.lb, p, !isVertical);
            } else {
                n.rt = insert(n.rt, p, !isVertical);
            }
        }
        return n;
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null) {
            throw new java.lang.NullPointerException("contains() has null argument");
        }
        return contains(root, p, true);
    }

    private boolean contains(Node n, Point2D p, boolean isVertical) {
        if (n == null) {
            return false;
        } else if (p.equals(n.p)) {
            return true;
        } else if (isVertical) {
            Double nodeX = n.p.x();
            Double pX = p.x();

            int cmp = pX.compareTo(nodeX);
            if (cmp < 0) {
                return contains(n.lb, p, !isVertical);
            } else {
                return contains(n.rt, p, !isVertical);
            }

        } else {
            Double nodeY = n.p.y();
            Double pY = p.y();

            int cmp = pY.compareTo(nodeY);
            if (cmp < 0) {
                return contains(n.lb, p, !isVertical);
            } else {
                return contains(n.rt, p, !isVertical);
            }
        }
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
        String filename = "C:\\Users\\Borui Wang\\Desktop\\Borui Wang\\Coursera\\part1_week5\\kdtree-testing\\kdtree\\circle4.txt";
        In in = new In(filename);
        // initialize the two data structures with point from standard input
        KdTree kd = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kd.insert(p);
        }

        StdOut.println(kd.contains(new Point2D(0.0 , 0.53 )));
    }
}
