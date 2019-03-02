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
import ru.dikidi.dikiditest.data.network.resources.UserListRes;
import ru.dikidi.dikiditest.data.network.resources.UserModelRes;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    Context mContext;
    List<UserListRes.New> mNews;
    int mi;

    public UsersAdapter (List<UserListRes.New> news) {
        mNews = news;
        int mi=0;
    }

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list,viewGroup, false);
        mi = 1;
        return new UserViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder userViewHolder, int i) {

        UserListRes.New mNew = mNews.get(i);
        //userViewHolder.mFullName.setText(mNews.getData().getTitle());
        userViewHolder.mFullName.setText(mNew.getName());

        mi = 1;
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        protected TextView mFullName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
        }
    }
}
