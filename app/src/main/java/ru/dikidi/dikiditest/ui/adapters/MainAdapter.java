package ru.dikidi.dikiditest.ui.adapters;

import android.content.Context;
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
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.resources.SharesListRes;
import ru.dikidi.dikiditest.ui.viewholders.ViewHolder1;
import ru.dikidi.dikiditest.ui.viewholders.ViewHolder2;

import static ru.dikidi.dikiditest.data.network.resources.ItemList.CATALOG_TYPE;
import static ru.dikidi.dikiditest.data.network.resources.ItemList.CATEGORY_TYPE;
import static ru.dikidi.dikiditest.data.network.resources.ItemList.SHARES_TYPE;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder1>{

    Context mContext;
    ArrayList<ItemList> mList = new ArrayList();

    public MainAdapter (ArrayList<ItemList> news) {
        mList = news;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        mContext = viewGroup.getContext();
//        View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list2, viewGroup, false);
//        mi = 1;

//        ViewHolder1 viewHolder;
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//
//        View v1 = inflater.inflate(R.layout.item_user_list2, viewGroup, false);
//        viewHolder = new ViewHolder1(v1);

        ViewHolder1 viewHolder;
        View v;
        //LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        LayoutInflater mLayoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ViewHolder2 viewHolder2;
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

//        switch (getItemViewType(i)) {
//            case SHARES_TYPE:
////                ViewHolder1 viewHolder1;
////                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//
//                v = inflater.inflate(R.layout.item_user_list2, viewGroup, false);
//                viewHolder = new ViewHolder1(v);
//                break;
//            case CATALOG_TYPE:
////                ViewHolder2 viewHolder2;
////                LayoutInflater inflater2 = LayoutInflater.from(viewGroup.getContext());
//
//                v = inflater.inflate(R.layout.item_user_list2, viewGroup, false);
//                viewHolder = new ViewHolder2(v);
//                break;
//            default:
//                v = inflater.inflate(R.layout.item_user_list2, viewGroup, false);
//                viewHolder = new ViewHolder2(v);
//                break;
//        }

        if(i == ItemList.SHARES_TYPE) {
            v = mLayoutInflater.inflate(R.layout.item_user_list2, viewGroup, false);
            return new ViewHolder1(v);
        }
        else if (i == ItemList.CATALOG_TYPE) {
            v = mLayoutInflater.inflate(R.layout.item_user_list2, viewGroup, false);
            return new ViewHolder1(v);
        }
        else if (i == ItemList.CATEGORY_TYPE) {
            v = mLayoutInflater.inflate(R.layout.item_user_list, viewGroup, false);
            return new ViewHolder1(v);
        }



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 userViewHolder, int i) {

//        int type = getItemViewType(i);
//
//        MainListRes.Category mNew = mNews.get(i);
//        userViewHolder.mFullName.setText(mNew.getName());

      //  ViewHolder1 vh1 = (ViewHolder1) userViewHolder;

        if(getItemViewType(i) == ItemList.SHARES_TYPE) {
            SharesListRes.ListShares item = (SharesListRes.ListShares) mList.get(i);
            ViewHolder1 holder = (ViewHolder1) userViewHolder;
            holder.mFullName.setText(item.getName());
        }
        else if (getItemViewType(i) == ItemList.CATALOG_TYPE) {
            CatalogListRes item = (CatalogListRes) mList.get(i);
            ViewHolder1 holder = (ViewHolder1) userViewHolder;
            holder.mFullName.setText(item.getName());
        }
        else if(getItemViewType(i) == ItemList.CATEGORY_TYPE) {
            MainListRes.Category item = (MainListRes.Category) mList.get(i);
            ViewHolder1 holder = (ViewHolder1) userViewHolder;
            holder.mFullName.setText(item.getName());

        }
//        configureViewHolder1(vh1, i);
    }

//    private void configureViewHolder1(ViewHolder1 userViewHolder, int i) {
//        ItemList mNew = mList.get(i);
//        userViewHolder.getText().setText(mList.get);
//    }

    @Override
    public int getItemViewType(int position) {
        // определяем какой тип в текущей позиции
        //int type = mList.get(position).getItemType();
//        if (type == 0) return HEADER;
//        else if (type == 1) return TYPE_ITEM1;
        int mi = mList.get(position).getItemType();
        return mList.get(position).getItemType();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    public static class MainViewHolder extends RecyclerView.ViewHolder {
//
//        protected TextView mFullName;
//
//        public MainViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mFullName = (TextView) itemView.findViewById(R.id.user_full_name_txt);
//        }
//    }
}
