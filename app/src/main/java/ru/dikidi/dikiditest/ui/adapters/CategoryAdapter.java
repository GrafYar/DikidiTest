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
import ru.dikidi.dikiditest.data.network.resources.CategoryListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>{
    Context mContext;
    ArrayList<ItemList> mList = new ArrayList();
    CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;

    public CategoryAdapter(ArrayList<ItemList> list, CategoryAdapterViewHolder.CategoryItemClickListener categoryItemClickListener){
        mList = list;
        mCategoryItemClickListener = categoryItemClickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false);
        return new CategoryAdapter.CategoryAdapterViewHolder(view, mCategoryItemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryAdapterViewHolder holder, int i) {

        CategoryListRes item = (CategoryListRes) mList.get(i);
        holder.mCategoryName.setText(item.getName());

        Picasso.with(mContext)
                .load(item.getImage())
                .error(mContext.getResources().getDrawable(R.drawable.placeholder))
                .into(holder.mImage);

    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class CategoryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mImage;
        TextView mCategoryName;
        CategoryItemClickListener mCategoryItemClickListener;

        private CategoryAdapterViewHolder(View itemView, CategoryItemClickListener categoryItemClickListener){
            super(itemView);
            mCategoryItemClickListener = categoryItemClickListener;

            mCategoryName = itemView.findViewById(R.id.category_name);
            mImage = itemView.findViewById(R.id.category_image);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mCategoryItemClickListener!= null){
                mCategoryItemClickListener.onCategoryItemClickListener(getAdapterPosition());
            }
        }

        public interface CategoryItemClickListener {
            void onCategoryItemClickListener (int position);
        }
    }
}
