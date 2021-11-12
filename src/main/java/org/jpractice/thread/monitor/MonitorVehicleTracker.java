/**
 * 
 */
package org.jpractice.thread.monitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-18 12:42:59
 * @Description: TODO
 * @version V1.0
 */
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> localtions;

    public MonitorVehicleTracker(Map<String, MutablePoint> localtions) {
        this.localtions = deepCopy(localtions);
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

}
