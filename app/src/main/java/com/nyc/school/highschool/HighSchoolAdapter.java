package com.nyc.school.highschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyc.school.R;
import com.nyc.school.data.HighSchools;

import java.util.ArrayList;
import java.util.List;

public class HighSchoolAdapter extends RecyclerView.Adapter<HighSchoolAdapter.ViewHolder> implements Filterable {
    private List<HighSchools> highSchoolsList;
    private List<HighSchools> filteredHighSchoolsList;
    private final OnItemClickListener itemClickListener;
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param schools List of high schools.
     * @param itemClickListener interface for click listener
     */
    public HighSchoolAdapter(List<HighSchools> schools, OnItemClickListener itemClickListener) {
        filteredHighSchoolsList = schools;
        highSchoolsList = new ArrayList<>(schools);
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<HighSchools> schools) {
        filteredHighSchoolsList = schools;
        highSchoolsList = new ArrayList<>(schools);
        notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView = itemView.findViewById(R.id.high_school_name);
        }

        private void bind(OnItemClickListener clickListener, HighSchools highSchool) {
            itemView.setOnClickListener(view -> clickListener.onItemClicked(highSchool.getDbn()));
            textView.setText(highSchool.getSchoolName());
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.high_school_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(itemClickListener, filteredHighSchoolsList.get(position));
    }

    // Return the size of your data (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filteredHighSchoolsList.size();
    }

    @Override
    public Filter getFilter() {
        return searchedFilter;
    }

    private final Filter searchedFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<HighSchools> filteredList = new ArrayList<>();
            // If charSequence is null or empty show all highSchools
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(highSchoolsList);
            } else {
                // If charSequence is valid search for related school name
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HighSchools item : highSchoolsList) {
                    if (item.getSchoolName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredHighSchoolsList.clear();
            filteredHighSchoolsList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    // Interface for ItemClick on the adapter
    interface OnItemClickListener{
        void onItemClicked(String highSchoolDbn);
    }

}
