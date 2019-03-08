package ru.dikidi.dikiditest.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.activities.MainActivity;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter;



public class MainFragment extends Fragment {

    Integer mCityId = 468902;
    RecyclerView mRecyclerView;
    ImageView mTitleImage;
    private CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener mCatalogItemClickListener;
    private MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener mCatalogButtonMoreClickListener;
    private CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;
    private SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener mSharesItemClickListener;
    private MainAdapter.SharesViewHolder.SharesButtonMoreClickListener mSharesButtonMoreClickListener;
    String mTitleApp, mTitleImageURL;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
    super.onAttach(context);
        if (context instanceof CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener){
            mCatalogItemClickListener = (CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener) context;
        }
        if (context instanceof MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener){
            mCatalogButtonMoreClickListener = (MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener) context;
        }
        if (context instanceof CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener){
            mCategoryItemClickListener = (CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener) context;
        }
        if (context instanceof SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener){
            mSharesItemClickListener = (SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener) context;
        }
        if (context instanceof MainAdapter.SharesViewHolder.SharesButtonMoreClickListener){
            mSharesButtonMoreClickListener = (MainAdapter.SharesViewHolder.SharesButtonMoreClickListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loadData();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = getView().findViewById(R.id.data_list);
    }

    public void loadData() {
        RetrofitService.getInstance()
                .getJSONApi()
                .getMainJson(mCityId)
                .enqueue(new Callback<MainListRes>() {
                    @Override
                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {

                        try {

                            ArrayList<ArrayList<ItemList>> listAll = new ArrayList<>();

                            ArrayList<ItemList> shares = new ArrayList<ItemList>(response.body().getData().getBlocks().getShares().getList());
                            ArrayList<ItemList> category = new ArrayList<ItemList>(response.body().getData().getBlocks().getCategories());
                            ArrayList<ItemList> catalog = new ArrayList<ItemList>(response.body().getData().getBlocks().getCatalog());
                            String catalogCount = response.body().getData().getCatalogCount();

                            mTitleApp = response.body().getData().getTitle();
                            mTitleImageURL = response.body().getData().getImage();

                            ((MainActivity) getActivity())
                                .setActionBarTitle(mTitleApp);
                            ((MainActivity) getActivity())
                                    .setActionBarImage(mTitleImageURL);

                            listAll.add(shares);
                            listAll.add(category);
                            listAll.add(catalog);

                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(layoutManager);

                            MainAdapter mainAdapter = new MainAdapter(getContext(), listAll, shares, category, catalog, catalogCount,
                                    mCatalogItemClickListener,mCatalogButtonMoreClickListener, mCategoryItemClickListener, mSharesItemClickListener, mSharesButtonMoreClickListener);

                            mRecyclerView.setAdapter(mainAdapter);



                        } catch (NullPointerException e) {
                            Toast toast = Toast.makeText(getContext(),
                                    "Ошибка",
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MainListRes> call, Throwable t) {
                        Toast toast = Toast.makeText(getContext(),
                                "Ошибка запроса",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCatalogItemClickListener = null;
        mCatalogButtonMoreClickListener = null;
        mCategoryItemClickListener = null;
        mSharesItemClickListener = null;
        mSharesButtonMoreClickListener = null;
    }
}
