package com.doorboard3.doorboardtablet;

import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<Message> messages;

    public MessageAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_entry, parent, false);
        MessageViewHolder holder = new MessageViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.profilePic.setImageBitmap(ThumbnailUtils.extractThumbnail(message.getProfilePic(), 300, 300));
        holder.name.setText(message.getName());
        holder.dateTime.setText(message.getDateTime());
        holder.status.setText(message.getStatus());
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return messages.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}



