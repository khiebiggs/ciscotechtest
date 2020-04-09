package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Average implements Serializable {

    public Average() {

    }

    public Average(long node_id, long window_start, int min_value, int max_value, int average_value){
        this.node_id = node_id;
        this.window_start = window_start;
        this.min_value = min_value;
        this.max_value = max_value;
        this.average_value = average_value;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String event_id;

    private long node_id;
    private long window_start;
    private int min_value;
    private int max_value;
    private int average_value;

    public long getNode_id() {
        return node_id;
    }

    public void setNode_id(long node_id) {
        this.node_id = node_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public long getWindow_start() {
        return window_start;
    }

    public void setWindow_start(long window_start) {
        this.window_start = window_start;
    }

    public int getMin_value() {
        return min_value;
    }

    public void setMin_value(int min_value) {
        this.min_value = min_value;
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getAverage_value() {
        return average_value;
    }

    public void setAverage_value(int average_value) {
        this.average_value = average_value;
    }
}
