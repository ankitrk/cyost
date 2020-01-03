package com.bcc.cyost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class SChapterAdapter extends BaseAdapter{
    private List<DChapter> listchapter;
    private Context context;
    public SChapterAdapter(Context context, List<DChapter> listchapter) {
        this.context = context;
        this.listchapter = listchapter;}
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listchapter.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listchapter.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        DChapter entry = listchapter.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.simpledropdown, null);
            TextView t= arg1.findViewById(R.id.dropdowntext);
           /* int time=entry.durationday;
            String type="";
            switch (entry.seldur){ case 1: type= "days"; break;
                case 2:time=time/7;type= "weeks"; break;
                case 3:time=time/30;type= "months"; break;
                case 4:time=time/365;type= "years"; break;
            }*/
            t.setText(entry.chaptername );
        }
        //TextView tvtax = (TextView) arg1.findViewById(R.id.textView1);
        //tvtax.setText(entry.getName());
        //TextView tvdd = (TextView) arg1.findViewById(R.id.textView2);
        //tvdd.setText(java.lang.Float.toString(java.lang.Float.parseFloat(entry.gethot())/2));
        // TextView tvcomm = (TextView) arg1.findViewById(R.id.textView4);
        // tvcomm.setText(entry.getphno());
        // TextView tv=(TextView) arg1.findViewById(R.id.textView7);
        // tv.setText(entry.getmail());
        // TextView tvdud = (TextView) arg1.findViewById(R.id.textView2);
        // tvdud.setText(java.lang.Integer.toString(entry.getid()));
        // Typeface t=Typeface.createFromAsset(null, "Bahaus-.TTF");
        //tvtax.setTypeface(t);tvdd.setTypeface(t);tvcomm.setTypeface(t);

        return arg1;
    }

}
