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
import ru.dikidi.dikiditest.ui.viewholders.ViewHolder1;
import ru.dikidi.dikiditest.ui.viewholders.ViewHolder2;

public class MainCatalogAdapter extends RecyclerView.Adapter<ViewHolder2>{

    Context mContext;
    List<CatalogListRes> mNews;

    public MainCatalogAdapter (List<CatalogListRes> news) {
        mNews = news;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        mContext = viewGroup.getContext();
//        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list, viewGroup, false);
//        return new ViewHolder2(convertView);

        ViewHolder2 viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v1 = inflater.inflate(R.layout.item_user_list, viewGroup, false);
        viewHolder = new ViewHolder2(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 userViewHolder, int i) {

//        CatalogListRes mNew = mNews.get(i);
//        userViewHolder.mFullName.setText(mNew.getName());

        ViewHolder2 vh1 = (ViewHolder2) userViewHolder;
        configureViewHolder1(vh1, i);
    }

    private void configureViewHolder1(ViewHolder2 userViewHolder, int i) {
        CatalogListRes mNew = mNews.get(i);
        userViewHolder.getText().setText(mNew.getName());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

//    public static class MainCatalogViewHolder extends RecyclerView.ViewHolder {
//
//        protected TextView mFullName;
//
//        public MainCatalogViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
//        }
//    }

}
