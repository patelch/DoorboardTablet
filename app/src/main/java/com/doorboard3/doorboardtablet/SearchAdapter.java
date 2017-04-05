package com.doorboard3.doorboardtablet;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charmipatel on 4/5/17.
 */

public class SearchAdapter extends ArrayAdapter<RoomOwner> {

    private Context context;
    private ArrayList<RoomOwner> data;
    private ArrayList<RoomOwner> suggestions;
    private ArrayList<RoomOwner> tempItems;

    public SearchAdapter(Context context, int resource, ArrayList<RoomOwner> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        tempItems = new ArrayList<RoomOwner>(data);
        suggestions = new ArrayList<RoomOwner>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_item, parent, false);
        }
        final RoomOwner person = getItem(position);
        if (person != null) {
            TextView name = (TextView) view.findViewById(R.id.search_name);
            name.setText(person.getName());

            TextView roomNum = (TextView) view.findViewById(R.id.search_room_number);
            roomNum.setText(person.getRoomNum());
        }


        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
//        @Override
//        public CharSequence convertResultToString(Object resultValue) {
//            String str = ((People) resultValue).getName();
//            return str;
//        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (RoomOwner person : tempItems) {
                    String name = person.getName();
                    String roomNum = person.getRoomNum();
                    if (name.toLowerCase().contains(constraint.toString().toLowerCase())
                            || roomNum.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(person);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<RoomOwner> filterList = (ArrayList<RoomOwner>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (RoomOwner person : filterList) {
                    add(person);
                    notifyDataSetChanged();
                }
            }
        }
    };

}
