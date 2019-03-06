package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.SharesListRes;
import ru.dikidi.dikiditest.ui.views.AspectRatioImageView;

public class MainSharesAdapter extends RecyclerView.Adapter<MainSharesAdapter.MyViewHolder>{

    ArrayList<ItemList> mList = new ArrayList();
    Context mContext;

    public MainSharesAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shares,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

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


//
//        SimpleDateFormat format = new SimpleDateFormat();
//        format.applyPattern("dd.MM.yyyy");
//        Date docDate= format.parse(item.getUsedCount());
       int mi = mList.size();
        holder.mTimeStop.setText("до: " + item.getTimeStop());
       // holder.mCountShares.setText(String.format(Locale.getDefault(), "%d", mi));


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
        AspectRatioImageView mIcon;
        CircleImageView mCompanyImage;
        TextView mName, mDiscountValue, mCompanyName, mCompanyStreetHouse, mViews, mUsed, mTimeStop, mCountShares;


        public MyViewHolder(View itemView){
            super(itemView);
            mIcon = itemView.findViewById(R.id.shares_icon);
            mName = itemView.findViewById(R.id.shares_name);
            mDiscountValue = itemView.findViewById(R.id.shares_discount_value);
            mCompanyImage = itemView.findViewById(R.id.shares_company_image);
            mCompanyName = itemView.findViewById(R.id.shares_company_name);
            mCompanyStreetHouse = itemView.findViewById(R.id.shares_company_street_house);
            mViews = itemView.findViewById(R.id.shares_views);
            mUsed = itemView.findViewById(R.id.shares_used);
            mTimeStop = itemView.findViewById(R.id.shares_time_stop);
            mCountShares = itemView.findViewById(R.id.shares_count_shares);

        }
    }

}
