package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder>{
    Context mContext;
    ArrayList<ItemList> mList = new ArrayList();

    public MainCategoryAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MainCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new MainCategoryAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainCategoryAdapter.MyViewHolder holder, int i) {

        MainListRes.Category item = (MainListRes.Category) mList.get(i);
        MainCategoryAdapter.MyViewHolder mHolder = (MyViewHolder) holder;
        mHolder.mCategoryName.setText(item.getName());

        Picasso.with(mContext)
                .load(item.getImage())
                .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                .into(holder.mImage);

    }

    @Override
    public int getItemViewType(int position) {
        // определяем какой тип в текущей позиции
        //int type = mList.get(position).getItemType();
//        if (type == 0) return HEADER;
//        else if (type == 1) return TYPE_ITEM1;
        return mList.get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mCategoryName;
        public MyViewHolder(View itemView){
            super(itemView);
            mCategoryName = (TextView) itemView.findViewById(R.id.category_name);
            mImage = itemView.findViewById(R.id.category_image);

        }
    }
}
