package ru.dikidi.dikiditest.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.dikidi.dikiditest.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder userViewHolder, int i) {

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
