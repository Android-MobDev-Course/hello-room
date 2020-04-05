package com.mobdev.helloroom;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Marco Picone (picone.m@gmail.com) 20/03/2020
 * Recycler View Adapter for Log Data Structure
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private ArrayList<LogDescriptor> mDataset;
	private Context mContext = null;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public class ViewHolder extends RecyclerView.ViewHolder {

		private View v = null;

		public ViewHolder(View v) {
			super(v);
			this.v = v;

			v.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					int position = getPosition();
					LogDescriptorManager.getInstance(mContext).removeLog(mDataset.get(position));
					notifyItemRemoved(position);
					return false;
				}
			});
		}

		public void setText(String text){
			TextView tView = (TextView)v.findViewById(R.id.myTextView);
			tView.setText(text);
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public MyAdapter(ArrayList<LogDescriptor> myDataset, Context context) {
		mDataset = myDataset;
		mContext  = context;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

		// create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_element, parent, false);
		// set the view's size, margins, paddings and layout parameters

		ViewHolder vh = new ViewHolder(v);

		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		holder.setText("["+mDataset.get(position).getTimestamp()+"]: "+mDataset.get(position).getData());

	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	public ArrayList<LogDescriptor> getmDataset() {
		return mDataset;
	}

	public void setmDataset(ArrayList<LogDescriptor> mDataset) {
		this.mDataset = mDataset;
	}
}