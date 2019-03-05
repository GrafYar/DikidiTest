package ru.dikidi.dikiditest.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.dikidi.dikiditest.R;

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
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
        int mi = 1;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        RetrofitService.getInstance()
//                .getJSONApi()
//                .getJson(468902)
//                .enqueue(new Callback<UserListRes>() {
//                    @Override
//                    public void onResponse(Call<UserListRes> call, Response<UserListRes> response) {
//
//                        try {
//                            // post = (List<UserListRes.New>) response.body().getData().getBlocks().getNew();
//                            post = response.body().getData().getBlocks().getNew();
//                            String mText = response.body().getData().getBlocks().getNew().get(0).getId();
//
//                            UsersAdapter mUserAdapter = new UsersAdapter(post);
//                            mRecyclerView.setAdapter(mUserAdapter);
//                            //String mTest = response.body().getData().getBlocks().getNew();
////                           UsersAdapter mUserAdapter = new UsersAdapter(post);
////                            mRecyclerView.setAdapter(mUserAdapter);
//
//
////                            TextView mText = findViewById(R.id.hello_world);
////                            mText.setText(post);
////                            Toast toast = Toast.makeText(getApplicationContext(),
////                                    "Тест " + post.getData().getTitle(),
////                                    Toast.LENGTH_LONG);
////                            toast.setGravity(Gravity.CENTER, 0, 0);
////                            toast.show();
//                        } catch (NullPointerException e) {
////                            Toast toast = Toast.makeText(getApplicationContext(),
////                                    "Хрен там",
////                                    Toast.LENGTH_LONG);
////                            toast.setGravity(Gravity.CENTER, 0, 0);
////                            toast.show();
//                        }
//
//
//                        //textView.append(post.getId() + "\n");
//                        //textView.append(post.getUserId() + "\n");
//                        //textView.append(post.getTitle() + "\n");
//                        //textView.append(post.getBody() + "\n");
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserListRes> call, Throwable t) {
////                        Toast toast = Toast.makeText(getApplicationContext(),
////                                "Провал ",
////                                Toast.LENGTH_LONG);
////                        toast.setGravity(Gravity.CENTER, 0, 0);
////                        toast.show();
//                    }
//                });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
