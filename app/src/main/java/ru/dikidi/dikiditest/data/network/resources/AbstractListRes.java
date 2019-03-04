package ru.dikidi.dikiditest.data.network.resources;

public abstract class AbstractListRes {

    public static final int CATEGORY_TYPE = 222;
    public static final int SHARES_TYPE = 1;
    public static final int CATALOG_TYPE = 2;

    public AbstractListRes() {
    }

    abstract public int getType();

}

