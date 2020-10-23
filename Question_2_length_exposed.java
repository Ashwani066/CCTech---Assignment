
import java.util.*;

public class Question_2_length_exposed {
	private static Scanner sc = new Scanner(System.in);
	

	static Double findDist(List<Point> v, Point sun) {
		int n = v.size();
		int m = 2;
		double X = sun.x;
		double Y = sun.y;

		Collections.sort(v, new Comp());

		// for(Point it : v) {
		// 	System.out.println(it.x + " " + it.y);
		// }
		
		Double ans = 0.0;
		ans += Math.abs(v.get(0).y - v.get(1).y);

        double mx = 0.0;
        
        //top surfaces of building
		for(int i=1; i<n-1; i+=3) {
			if(Y >= v.get(i).y && v.get(i).y >= mx) {
				ans += v.get(i + 1).x - v.get(i).x;
				mx = Math.max(mx, v.get(i).y);
			}
		}

        mx = 0.0;
        //  Vertical height facing sun
        for(int i=5; i<n; i+=4){
            if(i+1 < n && v.get(i+1).x >= mx && v.get(i+1).x >= Y){
                ans+= Math.abs(v.get(i+1).y- v.get(i-1).y);
                mx = Math.max(mx, v.get(i+1).y);
            }
        }

        mx = 0.0;
        //slant height due to sun position
		for(int i=5; i<n; i+=4) {
			if(i + 1 < n && v.get(i + 1).y >= mx && v.get(i+1).y > v.get(i-1).y){
                double x, y;
                x = v.get(i-1).x - X;
                y = v.get(i-1).y - Y;
                double dist = v.get(i).x - v.get(i-1).x;
                double mo = (y/x);

                ans += Math.tan(Math.toRadians(mo)) * dist;
                mx = Math.max(mx, v.get(i+1).y);
            }
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int n = sc.nextInt();
		
		List<Point> v = new ArrayList<>();
		
		for(int i=0; i<4*n; ++i) {
			Point pt = new Point(sc.nextDouble(), sc.nextDouble());
			v.add(pt);
		}

		Point sun = new Point(sc.nextDouble(), sc.nextDouble());
		Double ans = findDist(v, sun);

		System.out.println((double)ans);
	}
}

class Point {
	Double x, y;
	
	Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
}

class Comp implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
		if(p1.x.compareTo(p2.x) == 0) return p1.y.compareTo(p2.y);
		return p1.x.compareTo(p2.x);
	}
}