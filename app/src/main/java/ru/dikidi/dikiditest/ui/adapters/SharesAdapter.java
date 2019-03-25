package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.SharesListRes;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;

public class SharesAdapter extends RecyclerView.Adapter<SharesAdapter.SharesAdapterViewHolder>{

    private static final String REST_RESPONSE_FORMAT="yyyy-MM-dd HH:mm:ss";
    private static final String MONTH_DAY_FORMAT = "d MMM";

    private ArrayList<ItemList> mList;
    private Context mContext;
    private SharesAdapterViewHolder.SharesItemClickListener mSharesItemClickListener;

    protected SharesAdapter(ArrayList<ItemList> list, SharesAdapterViewHolder.SharesItemClickListener sharesItemClickListener){
        mList = list;
        mSharesItemClickListener = sharesItemClickListener;
    }

    @NonNull
    @Override
    public SharesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shares,viewGroup,false);
        return new SharesAdapterViewHolder(view, mSharesItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SharesAdapterViewHolder holder, int i) {

        SharesListRes.ListShares item = (SharesListRes.ListShares) mList.get(i);

        Picasso.with(mContext)
                .load(item.getIcon())
                .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                .into(holder.mIcon);
        Picasso.with(mContext)
                .load(item.getCompanyImage())
                .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                .into(holder.mCompanyImage);
        holder.mName.setText(item.getName());

        holder.mDiscountValue.setText(item.getDiscountValue());
        holder.mCompanyName.setText(item.getCompanyName());
        holder.mCompanyStreetHouse.setText(item.getCompanyStreet() + ", " + item.getCompanyHouse());
        holder.mViews.setText(item.getView());
        holder.mUsed.setText(item.getUsedCount());
        holder.mTimeStop.setText(getFormattedDate(item.getTimeStop()));


    }

    private String getFormattedDate(String rawDate) {
        SimpleDateFormat utcFormat = new SimpleDateFormat(REST_RESPONSE_FORMAT, Locale.ROOT);
        SimpleDateFormat displayedFormat = new SimpleDateFormat(MONTH_DAY_FORMAT, Locale.getDefault());
        try {
            Date date = utcFormat.parse(rawDate);
            return displayedFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SharesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.shares_icon) AspectRatioImageView mIcon;
        @BindView(R.id.shares_company_image) CircleImageView mCompanyImage;
        @BindView(R.id.shares_name) TextView mName;
        @BindView(R.id.shares_discount_value) TextView mDiscountValue;
        @BindView(R.id.shares_company_name) TextView mCompanyName;
        @BindView(R.id.shares_company_street_house) TextView mCompanyStreetHouse;
        @BindView(R.id.shares_views) TextView mViews;
        @BindView(R.id.shares_used) TextView mUsed;
        @BindView(R.id.shares_time_stop) TextView mTimeStop;
        private SharesItemClickListener mSharesItemClickListener;


        private SharesAdapterViewHolder(View itemView, SharesItemClickListener sharesItemClickListener){
            super(itemView);
            mSharesItemClickListener = sharesItemClickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mSharesItemClickListener!= null){
                mSharesItemClickListener.onSharesItemClickListener(getAdapterPosition());
            }
        }

        public interface SharesItemClickListener {
            void onSharesItemClickListener (int position);
        }
    }
}
