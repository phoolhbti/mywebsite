package com.pc.test.cqutil.product;

public class Product {
 
    public static final String PROP_SERIAL = "serial";
    public static final String PROP_PRICE = "price";
    public static final String PROP_CATEGORY = "category";
    public static final String PROP_IMG_URL = "imgURL";
 
    private String path;
    private String serial;
    private Double price;
    private String category;
    private String imgURL;
 
    public String getPath() {
        return path;
    }
 
    public void setPath(String path) {
        this.path = path;
    }
 
    public String getSerial() {
        return serial;
    }
 
    public void setSerial(String serial) {
        this.serial = serial;
    }
 
    public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
     
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
 
    public String getImgURL() {
        return imgURL;
    }
 
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
 
}