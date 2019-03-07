package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.ui.activities.ItemCatalogActivity;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<ArrayList<ItemList>> mList;
    private ArrayList<ItemList> mShares;
    private ArrayList<ItemList> mCategory;
    private ArrayList<ItemList> mCatalog;
    private MainCatalogAdapter.MyViewHolder.CatalogClickListener mCatalogClickListener;
    String mCatalogCount;

    public MainAdapter ( Context context, ArrayList<ArrayList<ItemList>> news, ArrayList<ItemList> shares, ArrayList<ItemList> category, ArrayList<ItemList> catalog, String catalogCount, MainCatalogAdapter.MyViewHolder.CatalogClickListener catalogClickListener) {
        mContext = context;
        mList = news;
        mShares = shares;
        mCategory = category;
        mCatalog = catalog;
        mCatalogCount = catalogCount;
        this.mCatalogClickListener = catalogClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder;

        switch (i){
            case ItemList.SHARES_TYPE:
                view = mLayoutInflater.inflate(R.layout.shares_recycler_view, viewGroup, false);
                holder = new SharesViewHolder(view);
                break;
            case ItemList.CATEGORY_TYPE:
                view = mLayoutInflater.inflate(R.layout.category_recycler_view, viewGroup, false);
                holder = new CategoryViewHolder(view);
                break;
            case ItemList.CATALOG_TYPE:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new CatalogViewHolder(view, mCatalogClickListener);
                break;
            default:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new CatalogViewHolder(view, mCatalogClickListener);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        switch (holder.getItemViewType()) {
                case ItemList.SHARES_TYPE:
                    sharesView ((SharesViewHolder) holder);
                    ((SharesViewHolder) holder).mCountShares.setText(String.format(Locale.getDefault(), "%d", mShares.size()));
                    break;
                case ItemList.CATEGORY_TYPE:
                    categoryView ((CategoryViewHolder) holder);
                    ((CategoryViewHolder) holder).mCountCategories.setText(String.format(Locale.getDefault(), "%d", mCategory.size()));
                    break;
                case ItemList.CATALOG_TYPE:
                    catalogView ((CatalogViewHolder) holder);
                    ((CatalogViewHolder) holder).mCountCatalog.setText(mCatalogCount);
                    break;
        }

    }

    private void sharesView(SharesViewHolder holder){

        MainSharesAdapter sharesAdapter = new MainSharesAdapter(mShares);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(sharesAdapter);

    }

    private void categoryView(CategoryViewHolder holder){

        MainCategoryAdapter categoryAdapter = new MainCategoryAdapter(mCategory);
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.HORIZONTAL,false));
        holder.mRecyclerView.setAdapter(categoryAdapter);

    }

    private void catalogView(CatalogViewHolder holder){

        MainCatalogAdapter catalogAdapter = new MainCatalogAdapter(mCatalog, mCatalogClickListener);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mRecyclerView.setAdapter(catalogAdapter);

    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SharesViewHolder extends RecyclerView.ViewHolder {

        private TextView mCountShares;
        private RecyclerView mRecyclerView;

        private SharesViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.shares_recycler_view);
            mCountShares = itemView.findViewById(R.id.shares_count_shares);
        }

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mCountCategories;
        private RecyclerView mRecyclerView;

        private CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.category_recycler_view);
            mCountCategories = itemView.findViewById(R.id.category_count_categories);
        }

    }

    public class CatalogViewHolder extends RecyclerView.ViewHolder {

        MainCatalogAdapter.MyViewHolder.CatalogClickListener mClickListener;

        private TextView mCountCatalog;
        private RecyclerView mRecyclerView;

        private CatalogViewHolder(@NonNull View itemView, MainCatalogAdapter.MyViewHolder.CatalogClickListener catalogClickListener) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.catalog_recycler_view);
            mCountCatalog = itemView.findViewById(R.id.catalog_count_catalogs);
            this.mClickListener = catalogClickListener;
        }

    }

}
