package ru.dikidi.dikiditest.data.network.resources;

public interface ItemList {

    int CATEGORY_TYPE = 0;
    int SHARES_TYPE = 1;
    int CATALOG_TYPE = 2;

    abstract public int getItemType();

}
