package com.doorboard3.doorboardtablet;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    private AutoCompleteTextView search;
    private ListView searchResults;

    public SearchFragment() {
        // Required empty public constructor
    }

//    public static SearchFragment newInstance(String param1, String param2) {
//        SearchFragment fragment = new SearchFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search = (AutoCompleteTextView) view.findViewById(R.id.search_rooms);


        ArrayList<RoomOwner> data = new ArrayList<RoomOwner>();
        data.add(new RoomOwner("Professor A", "some stuff", R.drawable.user, "001", "profA@cs.umd.edu", "(123) 456 - 7890"));
        data.add(new RoomOwner("Professor B", "some stuff", R.drawable.user, "002", "profB@cs.umd.edu", "(123) 456 - 7890"));
        data.add(new RoomOwner("Teacher A", "some stuff", R.drawable.user, "101", "teachA@cs.umd.edu", "(123) 456 - 7890"));
        data.add(new RoomOwner("Teacher B", "some stuff", R.drawable.user, "201", "teachB@cs.umd.edu", "(123) 456 - 7890"));
        data.add(new RoomOwner("TA A", "some stuff", R.drawable.user, "301", "taA@cs.umd.edu", "(123) 456 - 7890"));
        data.add(new RoomOwner("TA B", "some stuff", R.drawable.user, "402", "taB@cs.umd.edu", "(123) 456 - 7890"));
        final SearchAdapter adapter = new SearchAdapter(view.getContext(), R.layout.fragment_search, data);
        search.setAdapter(adapter);


        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.info_dialog);

                RoomOwner person = adapter.getItem(position);

                search.setText(person.getName());

                TextView name = (TextView) dialog.findViewById(R.id.dialog_name);
                name.setText(person.getName());

                TextView roomNum = (TextView) dialog.findViewById(R.id.dialog_room_num);
                roomNum.setText("Room " + person.getRoomNum());

                TextView description = (TextView) dialog.findViewById(R.id.dialog_description);
                description.setText(person.getDescription());

                TextView phoneNum = (TextView) dialog.findViewById(R.id.dialog_phone_num);
                phoneNum.setText(person.getPhoneNumber());

                TextView email = (TextView) dialog.findViewById(R.id.dialog_email);
                email.setText(person.getEmail());

                TextView endText = (TextView) dialog.findViewById(R.id.end_text);
                endText.setText("Room " + person.getRoomNum());

                ImageView image = (ImageView) dialog.findViewById(R.id.dialog_image);
                image.setImageResource(person.getImage());

                PhotoView start = (PhotoView) dialog.findViewById(R.id.start_map);
                start.setImageResource(R.drawable.floor_start);

                PhotoView end = (PhotoView) dialog.findViewById(R.id.end_map);
                end.setImageResource(R.drawable.floor_end);

                ImageButton closeDialog = (ImageButton) dialog.findViewById(R.id.dialog_close);
                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
        });




        return view;
    }

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
