package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogAdapterViewHolder>{

    private ArrayList<ItemList> mList;
    private CatalogAdapterViewHolder.CatalogItemClickListener mCatalogItemClickListener;


    protected CatalogAdapter(ArrayList<ItemList> list, CatalogAdapterViewHolder.CatalogItemClickListener catalogItemClickListener){
        mList = list;
        this.mCatalogItemClickListener = catalogItemClickListener;
    }

    @NonNull
    @Override
    public CatalogAdapter.CatalogAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalog, viewGroup,false);
        return new CatalogAdapter.CatalogAdapterViewHolder(view, context, mCatalogItemClickListener, mList);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.CatalogAdapterViewHolder holder, int position) {
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

    public static class CatalogAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ArrayList<ItemList> mList;
        private AspectRatioImageView mImageThumb, mImageAdv;
        private TextView mName, mRating, mStreetHouse, mCategories;
        private RatingBar mRatingBar;
        private TextView mAdvTextView, mAdvName, mAdvStreetHouse, mAdvCategories;
        private CatalogItemClickListener mCatalogItemClickListener;
        private Context mContext;

        private CatalogAdapterViewHolder(View itemView, Context context, CatalogItemClickListener catalogItemClickListener, ArrayList<ItemList> list){
            super(itemView);
            mContext = context;
            mList = list;
            mCatalogItemClickListener = catalogItemClickListener;

            //TODO: Использовать ButterKnife

            mImageThumb = itemView.findViewById(R.id.catalog_image_thumb);
            mName = itemView.findViewById(R.id.catalog_name);
            mRating = itemView.findViewById(R.id.catalog_rating);
            mStreetHouse = itemView.findViewById(R.id.catalog_street_house);
            mCategories = itemView.findViewById(R.id.catalog_categories);
            mRatingBar = itemView.findViewById(R.id.catalog_rating_bar);
            mImageAdv = itemView.findViewById(R.id.adv_image_thumb);

            mAdvTextView = itemView.findViewById(R.id.adv_txt_view);
            mAdvName = itemView.findViewById(R.id.adv_catalog_name);
            mAdvStreetHouse = itemView.findViewById(R.id.adv_catalog_street_house);
            mAdvCategories = itemView.findViewById(R.id.adv_catalog_categories);

            itemView.setOnClickListener(this);

        }

        //Method for choosing what to show inside catalog item: advertising or catalog item.
        private void bind(CatalogListRes item) {

            if(!isAdvertising(item)) {
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
                mStreetHouse.setText(item.getStreet() + ", " + item.getHouse());
                mCategories.setText(categories);

                mImageThumb.setVisibility(View.VISIBLE);
                mName.setVisibility(View.VISIBLE);
                mRating.setVisibility(View.VISIBLE);
                mStreetHouse.setVisibility(View.VISIBLE);
                mCategories.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);

                mImageAdv.setVisibility(View.GONE);
                mAdvTextView.setVisibility(View.GONE);
                mAdvName.setVisibility(View.GONE);
                mAdvStreetHouse.setVisibility(View.GONE);
                mAdvCategories.setVisibility(View.GONE);
            }
            else {
                mImageThumb.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                mStreetHouse.setVisibility(View.GONE);
                mCategories.setVisibility(View.GONE);

                mRating.setText("5,0");
                mRatingBar.setRating(5);

                mRating.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);
                mImageAdv.setVisibility(View.VISIBLE);
                mAdvTextView.setVisibility(View.VISIBLE);
                mAdvName.setVisibility(View.VISIBLE);
                mAdvStreetHouse.setVisibility(View.VISIBLE);
                mAdvCategories.setVisibility(View.VISIBLE);
            }
        }

        private boolean isAdvertising(CatalogListRes item) {
            if(item.getAdvertising()==null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void onClick(View v) {
            if(mCatalogItemClickListener!= null){
                CatalogListRes item = (CatalogListRes) mList.get(getAdapterPosition());
                mCatalogItemClickListener.onCatalogItemClickListener(isAdvertising(item));
            }
        }

        public interface CatalogItemClickListener {
            void onCatalogItemClickListener (boolean isAdv);
        }
    }
}
