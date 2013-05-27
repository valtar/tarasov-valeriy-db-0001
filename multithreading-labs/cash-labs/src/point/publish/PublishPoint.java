package point.publish;


public class PublishPoint {
	private double x, y;

	public PublishPoint(double x, double y) {
		set(x,y);
	}
	
	public PublishPoint(PublishPoint point) {
		this(point.x, point.y);
	}
	
	public synchronized void set(double x, double y){
		this.set(x, y);
	}

	public synchronized double [] getCoordinatesXY(){
		return new double[] {x,y};
	}
	


}
