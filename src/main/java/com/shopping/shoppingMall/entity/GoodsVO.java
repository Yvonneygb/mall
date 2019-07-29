package com.shopping.shoppingMall.entity;

import java.util.Date;

public class GoodsVO {
    private Integer id;

    private Integer goodsClassId;

    private String goodsClassName;

    private String goodsName;

    private String describeText;

    private String image;

    private Float price;

    private String link;

    private Date addDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(Integer goodsClassId) {
        this.goodsClassId = goodsClassId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getGoodsClassName() {
        return goodsClassName;
    }

    public void setGoodsClassName(String goodsClassName) {
        this.goodsClassName = goodsClassName;
    }

    @Override
    public String toString() {
        return "GoodsVO{" +
                "id=" + id +
                ", goodsClassId=" + goodsClassId +
                ", goodsClassName='" + goodsClassName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", addDate=" + addDate +
                '}';
    }
}