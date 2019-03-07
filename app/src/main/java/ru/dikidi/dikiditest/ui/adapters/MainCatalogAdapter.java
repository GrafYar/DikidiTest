package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;


public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.MyViewHolder>{

   static Context mContext;
    View mView;
    ViewGroup mViewGroup;
    ArrayList<ItemList> mList = new ArrayList();
    MyViewHolder.CatalogItemClickListener mCatalogClickListener;


    public MainCatalogAdapter(ArrayList<ItemList> list, MyViewHolder.CatalogItemClickListener catalogClickListener){
        mList = list;
        this.mCatalogClickListener = catalogClickListener;
    }

    @NonNull
    @Override
    public MainCatalogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        mViewGroup = viewGroup;
        mContext = mViewGroup.getContext();

        mView = LayoutInflater.from(mContext).inflate(R.layout.item_catalog, mViewGroup,false);
        return new MainCatalogAdapter.MyViewHolder(mView, mCatalogClickListener);


    }

    @Override
    public void onBindViewHolder(@NonNull MainCatalogAdapter.MyViewHolder holder, int position) {

        CatalogListRes item = (CatalogListRes) mList.get(position);
            holder.bind(item);

    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AspectRatioImageView mImageThumb, mImageAdv;
        TextView mName, mRating, mStreetHouse, mCategories;
        RatingBar mRatingBar;
        Button mBut;
        CatalogItemClickListener mCatalogItemClickListener;
        LinearLayout mLnr;

        private MyViewHolder(View itemView, CatalogItemClickListener catalogItemClickListener){
            super(itemView);
            this.mCatalogItemClickListener = catalogItemClickListener;

            mImageThumb = itemView.findViewById(R.id.catalog_image_thumb);
            mName = itemView.findViewById(R.id.catalog_name);
            mRating = itemView.findViewById(R.id.catalog_rating);
            mStreetHouse = itemView.findViewById(R.id.catalog_street_house);
            mCategories = itemView.findViewById(R.id.catalog_categories);
            mRatingBar = itemView.findViewById(R.id.catalog_rating_bar);
            mImageAdv = itemView.findViewById(R.id.adv_image_thumb);
            mBut = itemView.findViewById(R.id.adv_button);
            mLnr = itemView.findViewById(R.id.lnr_catalog);
            mLnr.setOnClickListener(this);

        }

        private void bind(CatalogListRes item) {

            if(item.getAdvertising()==null) {
                String categories = "";
                for(String object : item.getCategories()) {
                    object += ", ";
                    categories += object;
                }

                Picasso.with(mContext)
                        .load(item.getImage().getOrigin())
                        .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                        .into(mImageThumb);

                mName.setText(item.getName());
                mRating.setText(new DecimalFormat("#.#").format(item.getRating()));
                mRatingBar.setRating((float)item.getRating());
               // mRating.setText(String.format(Locale.getDefault(), "%f", item.getRating()));
                mStreetHouse.setText(item.getStreet() + ", " + item.getHouse());
                mCategories.setText(categories);


                mImageThumb.setVisibility(View.VISIBLE);
                mName.setVisibility(View.VISIBLE);
                mRating.setVisibility(View.VISIBLE);
                mStreetHouse.setVisibility(View.VISIBLE);
                mCategories.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);

                mImageAdv.setVisibility(View.GONE);
                mBut.setVisibility(View.GONE);
            }
            else {

                mImageThumb.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                mRating.setVisibility(View.GONE);
                mStreetHouse.setVisibility(View.GONE);
                mRatingBar.setVisibility(View.GONE);

                mImageAdv.setVisibility(View.VISIBLE);
                mBut.setVisibility(View.VISIBLE);

            }



        }
        @Override
        public void onClick(View v) {
            if(mCatalogItemClickListener!= null){
                mCatalogItemClickListener.onCatalogItemClickListener(getAdapterPosition());
            }
        }


        public interface CatalogItemClickListener {
            void onCatalogItemClickListener (int position);

        }
    }
}
