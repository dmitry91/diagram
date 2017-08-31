package com.dmitry.entities;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import java.util.Date;

/**
 * data storage object
 */
@ManagedBean(name="point")
public class Point implements Comparable<Point> {
    private int id;
    private Date date;
    private int value;
    private String message;

    public Point() {
    }

    public Point(int id, Date date, int value, String message) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (id != point.id) return false;
        if (value != point.value) return false;
        if (date != null ? !date.equals(point.date) : point.date != null) return false;
        return message != null ? message.equals(point.message) : point.message == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + value;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Point o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", date=" + date +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
