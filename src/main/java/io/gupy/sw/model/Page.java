package io.gupy.sw.model;

import java.util.List;

public class Page {

    private Integer count;
    private String next;
    private String previous;
    private List<Planet> results;
    
    public Page() {}

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Planet> getResults() {
        return results;
    }

    public void setResults(List<Planet> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Page [count=" + count + ", next=" + next + ", previous=" + previous + "]";
    }
}