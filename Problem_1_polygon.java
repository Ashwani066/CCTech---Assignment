package CCtech;

import java.io.*;
import java.util.*;

//  Answer is in Class Solution.

/*
    ######### INPUT ##############

    Take the input as Input1 : n -> the number of edges of Polygon.
    Then for the next n lines : x, y (Two space seperated double values) -> Co-ordinates of each point.

    Then next Last contains the Target Co-ordinates P : x, y (two space seperated double values).

*/

//  Main class.
public class Problem_1_polygon {

    // ------------------ Driver Code ----------

    public static void main(String[] args) throws Exception {

        FastReader in = new FastReader();

        // Number of edges of polygon
        int n = in.nextInt();
        // Array of points of the polygon
        List<Point> polygon = new ArrayList<Point>();

        for (int i = 0; i < n; i++) {
            // X, Y point
            double x = in.nextDouble(), y = in.nextDouble();
            polygon.add(new Point(x, y));
        }

        Point p = new Point(in.nextDouble(), in.nextDouble());

        Solution ans = new Solution();

        boolean res = ans.isInside(polygon, p);
        if (res)
            // If found P inside.
            System.out.println("True");
        else
            // If P is outside.
            System.out.println("False");

    }

    // -------------------- END --------------------------------------
}

class Solution {

    // Main function that gives the solution.

    // Function to check whether the point P is inside or not.
    public boolean isInside(List<Point> polygon, Point p) {

        int n = polygon.size();

        if (n < 3)
            return false;

        int counter = 0;

        for (int i = 0; i < n; i++) {
            Point a = polygon.get(i);
            Point b = polygon.get((i + 1) % n);

            // To check the boundary of the polygon.
            if (onBoundry(p, a, b))
                return true;

            // To check if the polygon is inside.
            if (checkInside(p, a, b))
                counter++;
        }

        // To check if the polygon is collinear.
        if (isCollinear(polygon))
            return false;

        // System.out.println(counter);
        boolean isOkay = false;
        if (counter % 2 != 0)
            isOkay = true;
        return isOkay;
    }

    // To check the colinearity of the polygon.
    static boolean isCollinear(List<Point> polygon) {
        int n = polygon.size();
        int localCounter = 0;
        double d = polygon.get(0).Y / polygon.get(0).X;
        for (int i = 1; i < n; i++) {
            double d2 = polygon.get(i).Y / polygon.get(i).X;
            if (d == d2) {
                localCounter++;
            }
        }

        if (localCounter == (n - 1))
            return true;
        return false;

    }

    // Check the point P if it is in the boundary of polygon.
    // If found return true else return false
    static boolean onBoundry(Point p, Point a, Point b) {
        double slope = (b.Y - a.Y) / (b.X - a.X);
        double x = (p.Y - a.Y) / slope + a.X;

        if (x == p.X)
            return true;
        return false;
    }

    // Check if the point is Strictly inside of the polygon.
    // If found retrun true else return false.
    static boolean checkInside(Point p, Point a, Point b) {

        double slope = (b.Y - a.Y) / (b.X - a.X);
        double x = (p.Y - a.Y) / slope + a.X;

        if (x > Math.min(a.X, b.X) && x < Math.max(a.X, b.X) && x > p.X)
            return true;
        return false;
    }
}

// Construction of a point.
class Point {
    Double X, Y;

    // Constructor of point class
    public Point(double x, double y) {
        this.X = x;
        this.Y = y;
    }
}

// Input Class
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
