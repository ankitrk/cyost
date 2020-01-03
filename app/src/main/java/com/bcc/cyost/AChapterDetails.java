package com.bcc.cyost;

//import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AChapterDetails extends FragmentActivity {
    public static List<DBlock> Blocks = new ArrayList<DBlock>();
    private CDatabase db;
    RecyclerView rv;
    ZBlockListAdapter adapter;
    private ZBlockViewModel mBlockViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      CDatabase  db= CDatabase.getDatabase(this);
        setContentView(R.layout.activity_achapter_details);
       final LinearLayout ll;
        Button addchapter,addtoken, addlogic,  addblock,addbutton, addcool, save;
        db = CDatabase.getDatabase(getApplicationContext());
        Blocks = db.getter().getBlocks(0/**/).getValue();
        adapter=new  ZBlockListAdapter(AChapterDetails.this);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mBlockViewModel = ViewModelProviders.of(AChapterDetails.this).get(ZBlockViewModel.class);
        mBlockViewModel.getBlocks(0/**/).observeForever( new Observer<List<DBlock>>() {
            @Override
            public void onChanged(@Nullable final List<DBlock> blocks) {
                // Update the cached copy of the words in the adapter.
                adapter.setBlocks(blocks);
            }
        });

       /* List<DBlock> blocks= db.getter().getBlocks(0);
        for (int i=0;i<blocks.size();i++){
            LinearLayout inner= new LinearLayout(this);
            int j=blocks.get(i).blocktype;
            inner.setOrientation(LinearLayout.VERTICAL);
            inner.addView(addtrigger());
            inner.addView(text());
            inner.addView(addconsequence())


addblock.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        LinearLayout in=new LinearLayout(AChapterDetails.this);
        ll.add(in);
        in.setOrientation(LinearLayout.VERTICAL);
        Button add
        ll
    }
});;*/
      /*  addele.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.addView(typebox);
                ll.addView(iflogic);
                ll.addView (textbox);
                ll.addView (ifresult);
                ll.addView (result);
            }
        });
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

}
