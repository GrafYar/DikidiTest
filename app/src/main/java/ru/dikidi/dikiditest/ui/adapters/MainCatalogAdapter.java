package ru.dikidi.dikiditest.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;


public class MainCatalogAdapter extends RecyclerView.Adapter<MainCatalogAdapter.MyViewHolder>{

    ArrayList<ItemList> mList = new ArrayList();

    public MainCatalogAdapter(ArrayList<ItemList> list){
        mList = list;

    }

    @NonNull
    @Override
    public MainCatalogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //       if(i == ItemList.CATEGORY_TYPE) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new MainCatalogAdapter.MyViewHolder(view);
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainCatalogAdapter.MyViewHolder holder, int i) {
//        if(getItemViewType(i) == ItemList.SHARES_TYPE) {
        CatalogListRes item = (CatalogListRes) mList.get(i);
        MainCatalogAdapter.MyViewHolder mHolder = (MainCatalogAdapter.MyViewHolder) holder;
        mHolder.mFullName.setText(item.getName());
        //       }
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
