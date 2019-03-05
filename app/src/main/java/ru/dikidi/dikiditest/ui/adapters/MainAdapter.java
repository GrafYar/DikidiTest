package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
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

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<ArrayList<ItemList>> mList;
    private ArrayList<ItemList> mShares;
    private ArrayList<ItemList> mCategory;
    private ArrayList<ItemList> mCatalog;

    public MainAdapter ( Context context, ArrayList<ArrayList<ItemList>> news, ArrayList<ItemList> shares, ArrayList<ItemList> category, ArrayList<ItemList> catalog) {
        mContext = context;
        mList = news;
        mShares = shares;
        mCategory = category;
        mCatalog = catalog;
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
                holder = new ViewHolder1(view);
                break;
            case ItemList.CATEGORY_TYPE:
                view = mLayoutInflater.inflate(R.layout.category_recycler_view, viewGroup, false);
                holder = new ViewHolder2(view);
                break;
            case ItemList.CATALOG_TYPE:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new ViewHolder3(view);
                break;
            default:
                view = mLayoutInflater.inflate(R.layout.catalog_recycler_view, viewGroup, false);
                holder = new ViewHolder3(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        switch (holder.getItemViewType()) {
                case ItemList.SHARES_TYPE:
                    sharesView ((ViewHolder1) holder);
                    ((ViewHolder1) holder).mCountShares.setText(String.format(Locale.getDefault(), "%d", mShares.size()));
                    break;
                case ItemList.CATEGORY_TYPE:
                    categoryView ((ViewHolder2) holder);
                    break;
                case ItemList.CATALOG_TYPE:
                    catalogView ((ViewHolder3) holder);
                    break;
        }

    }

    private void sharesView(ViewHolder1 holder){

        MainSharesAdapter sharesAdapter = new MainSharesAdapter(mShares);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(sharesAdapter);

    }

    private void categoryView(ViewHolder2 holder){

        MainCategoryAdapter categoryAdapter = new MainCategoryAdapter(mCategory);
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.HORIZONTAL,false));

        holder.mRecyclerView.setAdapter(categoryAdapter);

    }

    private void catalogView(ViewHolder3 holder){

        MainCatalogAdapter catalogAdapter = new MainCatalogAdapter(mCatalog);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mRecyclerView.setAdapter(catalogAdapter);

    }


    @Override
    public int getItemViewType(int position) {
        // определяем какой тип в текущей позиции
        //int type = mList.get(position).getItemType();
//        if (type == 0) return HEADER;
//        else if (type == 1) return TYPE_ITEM1;
        return mList.get(position).get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView mCountShares;
        private RecyclerView mRecyclerView;

        private ViewHolder1(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.shares_recycler_view);
            mCountShares = itemView.findViewById(R.id.shares_count_shares);
        }

    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView mFullName;
        private RecyclerView mRecyclerView;

        private ViewHolder2(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.category_recycler_view);
        }

    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {

        private TextView mFullName;
        private RecyclerView mRecyclerView;

        private ViewHolder3(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.catalog_recycler_view);
        }

    }

}
