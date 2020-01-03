package com.bcc.cyost;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ACharacterList extends Activity {
    private CDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDb= CDatabase.getDatabase(this);
        setContentView(R.layout.activity_acharacter_list);
/*
        TextView toptext=findViewById(R.id.diatext);
        toptext.setText(currentmem.name +"\nMobile: "+ currentmem.phonenum+"\nAddress: "+currentmem.address);
        final LinearLayout lv= findViewById(R.id.llvertical);
        final List<Membership> current=mDb.userModel().findUserAllCurrentMembership(id);
        final Button meminf, add,delete;
        meminf=findViewById(R.id.meminf);//new activity
        add=findViewById(R.id.addmem);//new activity if not all
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                   // Intent i= new Intent(Memberview.this, Addmem.class);
                   // i.putExtra("userid",(long)id);
                   // startActivity(i); finish();

            }
        });
        for(int i=0;i<current.size() ;i++)
        // mDb.CountModel().countusermembership(0)
        { LinearLayout lh= new LinearLayout(this);
            lh.setOrientation(LinearLayout.HORIZONTAL);
            Membership m= current.get(i);
            String date= new SimpleDateFormat("dd/MM/yyyy").format(m.endTime);
            lv.addView(textBox(m.memtypeId,date));
            lh.addView(button(id,m.id,0));
            lh.addView(button(id,m.id,1)); lh.addView(button(id,m.id,2));

            lh.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            lv.addView(lh);}

    }
    private TextView textBox(Long s, String date)
    {
        TextView t;
        t=new TextView(this);
        Cursor str;
        str=mDb.query("Select * FROM DCharacter");
        t.setText(str+"\nExpiring on: "+date);
        t.setGravity(Gravity.CENTER);
        return t;
    }
    private Button button(final Long u,final Long m, final int i)
    {Button b;
        b=new Button(this);
        if (i==0){b.setText("Edit\nMembership");}
        if (i==1){b.setText("Renew\nMembership");}
        if (i==2) b.setText(
                "Delete\nMembership"
        );
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==0){
                    Intent i= new Intent(Memberview.this, Editmem.class);
                    i.putExtra("userid",(long)u);
                    i.putExtra("membershipid",(long)m);
                    startActivity(i);
                    finish();
                }else if(i==1){
                    //TODO renew
                    Membership old=mDb.membershipModel().getmembershipfromid(m);
                    old.status=1;
                    mDb.membershipModel().editmembershiptrans(old, "expired");
                    Membership m2= new Membership();
                    m2.status=0;
                    m2.userId=old.userId;
                    m2.startTime=old.endTime;
                    m2.memtypeId=old.memtypeId;
                    long subtime= (long)(mDb.MemTypeModel().findMemTypeById(m2.memtypeId).durationday)*3600000*24;
                    m2.endTime =new Date(old.endTime.getTime()+subtime);
                    mDb.membershipModel().insertmembershiptrans(m2,"Renew membership");
                    Toast.makeText(Memberview.this,"Renewal successful",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(Memberview.this, Memberview.class);
                    i.putExtra("userid",(long)u);
                    startActivity(i);
                    finish();
                }else if(i==2){
                    //TODO
                    mDb.membershipModel().deletemembershiptrans(m,"Delete Membership");
                    Intent i= new Intent(Memberview.this, Memberview.class);
                    i.putExtra("userid",(long)u);
                    startActivity(i);
                    finish();
                }
            }
        });
        return b;*/
    }
}
