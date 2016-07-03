/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_5;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
 
/**
 *
 * @author Borui Wang
 */
public class PointSET {
	private SET<Point2D> points;
	
    public PointSET() // construct an empty set of points
    {
    	points = new SET<Point2D>();
    }

    public boolean isEmpty() // is the set empty?
    {
        return points.isEmpty();
    }

    public int size() // number of points in the set
    {
        return points.size();
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if(p == null)
            throw new java.lang.NullPointerException("Insert() has null argument");
        points.add(p);
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if(p == null)
            throw new java.lang.NullPointerException("contains() has null argument");
        return points.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
         for(Point2D point:points){
        	 point.draw();
         }
    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if(rect == null)
            throw new java.lang.NullPointerException("range() has null argument");
        
        Queue<Point2D> queue = new Queue<Point2D>();
        for(Point2D point:points){
        	if(rect.contains(point)){
        		queue.enqueue(point);
        	}
        }
        return queue;
    }

    public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if(p == null)
            throw new java.lang.NullPointerException("nearest() has null argument");
        if(isEmpty())
        	return null;
        
        double shortest = Double.POSITIVE_INFINITY;
        Point2D shortestP = null;
        
        for(Point2D point: points){
        	if(p.equals(point))
        		return point;
        	double distance = p.distanceTo(point);
        	if(shortest > distance){
        		shortest = distance;
        		shortestP = point;
        	}
        }
        return shortestP;
    }

    public static void main(String[] args) // unit testing of the methods (optional)
    {
    	 String filename = "C:\\Users\\Borui Wang\\Desktop\\Borui Wang\\Coursera\\part1_week5\\kdtree-testing\\kdtree\\circle4.txt";
         In in = new In(filename);

 
         // initialize the two data structures with point from standard input
         PointSET brute = new PointSET();
          while (!in.isEmpty()) {
             double x = in.readDouble();
             double y = in.readDouble();
             Point2D p = new Point2D(x, y);
             brute.insert(p);
         }
          brute.draw();
          brute.nearest(new Point2D(0.99,0.5));
    }
}