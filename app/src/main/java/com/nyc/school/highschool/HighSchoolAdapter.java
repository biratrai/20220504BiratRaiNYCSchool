package com.nyc.school.highschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyc.school.R;
import com.nyc.school.data.HighSchools;

import java.util.List;

public class HighSchoolAdapter extends RecyclerView.Adapter<HighSchoolAdapter.ViewHolder> {
    private List<HighSchools> highSchoolsList;
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param schools List[] of high schools.
     */
    public HighSchoolAdapter(List<HighSchools> schools) {
        highSchoolsList = schools;
    }

    public void setData(List<HighSchools> schools) {
        highSchoolsList = schools;
        notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.high_school_name);
        }

        public TextView getTextView() {
            return textView;
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
        viewHolder.getTextView().setText(highSchoolsList.get(position).getSchoolName());
    }

    // Return the size of your data (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return highSchoolsList.size();
    }
}