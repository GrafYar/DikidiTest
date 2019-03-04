package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MainListRes extends AbstractListRes{

    @SerializedName("data")
    @Expose
    private Data data;

//    int type;
//
//    @Override
//    public int getItemType() {
//        return type;
//    }

    @Override
    public int getType() {
        return CATALOG_TYPE;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

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

    public class Blocks{

        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("shares")
        @Expose
        private SharesListRes shares = null;
        @SerializedName("catalog")
        @Expose
        private List<CatalogListRes> catalog = null;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public SharesListRes getShares() {
            return shares;
        }

        public void setShares(SharesListRes shares) {
            this.shares = shares;
        }

        public List<CatalogListRes> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<CatalogListRes> catalog) {
            this.catalog = catalog;
        }

    }

    public class Category implements ItemList {

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

        public void setImage(String image) {
            this.image = image;
        }

    }



}
