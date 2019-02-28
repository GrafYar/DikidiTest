package ru.dikidi.dikiditest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class UserModelRes {

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

        }

}
