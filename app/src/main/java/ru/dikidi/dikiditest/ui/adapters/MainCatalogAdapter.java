package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;

public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.MainCatalogViewHolder>{

    Context mContext;
    List<CatalogListRes> mNews;

    public MainCatalogAdapter (List<CatalogListRes> news) {
        mNews = news;
    }

    @NonNull
    @Override
    public MainCatalogAdapter.MainCatalogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list, viewGroup, false);
        return new MainCatalogAdapter.MainCatalogViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCatalogAdapter.MainCatalogViewHolder userViewHolder, int i) {

        CatalogListRes mNew = mNews.get(i);
        userViewHolder.mFullName.setText(mNew.getName());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public static class MainCatalogViewHolder extends RecyclerView.ViewHolder {

        protected TextView mFullName;

        public MainCatalogViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
        }
    }

}
