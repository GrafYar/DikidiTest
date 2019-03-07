package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<ArrayList<ItemList>> mList;
    private ArrayList<ItemList> mShares;
    private ArrayList<ItemList> mCategory;
    private ArrayList<ItemList> mCatalog;
    private String mCatalogCount;
    private CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener mCatalogItemClickListener;
    private CatalogViewHolder.CatalogButtonMoreClickListener mCatalogButtonMoreClickListener;
    private CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;

    public MainAdapter ( Context context, ArrayList<ArrayList<ItemList>> news, ArrayList<ItemList> shares,
                         ArrayList<ItemList> category, ArrayList<ItemList> catalog, String catalogCount,
                         CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener catalogItemClickListener,
                         CatalogViewHolder.CatalogButtonMoreClickListener catalogButtonMoreClickListener,
                         CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener categoryItemClickListener) {
        mContext = context;
        mList = news;
        mShares = shares;
        mCategory = category;
        mCatalog = catalog;
        mCatalogCount = catalogCount;
        mCatalogItemClickListener = catalogItemClickListener;
        mCatalogButtonMoreClickListener = catalogButtonMoreClickListener;
        mCategoryItemClickListener = categoryItemClickListener;
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
                holder = new CategoryViewHolder(view, mCategoryItemClickListener);
                break;
            case ItemList.CATALOG_TYPE:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new CatalogViewHolder(view, mCatalogItemClickListener, mCatalogButtonMoreClickListener);
                break;
            default:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new CatalogViewHolder(view, mCatalogItemClickListener, mCatalogButtonMoreClickListener);
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

        SharesAdapter sharesAdapter = new SharesAdapter(mShares);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(sharesAdapter);

    }

    private void categoryView(CategoryViewHolder holder){

        CategoryAdapter categoryAdapter = new CategoryAdapter(mCategory, mCategoryItemClickListener);
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.HORIZONTAL,false));
        holder.mRecyclerView.setAdapter(categoryAdapter);

    }

    private void catalogView(CatalogViewHolder holder){

        CatalogAdapter catalogAdapter = new CatalogAdapter(mCatalog, mCatalogItemClickListener);
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

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView mCountCategories;
        private RecyclerView mRecyclerView;
        CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;

        private CategoryViewHolder(@NonNull View itemView, CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener categoryItemClickListener) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.category_recycler_view);
            mCountCategories = itemView.findViewById(R.id.category_count_categories);
            mCategoryItemClickListener = categoryItemClickListener;
        }

    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mCountCatalog;
        private Button mCatalogButtonMore;
        private CatalogButtonMoreClickListener mCatalogButtonMoreClickListener;
        private RecyclerView mRecyclerView;
        CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener mClickItemListener;

        private CatalogViewHolder(@NonNull View itemView, CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener catalogItemClickListener, CatalogButtonMoreClickListener catalogButtonMoreClickListener) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.catalog_recycler_view);
            mCountCatalog = itemView.findViewById(R.id.catalog_count_catalogs);
            mCatalogButtonMore = itemView.findViewById(R.id.catalog_button_more);
            mCatalogButtonMoreClickListener = catalogButtonMoreClickListener;
            this.mClickItemListener = catalogItemClickListener;
            mCatalogButtonMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mCatalogButtonMoreClickListener!= null){
                mCatalogButtonMoreClickListener.onCatalogButtonMoreClickListener(getAdapterPosition());
            }
        }

        public interface CatalogButtonMoreClickListener {
            void onCatalogButtonMoreClickListener (int position);
        }
    }

}
