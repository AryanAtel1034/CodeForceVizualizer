package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageview.R;

import java.util.ArrayList;

import models.Example;

public class PastContestAdapter extends RecyclerView.Adapter<PastContestAdapter.myViewholder> {


    ArrayList<Example> contestList = new ArrayList<>();
    Context context;
    ProgressBar progressBar;

    public PastContestAdapter(ArrayList<Example> contestList, Context context) {
        this.contestList = contestList;
        this.context = context;
    }

    public PastContestAdapter(ArrayList<Example> contestList) {
        this.contestList = contestList;
    }

    public void updateimagelist2(ArrayList<Example> list) {
        this.contestList = list;
        notifyDataSetChanged();


    }

    @NonNull
    @Override


    public PastContestAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contestdesignpast, parent, false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastContestAdapter.myViewholder holder, int position) {


        String phase = contestList.get(position).getResult().get(position).getPhase();
//        Log.d("original", phase);

        if (phase.equals("FINISHED")) {
            String name = contestList.get(position).getResult().get(position).getName().toString();
            holder.txtContestName.setText(name);

        } else if (phase.equals("BEFORE")){
           return;
        }


    }

    @Override
    public int getItemCount() {
        if (this.contestList != null) {
            return this.contestList.size();

        } else {
            return 0;
        }
    }

    public void onDataChange() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public class myViewholder extends RecyclerView.ViewHolder {

        TextView txtContestName, txtContestDate, txtContestTime;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            txtContestName = itemView.findViewById(R.id.txtContestName);
            txtContestDate = itemView.findViewById(R.id.txtContestDate);
            txtContestTime = itemView.findViewById(R.id.txtContestTime);
        }
    }
}
