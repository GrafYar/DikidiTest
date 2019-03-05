package ru.dikidi.dikiditest.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.SharesListRes;

public class MainSharesAdapter extends RecyclerView.Adapter<MainSharesAdapter.MyViewHolder>{

    ArrayList<ItemList> mList = new ArrayList();

    public MainSharesAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

   //     if(i == ItemList.SHARES_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shares_item,viewGroup,false);
            return new MyViewHolder(view);
     //   }
  //      return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
//        if(getItemViewType(i) == ItemList.SHARES_TYPE) {
            SharesListRes.ListShares item = (SharesListRes.ListShares) mList.get(i);
            MyViewHolder mHolder = (MyViewHolder) holder;
            mHolder.mFullName.setText(item.getName());
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
        TextView mFullName;
        public MyViewHolder(View itemView){
            super(itemView);
            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);

        }
    }

}
