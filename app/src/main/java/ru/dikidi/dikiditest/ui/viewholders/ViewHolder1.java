package ru.dikidi.dikiditest.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.dikidi.dikiditest.R;

public class ViewHolder1 extends RecyclerView.ViewHolder {

    public TextView mFullName;

    public ViewHolder1(@NonNull View itemView) {
        super(itemView);

        mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
    }

    public TextView getText() {
        return mFullName;
    }

    public void setText(TextView mFullName) {
        this.mFullName = mFullName;
    }

}
