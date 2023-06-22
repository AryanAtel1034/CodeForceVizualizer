package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageview.R;

import java.util.List;

import models.Example;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.myViewholder> {

    List<Example> recentList;
    Context context;

    public RecentAdapter(List<Example> contestList, Context context) {
        this.recentList = contestList;
        this.context = context;
    }

    public RecentAdapter(List<Example> contestList) {
        this.recentList = contestList;
    }

    public void updateimagelist3(List<Example> list) {

        this.recentList = list;
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recentdesign, parent, false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {

        Log.d("original", ":::::::::::" + recentList.get(0).getResult().size());


        holder.problemName.setText(recentList.get(position).getResult().get(position).getProblem().getName());
        holder.problemLanguageUsed.setText(recentList.get(position).getResult().get(position).getProgrammingLanguage());
        holder.problemVerdict.setText(recentList.get(position).getResult().get(position).getVerdict());

    }

    @Override
    public int getItemCount() {
        if (this.recentList != null) {
            return this.recentList.size();

        } else {
            return 0;
        }
    }

    public class myViewholder extends RecyclerView.ViewHolder {


        TextView problemName, problemVerdict, problemLanguageUsed;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            problemLanguageUsed = itemView.findViewById(R.id.problemLanguageUsed);
            problemName = itemView.findViewById(R.id.problemName);
            problemVerdict = itemView.findViewById(R.id.problemVerdict);
        }
    }
}
