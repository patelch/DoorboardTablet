package com.doorboard3.doorboardtablet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class InfoFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private static final String TAG = "Poop";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private DoorboardDbHelper dbHelper;
    private ImageView profile1;
    private ImageView profile2;
    private String room;
    private Bundle bundle;

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbHelper = new DoorboardDbHelper(getContext());

        // Populate db with test data
        Populator populator = new Populator(dbHelper, getActivity());
        populator.populate();
        Log.i(TAG, "Populate db with test data");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        Log.i(TAG, "Inflate the layout");

        //profile1 = (ImageView) v.findViewById(R.id.profile_pic_1);
        //Bitmap b1 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_profile_pic_test);
        //profile1.setImageBitmap(ThumbnailUtils.extractThumbnail(b1, 300, 300));

        // Set the room
        bundle = this.getActivity().getIntent().getExtras();
        if (bundle != null && bundle.getBoolean("UPDATE")) {
            Log.i(TAG, "UPDATE is true");
            room = bundle.getString("ROOM");
        } else {
            room = "Iribe 003";
        }
        Log.i(TAG, "Room: " + room);

        // Get messages
        mRecyclerView = (RecyclerView) v.findViewById(R.id.message_list);
        mAdapter = new MessageAdapter(dbHelper.getMessagesForRoom("Iribe 003"));

        ArrayList<Message> data = dbHelper.getMessagesForRoom("Iribe 003");
        for(Message d:data){
            Log.i(TAG, "Message: "+ d.getStatus());
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        Log.i(TAG, "Get Messages");


        return v;
    }
}