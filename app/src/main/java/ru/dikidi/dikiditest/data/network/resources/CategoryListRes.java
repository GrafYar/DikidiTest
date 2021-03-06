package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO model for Category resources from REST API
public class CategoryListRes implements ItemList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;

    @Override
    public int getItemType() {
        return CATEGORY_TYPE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

}
