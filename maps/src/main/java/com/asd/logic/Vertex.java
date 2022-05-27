package com.asd.logic;

public class Vertex {

    long key;
    boolean finished;
    Vertex parent;
    int num;
    String placeName;

    public Vertex(int num, String placeName) {
        this.num = num;
        key = Integer.MAX_VALUE;
        finished = false;
        this.placeName = placeName;
    }

    public Vertex(Vertex vertex) {
        this.key = vertex.key;
        this.finished = vertex.finished;
        this.parent = vertex.parent;
        this.num = vertex.num;
    }

    public long getKey() {
        return this.key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Vertex getParent() {
        return this.parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

}
