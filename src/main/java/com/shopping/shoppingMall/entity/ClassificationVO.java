package com.shopping.shoppingMall.entity;

import java.util.Date;

public class ClassificationVO {
    private Integer id;

    private String className;

    private String describeText;

    private Date addDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    @Override
    public String toString() {
        return "ClassificationVO{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", describeText='" + describeText + '\'' +
                ", addDate='" + addDate + '\'' +
                '}';
    }
}