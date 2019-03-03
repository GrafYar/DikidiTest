package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatalogListRes {

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

    public void setImage(Image image) {
        this.image = image;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isIsMaster() {
        return isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public class Image {

        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("origin")
        @Expose
        private String origin;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
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

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getWorkFrom() {
            return workFrom;
        }

        public void setWorkFrom(String workFrom) {
            this.workFrom = workFrom;
        }

        public String getWorkTo() {
            return workTo;
        }

        public void setWorkTo(String workTo) {
            this.workTo = workTo;
        }

    }

}
