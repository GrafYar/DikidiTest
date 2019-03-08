package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// POJO model for Catalor resources from REST API
public class CatalogListRes implements ItemList{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("house")
    @Expose
    private String house;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("isMaster")
    @Expose
    private boolean isMaster;
    @SerializedName("advertising")
    @Expose
    private String advertising;

    @Override
    public int getItemType() {
        return CATALOG_TYPE;
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

    public Image getImage() {
        return image;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getAdvertising() {
        return advertising;
    }

    public class Image {

        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("origin")
        @Expose
        private String origin;

        public String getOrigin() {
            return origin;
        }

    }

    public class Schedule {

        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("work_from")
        @Expose
        private String workFrom;
        @SerializedName("work_to")
        @Expose
        private String workTo;

    }

}
