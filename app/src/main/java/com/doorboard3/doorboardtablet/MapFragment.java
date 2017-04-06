package com.doorboard3.doorboardtablet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.github.chrisbanes.photoview.PhotoView;

public class MapFragment extends Fragment {

    private RadioGroup floorToggle;
    private ToggleButton floorG;
    private ToggleButton floor1;
    private ToggleButton floor2;
    private ToggleButton floor3;
    private ToggleButton floor4;
    private ToggleButton floor5;
    //private ImageView floorMap;
    private PhotoView floorMap;

    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        floorG = (ToggleButton) view.findViewById(R.id.floor_g);
        floor1 = (ToggleButton) view.findViewById(R.id.floor_1);
        floor2 = (ToggleButton) view.findViewById(R.id.floor_2);
        floor3 = (ToggleButton) view.findViewById(R.id.floor_3);
        floor4 = (ToggleButton) view.findViewById(R.id.floor_4);
        floor5 = (ToggleButton) view.findViewById(R.id.floor_5);

        floorToggle = (RadioGroup) view.findViewById(R.id.floor_toggle);
        floorToggle.setOnCheckedChangeListener(ToggleListener);

        floorMap = (PhotoView) view.findViewById(R.id.floor_map);

        // door board is for room 003 -- first floor
        floorG.setChecked(true);
        floorMap.setImageResource(R.drawable.ground_floor);

        return view;
    }



//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
