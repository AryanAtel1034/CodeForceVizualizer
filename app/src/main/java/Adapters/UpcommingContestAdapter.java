package Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageview.R;

import java.util.ArrayList;

import models.Example;

public class UpcommingContestAdapter extends RecyclerView.Adapter<UpcommingContestAdapter.myViewholder> {

    ArrayList<Example> contestList = new ArrayList<>();
    Context context;
    public UpcommingContestAdapter(ArrayList<Example> imagelist) {
        this.contestList = imagelist;

    }

    public UpcommingContestAdapter(ArrayList<Example> contestList, Context context) {
        this.contestList = contestList;
        this.context = context;
    }

    public void updateimagelist(ArrayList<Example> list) {

        this.contestList = list;
        notifyDataSetChanged();


    }


    @NonNull
    @Override
    public UpcommingContestAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contestdesignupcomming, parent, false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcommingContestAdapter.myViewholder holder, int position) {


        String phase = contestList.get(0).getResult().get(position).getPhase();

        if (phase.equals("BEFORE")) {
            String name = contestList.get(position).getResult().get(position).getName().toString();
            holder.txtContestName.setText(name);

        } else {
            return;
        }


        holder.btncalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Code 2");
                intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, "8:05");
                intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context.getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.contestList != null) {
            return this.contestList.size();

        } else {
            return 0;
        }

    }

    public class myViewholder extends RecyclerView.ViewHolder {

        TextView txtContestName, txtContestDate, txtContestTime;
        ImageView btncalender;


        public myViewholder(@NonNull View itemView) {
            super(itemView);
            txtContestName = itemView.findViewById(R.id.txtContestName);
            txtContestDate = itemView.findViewById(R.id.txtContestDate);
            txtContestTime = itemView.findViewById(R.id.txtContestTime);
            btncalender = itemView.findViewById(R.id.btncalender);


        }
    }

}
