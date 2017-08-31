package com.dmitry.beans;

import com.dmitry.entities.Point;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ApplicationScoped
public class PointBean implements Serializable {

    private List <Point> listPoint;
    //array for output
    private List <Point> resultPoint;
    //date for result list
    private Date from, to;
    //for current date
    private Calendar date;

    public PointBean() {
        listPoint = new ArrayList<Point>();
        date = Calendar.getInstance();
    }

    public void generateArray() {
        if (listPoint.isEmpty()) {
            for (int i = 0; i < 50; i++) {
                date.add(Calendar.MONTH, 1);
                listPoint.add(new Point(i + 1, date.getTime(), i * 3, "message - " + (i + 1)));
            }
        }
    }

    /**
     * get result list
     * point from use date
     * point to use date
     */
    public void getResultSortedPoint() {
        resultPoint = new ArrayList<Point>();
        if (!listPoint.isEmpty()) {
            for (Point p : listPoint) {
                if (p.getDate().getTime() > from.getTime() && p.getDate().getTime() < to.getTime()) {
                    resultPoint.add(p);
                }
            }
        }
        Collections.sort(resultPoint);
    }

    public List<Point> getResultPoint() {
        return resultPoint;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void addPoint(Point point){
        boolean add =true;
        FacesMessage facesMessage;
        //dates in points should not be repeated
        for(Point pnt: listPoint){

            Calendar calNewDate = Calendar.getInstance();
            calNewDate.setTime(pnt.getDate());
            int yearNew = calNewDate.get(Calendar.YEAR);
            int monthNew = calNewDate.get(Calendar.MONTH);
            int dayNew = calNewDate.get(Calendar.DAY_OF_MONTH);

            Calendar calListDate = Calendar.getInstance();
            calListDate.setTime(point.getDate());
            int yearList = calListDate.get(Calendar.YEAR);
            int monthList = calListDate.get(Calendar.MONTH);
            int dayList = calListDate.get(Calendar.DAY_OF_MONTH);

            if(yearNew==yearList && monthNew==monthList && dayNew==dayList){
                add = false;
            }
        }
        if(add) {
            point.setId(listPoint.size() + 1);
            listPoint.add(point);
            facesMessage = new FacesMessage("Add new point");
        }
        else {
            facesMessage = new FacesMessage("A point with such a date already exists");
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    //delete all data
    public void clear(){
        listPoint = new ArrayList<Point>();
        resultPoint = new ArrayList<Point>();
    }
}
