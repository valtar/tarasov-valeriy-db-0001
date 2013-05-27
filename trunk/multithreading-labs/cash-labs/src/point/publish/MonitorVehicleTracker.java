package point.publish;


import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import point.immutable.ImmutablePoint;

public class MonitorVehicleTracker {
	private final ConcurrentMap<String, PublishPoint> data;
	private final Map<String, PublishPoint> unmodifiableData;
	
	public MonitorVehicleTracker() {
		this.data = new ConcurrentHashMap<>();
		this.unmodifiableData = Collections.unmodifiableMap(this.data);
	}
	
	public MonitorVehicleTracker(Map<String, PublishPoint> data) {
		this.data = new ConcurrentHashMap<>(data);
		this.unmodifiableData = Collections.unmodifiableMap(this.data);
	}
	
	public  PublishPoint getLocation(String name) {
		return data.get(name);
	}

	public  void setLocation(String name, PublishPoint point) {
		if(data.replace(name, point) == null){
			throw new IllegalArgumentException();
		}
	}

	public  Map<String, PublishPoint> getLocations() {
		return unmodifiableData;
	}
}
