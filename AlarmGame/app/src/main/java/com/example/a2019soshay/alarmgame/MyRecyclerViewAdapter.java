package com.example.a2019soshay.alarmgame;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>  {

    private List<ArrayList<String>> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<String> participant;
    private boolean flag;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<ArrayList<String>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.alarm_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        participant = mData.get(position);
        holder.myTextView.setText(participant.get(0)+", "+participant.get(1));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        protected Button on_off;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.alarm_name);
            itemView.setOnClickListener(this);
            on_off = (Button) itemView.findViewById(R.id.on_off);
            for(int x=0; x<ListAlarmActivity.mButtons.size(); x++){
                if (ListAlarmActivity.mButtons.get(x)==true){
                    on_off.setText("ON");
                    on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.green));
                }
                else{
                    on_off.setText("OFF");
                    on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.red));
                }
            }

            on_off.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)


            if (view.getId() == on_off.getId()){

                //View tempview = (View) on_off.getTag(R.integer.btn_plus_view);
                //TextView tv = (TextView) tempview.findViewById(R.id.number);
                //int number = Integer.parseInt(tv.getText().toString()) + 1;
                //tv.setText(String.valueOf(number));
                if (on_off.getText().equals("OFF")){
                    on_off.setText("ON");
                    on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.green));
                    String time = ListAlarmActivity.mNames.get(getAdapterPosition());
                    Integer hour = Integer.parseInt(time.substring(0,2));
                    Integer minute = Integer.parseInt(time.substring(3,5));
                    Calendar c1 = Calendar.getInstance();
                    c1.set(Calendar.HOUR_OF_DAY,hour);
                    c1.set(Calendar.MINUTE,minute);
                    c1.set(Calendar.SECOND,0);
                    ListAlarmActivity.mAlarms.get(getAdapterPosition()).setAlarm(on_off.getContext(),c1);
                }
                else{
                    on_off.setText("OFF");
                    on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.red));
                    ListAlarmActivity.mAlarms.get(getAdapterPosition()).cancelAlarm(on_off.getContext());
                }
        }
        else{
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

    }

    // convenience method for getting data at click position
    ArrayList<String> getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected Button on_off;
    //private TextView tvFruit, tvnumber;

    public MyViewHolder(View itemView) {
        super(itemView);

      //  tvFruit = (TextView) itemView.findViewById(R.id.animal);
      //  tvnumber = (TextView) itemView.findViewById(R.id.number);
        on_off = (Button) itemView.findViewById(R.id.on_off);
      //  btn_minus = (Button) itemView.findViewById(R.id.minus);

        ///on_off.setTag(R.integer.btn_plus_view, itemView); ?????
        //btn_minus.setTag(R.integer.btn_minus_view, itemView);
        on_off.setOnClickListener(this);
        //btn_minus.setOnClickListener(this);

    }

    // onClick Listener for view
    @Override
    public void onClick(View v) {

        if (v.getId() == on_off.getId()){

            //View tempview = (View) on_off.getTag(R.integer.btn_plus_view);
            //TextView tv = (TextView) tempview.findViewById(R.id.number);
            //int number = Integer.parseInt(tv.getText().toString()) + 1;
            //tv.setText(String.valueOf(number));
            if (on_off.getText()=="OFF"){
                on_off.setText("ON");
                on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.green));
                String time = ListAlarmActivity.mNames.get(getAdapterPosition());
                Integer hour = Integer.parseInt(time.substring(0,2));
                Integer minute = Integer.parseInt(time.substring(3,5));
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.HOUR_OF_DAY,hour);
                c1.set(Calendar.MINUTE,minute);
                ListAlarmActivity.mAlarms.get(getAdapterPosition()).setAlarm(on_off.getContext(),c1);
            }
            else{
                on_off.setText("OFF");
                on_off.setBackgroundColor(on_off.getContext().getResources().getColor(R.color.red));
                ListAlarmActivity.mAlarms.get(getAdapterPosition()).cancelAlarm(on_off.getContext());
            }
            //MainActivity.modelArrayList.get(getAdapterPosition()).setNumber(number);

        }
    }
}