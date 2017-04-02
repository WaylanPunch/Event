package com.waylanpunch.event.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.waylanpunch.event.R;
import com.waylanpunch.event.widget.CycleViewPagerFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFragment extends Fragment {
    private static final String TAG = FindFragment.class.getName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private FrameLayout fl_content;

    public FindFragment() {
        Log.i(TAG, "FindFragment,Required empty public constructor");

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        initView(view);
    }

    private void initView(View view) {
        ArrayList<Integer> imageIds = new ArrayList<Integer>();
        imageIds.add(R.drawable.a);
        imageIds.add(R.drawable.b);
        imageIds.add(R.drawable.c);
        imageIds.add(R.drawable.d);
        imageIds.add(R.drawable.e);
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("巩俐不低俗，我就不能低俗");
        titles.add("扑树又回来啦！再唱经典老歌引万人大合唱");
        titles.add("揭秘北京电影如");
        titles.add("乐视网TV版大派送");
        titles.add("热血屌丝的反杀");

        CycleViewPagerFragment cycleViewPagerFragment = CycleViewPagerFragment.newInstance(imageIds, titles);
        getChildFragmentManager().beginTransaction().add(R.id.fl_content, cycleViewPagerFragment).commit();
    }


    public void onButtonPressed(Bundle bundle) {
        Log.i(TAG, "onButtonPressed,update argument and hook method into UI event");
        if (mListener != null) {
            mListener.onFragmentInteraction(R.id.navigation_find, bundle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach");
        super.onDetach();
        mListener = null;
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

}
