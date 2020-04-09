package app.model;

public class NodeValueResult {
    public long window_start;
    public int max_value;
    public int min_value;
    public int count;
    public int total;

    public NodeValueResult(){

    }

    public NodeValueResult(int max_value, int min_value, int count, int total){
        this.max_value = max_value;
        this.min_value = min_value;
        this.count = count;
        this.total = total;
    }

    public String toString() {
        return String.format("max_value: %d  min_value: %d count: %d total %d", max_value, min_value, count, total);
    }

    public NodeValueResult decorateWindowStart(long timestamp){
        window_start = timestamp;
        return this;
    }
}
