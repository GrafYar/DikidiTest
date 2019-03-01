package ru.dikidi.dikiditest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.UserModelRes;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    Context mContext;
    UserModelRes mUser;

    public UsersAdapter (UserModelRes user) {
        mUser = user;
    }

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list,viewGroup, false);
        return new UserViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder userViewHolder, int i) {
        userViewHolder.mFullName.setText(mUser.getData().getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        protected TextView mFullName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
        }
    }
}
