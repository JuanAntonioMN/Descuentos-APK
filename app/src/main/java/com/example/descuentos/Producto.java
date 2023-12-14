package com.example.descuentos;
import com.google.gson.annotations.SerializedName;
public class Producto {

    @SerializedName("title")
    String title;
    @SerializedName("price")
    String price;
    @SerializedName("original_price")
    String original_price;
    @SerializedName("discount_percentage")
    String discount_percentage;
    @SerializedName("thumbnail")
    String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(String discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
