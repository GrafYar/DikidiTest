package ru.dikidi.dikiditest.data.network.resources;

// Interface for resources from REST API
public interface ItemList {

    int SHARES_TYPE = 0;
    int CATEGORY_TYPE = 1;
    int CATALOG_TYPE = 2;

    int getItemType();

}
