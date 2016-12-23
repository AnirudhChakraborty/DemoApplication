package com.anirudh.mydermacydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anirudh.mydermacydemo.R;

/**
 * Created by Anirudh Chakraborty on 23/12/16.
 * Recycler adapter
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private Context mContext;
    private String[] locations;

    public RecyclerAdapter(Context context, String[] list) {
        mContext = context;
        locations = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.layout_list_row, parent, false);

        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvLocation.setText(locations[position]);
    }

    @Override
    public int getItemCount() {
        return locations.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLocation;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvLocation  = (TextView) itemView.findViewById(R.id.tv_location);
        }
    }
}
