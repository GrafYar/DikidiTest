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
import java.util.Locale;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;


public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.MyViewHolder>{

    Context mContext;
    ArrayList<ItemList> mList = new ArrayList();

    public MainCatalogAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MainCatalogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //       if(i == ItemList.CATEGORY_TYPE) {
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_catalog,viewGroup,false);
        return new MainCatalogAdapter.MyViewHolder(view);
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainCatalogAdapter.MyViewHolder holder, int i) {
//        if(getItemViewType(i) == ItemList.SHARES_TYPE) {
        CatalogListRes item = (CatalogListRes) mList.get(i);
        if(item.getAdvertising()==null) {
            Picasso.with(mContext)
                    .load(item.getImage().getOrigin())
                    .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                    .into(holder.mImageThumb);
            holder.mName.setText(item.getName());
            holder.mRating.setText(String.format(Locale.getDefault(), "%f", item.getRating()));
            holder.mStreetHouse.setText(item.getStreet() + ", " + item.getHouse());
        }
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
        ImageView mImageThumb;
        TextView mName, mRating, mStreetHouse;
        public MyViewHolder(View itemView){
            super(itemView);

            mImageThumb = itemView.findViewById(R.id.catalog_image_thumb);
            mName = (TextView) itemView.findViewById(R.id.catalog_name);
            mRating = (TextView) itemView.findViewById(R.id.catalog_rating);
            mStreetHouse = itemView.findViewById(R.id.catalog_street_house);

        }
    }
}
