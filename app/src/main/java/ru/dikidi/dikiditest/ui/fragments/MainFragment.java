package ru.dikidi.dikiditest.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
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
import ru.dikidi.dikiditest.utilits.ConstantManager;


public class MainFragment extends Fragment {

    private static final String TAG = ConstantManager.TAG_PREFIX + " MainFragment";
    private RecyclerView mRecyclerView;
    private CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener mCatalogItemClickListener;
    private MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener mCatalogButtonMoreClickListener;
    private CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;
    private SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener mSharesItemClickListener;
    private MainAdapter.SharesViewHolder.SharesButtonMoreClickListener mSharesButtonMoreClickListener;
    private String mTitleApp, mTitleImageURL;

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
                .getMainJson(ConstantManager.CITY_ID)
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
                            Log.e(TAG, e.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<MainListRes> call, Throwable t) {
                        Log.e(TAG, t.toString());
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
