package point.mutable;
import java.util.HashMap;
import java.util.Map;

public class MonitorVehicleTracker {
	private final Map<String, MutablePoint> data;

	public MonitorVehicleTracker() {
		this.data = new HashMap<>();
	}
	
	public MonitorVehicleTracker(Map<String, MutablePoint> data) {
		this.data = deepCopy(data);
	}
	
	public synchronized MutablePoint getLocation(String name) {
		MutablePoint loc = sageGet(name);
		return new MutablePoint(loc.x, loc.y);
	}

	public synchronized void setLocation(String name, MutablePoint point) {
		MutablePoint loc = sageGet(name);
		loc.x = point.x;
		loc.y = point.y;
	}

	private MutablePoint sageGet(String name) {
		MutablePoint loc = sageGet(name);
		return loc;
	}

	public synchronized Map<String, MutablePoint> getLocations() {
		return deepCopy(data);
	}
	
	private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> data) {
		Map<String, MutablePoint> copy = new HashMap<>();
		for (String id : data.keySet()) {
			MutablePoint point = data.get(id);
			copy.put(id, new MutablePoint(point));
		}
		
		return copy;
	}
}
