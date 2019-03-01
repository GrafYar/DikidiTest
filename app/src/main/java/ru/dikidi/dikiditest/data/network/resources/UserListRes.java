package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserListRes {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("blocks")
        @Expose
        private Blocks blocks;
        @SerializedName("catalog_count")
        @Expose
        private String catalogCount;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Blocks getBlocks() {
            return blocks;
        }

        public void setBlocks(Blocks blocks) {
            this.blocks = blocks;
        }

        public String getCatalogCount() {
            return catalogCount;
        }

        public void setCatalogCount(String catalogCount) {
            this.catalogCount = catalogCount;
        }

    }

    public class Blocks {




        @SerializedName("new")
        @Expose
        private List<New> list_new;

//        @SerializedName("new")
//        @Expose
//        private New _new;
        @SerializedName("examples")
        @Expose
        private String examples;

        public List<New> getNew() {
            return list_new;
        }

        public void setNew(List<New> _new) {
            this.list_new = _new;
        }

        public String getExamples() {
            return examples;
        }

        public void setExamples(String examples) {
            this.examples = examples;
        }

    }

    public class New {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
//        @SerializedName("image")
//        @Expose
//        private String image;
//        @SerializedName("street")
//        @Expose
//        private String street;
//        @SerializedName("house")
//        @Expose
//        private String house;
//        @SerializedName("rating")
//        @Expose
//        private String rating;
//        @SerializedName("distance")
//        @Expose
//        private String distance;

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
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getStreet() {
//            return street;
//        }
//
//        public void setStreet(String street) {
//            this.street = street;
//        }
//
//        public String getHouse() {
//            return house;
//        }
//
//        public void setHouse(String house) {
//            this.house = house;
//        }
//
//        public String getRating() {
//            return rating;
//        }
//
//        public void setRating(String rating) {
//            this.rating = rating;
//        }
//
//        public String getDistance() {
//            return distance;
//        }
//
//        public void setDistance(String distance) {
//            this.distance = distance;
//        }

    }

}
