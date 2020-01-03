package com.bcc.cyost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ATriggerDetail extends Activity {
    CDatabase db= CDatabase.getDatabase(this);
    List<DChapter> chapterlist; List<DTokens> tokenlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrigger_details);
        final LinearLayout ll= findViewById(R.id.lladd);
        Intent i= getIntent();
        final int blockid= i.getIntExtra("blockid",0);
        final DBlock block=db.getter().getBlock(blockid);
       final ArrayList<Integer> logicids= db.getter().getTriggerLogicsfromBlock(blockid);
        final RadioButton and= findViewById(R.id.andradio);
        RadioButton or= findViewById(R.id.orradio);
        if(block.iflogictype==0)and.setSelected(true); else or.setSelected(true);
        for(int t=0;t<logicids.size();t++){
       final DLogic l= db.getter().getLogics(t) ;
            LinearLayout llhor=new LinearLayout(this);
            EditText inputvalue= new EditText(this);
            Spinner tokendropdown= new Spinner(this);
            tokenlist=db.getter().getTokens();
            tokendropdown.setAdapter( new STokenAdapter(ATriggerDetail.this,tokenlist));
            tokendropdown.setEnabled(false);
            Spinner operationdropdown= new Spinner(this);
            inputvalue.setText(l.value);
            inputvalue.setEnabled(false);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.triggerdropdown, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            operationdropdown.setAdapter( adapter);
            operationdropdown.setEnabled(false);
            final int j=t;
            Button delete= new Button(this);
            delete.setText("del");
            delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    logicids.remove(j);
                    db.deleter().deleteLogic(l);
                    //TODO restart activity
                }
            });
            llhor.setOrientation(LinearLayout.HORIZONTAL);

            llhor.addView(tokendropdown);
            llhor.addView(operationdropdown);
            llhor.addView(inputvalue);
            llhor.addView(delete);
            ll.addView(llhor);
        }
        LinearLayout llhor=new LinearLayout(this);
        final EditText inputvalue= new EditText(this);
        final Spinner tokendropdown= new Spinner(this);
        tokenlist=db.getter().getTokens();
        tokendropdown.setAdapter( new STokenAdapter(ATriggerDetail.this,tokenlist));
        tokendropdown.setEnabled(false);
        final Spinner operationdropdown= new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.triggerdropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputvalue.setInputType(InputType.TYPE_CLASS_NUMBER);
        llhor.addView(tokendropdown);
        llhor.addView(operationdropdown);
        llhor.addView(inputvalue);
        ll.addView(llhor);
        Button savebutton= new Button(this);
        savebutton.setText("Save Logic");
        savebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DLogic l= new DLogic();
                l.logictype=DLogic.ltypetrigger;
                l.value=Integer.parseInt(inputvalue.getText().toString());
                l.tokenid=((DTokens)tokendropdown.getSelectedItem()).tokenid;
                l.operationtype=operationdropdown.getSelectedItemPosition();
              int newlogic=  db.inserter().addLogic(l);
              //check notnull dropboxs, textbox
                if (and.isChecked())block.iflogictype=0; else block.iflogictype=1;
                block.triglogicids.add(newlogic);

                db.inserter().addBlock(block);

                //TODO restart activity
            }
        });
        ll.addView(savebutton);
    }
}
