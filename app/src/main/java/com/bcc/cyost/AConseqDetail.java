package com.bcc.cyost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class AConseqDetail extends Activity {
    CDatabase db= CDatabase.getDatabase(this);
    List<DChapter> chapterlist; List<DTokens> tokenlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aconseq_details);
        final LinearLayout ll= findViewById(R.id.lladd);
        Intent i= getIntent();
        final int blockid= i.getIntExtra("blockid",0);
        final DBlock block=db.getter().getBlock(blockid);
        final Spinner chapterdropdown= findViewById(R.id.chapterdrop);
        chapterlist=db.getter().getChapters();
        chapterdropdown.setAdapter( new SChapterAdapter(AConseqDetail.this,chapterlist));
        if (!block.move)chapterdropdown.setVisibility(View.GONE);
        if (block.move)chapterdropdown.setSelection(block.movechapter);
        final CheckBox chaptercheck= findViewById(R.id.chaptercheck);
      chaptercheck.setSelected(block.move);
      chaptercheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  chapterdropdown.setVisibility(View.VISIBLE);
                  chapterdropdown.setSelection(block.movechapter);} else{
                  chapterdropdown.setVisibility(View.GONE);
              }
          }
      });

        ArrayList<Integer> logicids= db.getter().getConseqLogicsfromBlock(blockid);
        for(int t=0;t<logicids.size();t++){
       final DLogic l= db.getter().getLogics(t) ;
            LinearLayout llhor=new LinearLayout(this);
            EditText inputvalue= new EditText(this);
            Spinner tokendropdown= new Spinner(this);
            tokenlist=db.getter().getTokens();
            tokendropdown.setAdapter( new STokenAdapter(AConseqDetail.this,tokenlist));
            tokendropdown.setEnabled(false);
            Spinner operationdropdown= new Spinner(this);
            inputvalue.setText(l.value);
            inputvalue.setEnabled(false);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conseqdropdown, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            operationdropdown.setAdapter( adapter);
            operationdropdown.setEnabled(false);
            Button delete= new Button(this);
            delete.setText("del");
            delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
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
        tokendropdown.setAdapter( new STokenAdapter(AConseqDetail.this,tokenlist));
        tokendropdown.setEnabled(false);
        final Spinner operationdropdown= new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conseqdropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //inputvalue.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                l.logictype=DLogic.ltypeconseq;
                l.value=Integer.parseInt(inputvalue.getText().toString());
                l.tokenid=((DTokens)tokendropdown.getSelectedItem()).tokenid;
                l.operationtype=operationdropdown.getSelectedItemPosition();
              int newlogic=  db.inserter().addLogic(l);
              //check notnull dropboxs, textbox
                block.conslogicids.add(newlogic);
                if (chaptercheck.isChecked()){
                    block.move=true;
                block.movechapter=((DBlock)(chapterdropdown.getSelectedItem())).blockid;
               ;} else {
                        block.move=false;
                        block.movechapter=0;
                }
                db.inserter().addBlock(block);

                //TODO restart activity
            }
        });
        ll.addView(savebutton);
    }
}
