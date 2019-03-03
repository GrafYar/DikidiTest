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
import ru.dikidi.dikiditest.data.network.resources.MainListRes;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    Context mContext;
    List<MainListRes.Category> mNews;
    int mi;

    public MainAdapter (List<MainListRes.Category> news) {
        mNews = news;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list, viewGroup, false);
        mi = 1;
        return new MainAdapter.MainViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder userViewHolder, int i) {

        MainListRes.Category mNew = mNews.get(i);
        userViewHolder.mFullName.setText(mNew.getName());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        protected TextView mFullName;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
        }
    }
}
