package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;


public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.MyViewHolder>{

    Context mContext;
    View mView;
    ViewGroup mViewGroup;
    ArrayList<ItemList> mList = new ArrayList();

    public MainCatalogAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MainCatalogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

//        mViewGroup = viewGroup;
//
//        if(viewType == ItemList.CATEGORY_TYPE) {
//        mContext = viewGroup.getContext();
//
//        mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_catalog,viewGroup,false);
//        return new MainCatalogAdapter.MyViewHolder(mView);
//        }

        mViewGroup = viewGroup;


        mContext = mViewGroup.getContext();

        mView = LayoutInflater.from(mContext).inflate(R.layout.item_catalog,mViewGroup,false);
        return new MainCatalogAdapter.MyViewHolder(mView);


    }

    @Override
    public void onBindViewHolder(@NonNull MainCatalogAdapter.MyViewHolder holder, int position) {

        CatalogListRes item = (CatalogListRes) mList.get(position);
//        if(item.getAdvertising()==null) {
//            Picasso.with(mContext)
//                    .load(item.getImage().getOrigin())
//                    .error(mContext.getResources().getDrawable(R.drawable.placeholder))
//                    .into(holder.mImageThumb);
//            holder.mName.setText(item.getName());
//            holder.mRating.setText(String.format(Locale.getDefault(), "%f", item.getRating()));
//            holder.mStreetHouse.setText(item.getStreet() + ", " + item.getHouse());
//
//
//
//
//        }
//        else {
            holder.bind(item);
//        }

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
        AspectRatioImageView mImageThumb, mImageAdv;
        TextView mName, mRating, mStreetHouse;
        Button mBut;
        public MyViewHolder(View itemView){
            super(itemView);

            mImageThumb = itemView.findViewById(R.id.catalog_image_thumb);
            mName = (TextView) itemView.findViewById(R.id.catalog_name);
            mRating = (TextView) itemView.findViewById(R.id.catalog_rating);
            mStreetHouse = itemView.findViewById(R.id.catalog_street_house);

            mImageAdv = itemView.findViewById(R.id.adv_image_thumb);
            mBut = itemView.findViewById(R.id.adv_button);

        }

        public void bind(CatalogListRes item) {



            if(item.getAdvertising()==null) {
                Picasso.with(mContext)
                        .load(item.getImage().getOrigin())
                        .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                        .into(mImageThumb);

                mName.setText(item.getName());
                mRating.setText(String.format(Locale.getDefault(), "%f", item.getRating()));
                mStreetHouse.setText(item.getStreet() + ", " + item.getHouse());

                mImageThumb.setVisibility(View.VISIBLE);
                mName.setVisibility(View.VISIBLE);
                mRating.setVisibility(View.VISIBLE);
                mStreetHouse.setVisibility(View.VISIBLE);

                mImageAdv.setVisibility(View.GONE);
                mBut.setVisibility(View.GONE);


            }
            else {

                mImageThumb.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                mRating.setVisibility(View.GONE);
                mStreetHouse.setVisibility(View.GONE);

                mImageAdv.setVisibility(View.VISIBLE);
                mBut.setVisibility(View.VISIBLE);

            }

        }
    }
}
