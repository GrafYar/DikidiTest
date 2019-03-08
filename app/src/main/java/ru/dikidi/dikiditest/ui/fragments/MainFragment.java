package ru.dikidi.dikiditest.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    Integer mCityId = 468902;
    RecyclerView mRecyclerView;
    ImageView mTitleImage;
    private CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener mCatalogItemClickListener;
    private MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener mCatalogButtonMoreClickListener;
    private CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener mCategoryItemClickListener;
    private SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener mSharesItemClickListener;
    private MainAdapter.SharesViewHolder.SharesButtonMoreClickListener mSharesButtonMoreClickListener;

 //   private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        mTitleImage = getView().findViewById(R.id.title_image);

//        getActivity().setTitle(mTitleApp);
//
//        Picasso.with(getActivity())
//                .load(mTitleImageURL)
//                .into(mTitleImage);

    }

    public void loadData() {
//        RetrofitService.getInstance()
//                .getJSONApi()
//                .getMainJson(mCityId)
//                .enqueue(new Callback<MainListRes>() {
//                    @Override
//                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {
//
//                        try {
//                            mShares.addAll(response.body().getData().getBlocks().getShares().getList());
//                            mCategory.addAll(response.body().getData().getBlocks().getCategories());
//                            mCatalog.addAll(response.body().getData().getBlocks().getCatalog());
//
//                            mTitleApp = response.body().getData().getTitle();
//                            mTitleImageURL = response.body().getData().getImage();
//
//                       //     loadData2();
//
////                            Picasso.with(getActivity())
////                                    .load(response.body().getData().getImage())
////                                    .into(mTitleImage);
//
//                            mList.add(mShares);
//                            mList.add(mCategory);
//                            mList.add(mCatalog);
//
//                            LinearLayoutManager layoutManager
//                                    = new LinearLayoutManager(getContext());
//                            mRecyclerView.setLayoutManager(layoutManager);
//
//                            MainAdapter mainAdapter = new MainAdapter(getContext(), mList, mShares, mCategory, mCatalog);
//                            mRecyclerView.setAdapter(mainAdapter);
//
//
//                        } catch (NullPointerException e) {
//                            Toast toast = Toast.makeText(getContext(),
//                                    "Ошибка",
//                                    Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MainListRes> call, Throwable t) {
//                        Toast toast = Toast.makeText(getContext(),
//                                "Ошибка запроса",
//                                Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
//                });

        RetrofitService.getInstance()
                .getJSONApi()
                .getMainJson(mCityId)
                .enqueue(new Callback<MainListRes>() {
                    @Override
                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {

                        try {

                            ArrayList<ArrayList<ItemList>> listAll = new ArrayList<>();
                            ArrayList<ItemList> shares = new ArrayList<>();
                            ArrayList<ItemList> category = new ArrayList<>();
                            ArrayList<ItemList> catalog = new ArrayList<>();

                            shares.addAll(response.body().getData().getBlocks().getShares().getList());
                            category.addAll(response.body().getData().getBlocks().getCategories());
                            catalog.addAll(response.body().getData().getBlocks().getCatalog());
                            String catalogCount = response.body().getData().getCatalogCount();

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
