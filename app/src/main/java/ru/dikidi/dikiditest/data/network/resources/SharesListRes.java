package ru.dikidi.dikiditest.data.network.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// POJO model for Shares resources from REST API
public class SharesListRes {

        @SerializedName("list")
        @Expose
        private List<ListShares> list = null;
        @SerializedName("count")
        @Expose
        private String count;

        public List<ListShares> getList() {
            return list;
        }

    public class ListShares implements ItemList{

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


        @Override
        public int getItemType() {
            return SHARES_TYPE;
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

        public String getIcon() {
            return icon;
        }

        public String getTimeStop() {
            return timeStop;
        }

        public String getDiscountValue() {
            return discountValue;
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

        public String getCompanyName() {
            return companyName;
        }

        public String getCompanyStreet() {
            return companyStreet;
        }

        public String getCompanyHouse() {
            return companyHouse;
        }

        public String getCompanyImage() {
            return companyImage;
        }

    }

}
