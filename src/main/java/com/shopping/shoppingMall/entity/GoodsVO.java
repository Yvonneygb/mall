package com.shopping.shoppingMall.entity;

public class GoodsVO {
    private Integer id;

    private Integer goodsClassId;

    private String goodsName;

    private String image;

    private Integer price;

    private String link;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }
}