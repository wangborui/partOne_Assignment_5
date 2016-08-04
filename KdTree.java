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
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class KdTree {

    private Node root;
    private int N;
    private static final boolean LB = true;
    private static final boolean RT = false;

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
        root = insert(root, p, null, true,false);
    }

    private Node insert(Node n, Point2D p, Node prevNode, boolean isVertical, boolean isLB) {
    	int cmp;
    	//insert a new node
    	//public    RectHV(double xmin, double ymin, double xmax, double ymax)      
    	// construct the rectangle [xmin, xmax] x [ymin, ymax] 
    	 if (n == null) {
    		//root node is null
	    	if(prevNode == null){
	    		RectHV rect = new RectHV(0,0,1,1);
	    		return new Node(p, rect, null, null);
	    	}
	    	else if(isVertical){
            	RectHV rect = new RectHV(
            			 prevNode.rect.xmin()
            			,isLB?prevNode.rect.ymin():prevNode.p.y()
            			,prevNode.rect.xmax()
            			,isLB?prevNode.p.y():prevNode.rect.ymax());
            	return new Node(p, rect, null, null);
            }else{
            	RectHV rect = new RectHV(
            			isLB?prevNode.rect.xmin():prevNode.p.x()
            			,prevNode.rect.ymin()
            			,isLB?prevNode.p.x():prevNode.rect.xmax()
            			,prevNode.rect.ymax());
            	return new Node(p, rect, null, null);
            }
        } else if (isVertical) {
            Double nodeX = n.p.x();
            Double pX = p.x();
            cmp = pX.compareTo(nodeX);
        } else {
            Double nodeY = n.p.y();
            Double pY = p.y();
            cmp = pY.compareTo(nodeY);
        }
        if (cmp < 0) {
            n.lb = insert(n.lb, p,n, !isVertical,LB);
        } else {
            n.rt = insert(n.rt, p,n, !isVertical,RT);
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
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        
        draw(root,true);
    }
    
    private void draw(Node n, boolean isVertical){
   
        
        if(n != null){
            //draw the point first
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            n.p.draw();
            StdDraw.text(n.p.x(), n.p.y(), ""+n.p);
            //draw the separating line
            if(isVertical){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.001);
                StdDraw.line(n.p.x(), n.rect.ymin() , n.p.x(), n.rect.ymax());
            }
            else{
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(.001);
                StdDraw.line(n.rect.xmin(), n.p.y() , n.rect.xmax(), n.p.y());
            }
            draw(n.lb,!isVertical);
            draw(n.rt,!isVertical);
        }
    }
    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if (rect == null) {
            throw new java.lang.NullPointerException("range() has null argument");
        }
       Queue<Point2D> queue = new Queue();
       range(root, rect, queue);
       return queue;
    }
    private Iterable<Point2D> range(Node n, RectHV rect, Queue<Point2D> it){
        if(n == null)
            return it;
        
        if(rect.contains(n.p))
            it.enqueue(n.p);
        
        range(n.lb,rect,it);
        range(n.rt,rect,it);
        return it;
    }
  // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (root == null) return null;
        return nearest(p, root, root.p, true);
    }
    private Point2D nearest(Point2D queryPoint, Node node, Point2D champion, boolean compareX) {
        if (node == null) return champion;
        // if node point is closer than champion, it becomes the new champion
        if (queryPoint.distanceSquaredTo(node.p) < queryPoint.distanceSquaredTo(champion)) {
            champion = node.p;
        }
        // only explore node if it can contain a point closer than the champion
        if (node.rect.distanceSquaredTo(queryPoint) < queryPoint.distanceSquaredTo(champion)) {
            // query point is left or below node point
            if ((compareX && queryPoint.x() < node.p.x()) ||
                    (!compareX && queryPoint.y() < node.p.y())) {
                // explore left bottom first
                StdOut.println("left " +"node point: "+node.p+ " queryPoint: " + queryPoint +" node rect: "+ node.rect +" champion: "+ champion + " compareX: "+compareX);
                champion = nearest(queryPoint, node.lb, champion, !compareX);
                StdOut.println("right " +"node point: "+node.p+  " queryPoint: " + queryPoint +" node rect: "+ node.rect +" champion: "+ champion + " compareX: "+compareX);
                champion = nearest(queryPoint, node.rt, champion, !compareX);
            } else {
                // query point is right, above, or equal to node point
                // explore right top first
                StdOut.println("right " +"node point: "+node.p+  " queryPoint: " + queryPoint +" node rect: "+ node.rect +" champion: "+ champion + " compareX: "+compareX);
                champion = nearest(queryPoint, node.rt, champion, !compareX);
                StdOut.println("left " +"node point: "+node.p+  " queryPoint: " + queryPoint +" node rect: "+ node.rect +" champion: "+ champion + " compareX: "+compareX);
                champion = nearest(queryPoint, node.lb, champion, !compareX);
            }
//            StdOut.println("right");
//            champion = nearest(queryPoint, node.rt, champion, !compareX);
//            StdOut.println("left");
//                champion = nearest(queryPoint, node.lb, champion, !compareX);
        }
        return champion;
    }
    public static void main(String[] args) // unit testing of the methods (optional)
    {
        String filename = "C:\\Users\\Borui Wang\\Desktop\\Borui Wang\\Coursera\\part1_week5\\kdtree-testing\\kdtree\\circle10.txt";
        In in = new In(filename);
        // initialize the two data structures with point from standard input
        KdTree kd = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kd.insert(p);
        }

         kd.draw();
//        RectHV test = new RectHV(0.0,0.0,0.6,0.9);
//        StdOut.println(kd.range(test));
        StdOut.println("Query Point: (0.9, 0.3) nearest: " + kd.nearest(new Point2D(0.9,0.3)));
     }
}
