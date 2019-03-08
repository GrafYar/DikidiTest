package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// POJO model for main Data and Blocks from REST API
public class MainListRes{

    @SerializedName("data")
    @Expose
    private Data data;

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

        public String getCatalogCount() {
            return catalogCount;
        }

        public Blocks getBlocks() {
            return blocks;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }
    }

    public class Blocks{

        @SerializedName("categories")
        @Expose
        private List<CategoryListRes> categories = null;
        @SerializedName("shares")
        @Expose
        private SharesListRes shares = null;
        @SerializedName("catalog")
        @Expose
        private List<CatalogListRes> catalog = null;

        public List<CategoryListRes> getCategories() {
            return categories;
        }

        public SharesListRes getShares() {
            return shares;
        }

        public List<CatalogListRes> getCatalog() {
            return catalog;
        }

    }

}
