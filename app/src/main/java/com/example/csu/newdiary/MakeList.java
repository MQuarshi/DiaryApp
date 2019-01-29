package com.example.csu.newdiary;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

//This will show all the entries in list view using the recyclerview
import static com.example.csu.newdiary.ShowEntries.dl;


public class MakeList extends RecyclerView.Adapter<MakeList.VHolder> {
    private List<Recyclerview > mRec;
    private Context mContext;
    private Recyclerview  mRecV;
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  MakeList(List<Recyclerview > mRec) {
        this.mRec = mRec;
    }

    public class VHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleV;
        public TextView dateV;
        public TextView monthV;
        public TextView conV;
        public String dt;

        public VHolder(View v) {
            super(v);
            titleV = (TextView) v.findViewById(R.id.ts);
            dateV = (TextView) v.findViewById(R.id.date);
            monthV = (TextView) v.findViewById(R.id.month);
            conV = (TextView) v.findViewById(R.id.con);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in =new Intent(dl, ShowEntryA.class);
                    in.putExtra("diary", titleV.getText().toString());
                    in.putExtra("con", conV.getText().toString());
                    in.putExtra("date", dt.toString());
                    dl.startActivity(in);
                }
            });
        }
    }

    public MakeList(List<Recyclerview > myDataset, Context context, Recyclerview  recyclerView) {
        mRec = myDataset;
        mContext = context;
        mRecV = recyclerView;
    }
    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler,null);

        // set the view's size, margins, paddings and layout parameters
        return new VHolder(v);
    }

    int month;
    String[] strMonths;
    String monthName,dom,tit,con;
    @Override
    public void onBindViewHolder(VHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Calendar GCalendar = (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getDefault());;
        Recyclerview  mrec = mRec.get(position);

        try {
            GCalendar.setTime(dateFormatter.parse(mrec.getDate().toString()));
            month = GCalendar.get(Calendar.MONTH)+1;
            strMonths = new String[] { "DEC", "JAN", "FEB", "MAR", "APR", "MEI", "JUN",
                    "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
            monthName = strMonths[month];
            dom = String.valueOf(GCalendar.get(Calendar.DAY_OF_MONTH));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.titleV.setText(mrec.getTitle().toString());
        holder.dateV.setText(dom.toString());
        holder.monthV.setText(monthName.toString());
        holder.conV.setText(mrec.getContent().toString());
        holder.dt = mrec.getDate().toString();
    }

    @Override
    public int getItemCount() {
        return mRec.size();
    }
}
