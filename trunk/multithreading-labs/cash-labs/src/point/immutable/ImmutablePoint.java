package point.immutable;

public class ImmutablePoint {
	final public double x, y;

	public ImmutablePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public ImmutablePoint(ImmutablePoint point) {
		this(point.x, point.y);
	}
	
	
}
