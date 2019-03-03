package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MainListRes {

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
        @SerializedName("catalog_count")
        @Expose
        private String catalogCount;
        @SerializedName("blocks")
        @Expose
        private Blocks blocks;

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

        public String getCatalogCount() {
            return catalogCount;
        }

        public void setCatalogCount(String catalogCount) {
            this.catalogCount = catalogCount;
        }

        public Blocks getBlocks() {
            return blocks;
        }

        public void setBlocks(Blocks blocks) {
            this.blocks = blocks;
        }

    }

    public class Blocks {

        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("shares")
        @Expose
        private Shares shares;
        @SerializedName("catalog")
        @Expose
        private List<Catalog> catalog = null;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public Shares getShares() {
            return shares;
        }

        public void setShares(Shares shares) {
            this.shares = shares;
        }

        public List<Catalog> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<Catalog> catalog) {
            this.catalog = catalog;
        }

    }

    public class Category {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;

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

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class Shares {

        @SerializedName("list")
        @Expose
        private List<ListShares> list = null;
        @SerializedName("count")
        @Expose
        private String count;

        public List<ListShares> getList() {
            return list;
        }

        public void setList(List<ListShares> list) {
            this.list = list;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

    }

    public class ListShares {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("time_start")
        @Expose
        private String timeStart;
        @SerializedName("time_stop")
        @Expose
        private String timeStop;
        @SerializedName("discount_value")
        @Expose
        private String discountValue;
        @SerializedName("view")
        @Expose
        private String view;
        @SerializedName("used_count")
        @Expose
        private String usedCount;
        @SerializedName("company_id")
        @Expose
        private String companyId;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("company_street")
        @Expose
        private String companyStreet;
        @SerializedName("company_house")
        @Expose
        private String companyHouse;
        @SerializedName("company_image")
        @Expose
        private String companyImage;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeStop() {
            return timeStop;
        }

        public void setTimeStop(String timeStop) {
            this.timeStop = timeStop;
        }

        public String getDiscountValue() {
            return discountValue;
        }

        public void setDiscountValue(String discountValue) {
            this.discountValue = discountValue;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(String usedCount) {
            this.usedCount = usedCount;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyStreet() {
            return companyStreet;
        }

        public void setCompanyStreet(String companyStreet) {
            this.companyStreet = companyStreet;
        }

        public String getCompanyHouse() {
            return companyHouse;
        }

        public void setCompanyHouse(String companyHouse) {
            this.companyHouse = companyHouse;
        }

        public String getCompanyImage() {
            return companyImage;
        }

        public void setCompanyImage(String companyImage) {
            this.companyImage = companyImage;
        }

    }

    public class Catalog {

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
