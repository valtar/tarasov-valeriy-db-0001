package point.mutable;

public class MutablePoint {
	double x, y;

	public MutablePoint(double x, double y) {
		set(x, y);
	}
	
	public MutablePoint(MutablePoint point) {
		this(point.x, point.y);
	}
	
	public void set(double x2, double y2){
		this.x = x2;
		this.y = y2;
	}
	
	
}
