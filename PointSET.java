/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 *
 * @author Borui Wang
 */
public class PointSET {

    public PointSET() // construct an empty set of points
    {

    }

    public boolean isEmpty() // is the set empty?
    {
        return false;
    }

    public int size() // number of points in the set
    {
        return -1;
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if(p == null)
            throw new java.lang.NullPointerException("Insert() has null argument");
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if(p == null)
            throw new java.lang.NullPointerException("contains() has null argument");
        return false;
    }

    public void draw() // draw all points to standard draw
    {

    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if(rect == null)
            throw new java.lang.NullPointerException("range() has null argument");
        return null;
    }

    public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if(p == null)
            throw new java.lang.NullPointerException("nearest() has null argument");
        return null;
    }

    public static void main(String[] args) // unit testing of the methods (optional)
    {

    }
}
