package point.immutable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MonitorVehicleTracker {
	private final ConcurrentMap<String, ImmutablePoint> data;
	private final Map<String, ImmutablePoint> unmodifiableData;
	
	public MonitorVehicleTracker() {
		this.data = new ConcurrentHashMap<>();
		this.unmodifiableData = Collections.unmodifiableMap(this.data);
	}
	
	public MonitorVehicleTracker(Map<String, ImmutablePoint> data) {
		this.data = new ConcurrentHashMap<>(data);
		this.unmodifiableData = Collections.unmodifiableMap(this.data);
	}
	
	public  ImmutablePoint getLocation(String name) {
		return data.get(name);
	}

	public  void setLocation(String name, ImmutablePoint point) {
		if(data.replace(name, point) == null){
			throw new IllegalArgumentException();
		}
	}

	public  Map<String, ImmutablePoint> getLocations() {
		return unmodifiableData;
	}

}
