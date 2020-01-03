package com.bcc.cyost;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


    public class ZBlockListAdapter extends RecyclerView.Adapter<ZBlockListAdapter.BlockViewHolder> {
        //private List<User> mWords;
        class BlockViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
        {
            private final EditText addText; private final Button trigger,conseq,bsave;

            private BlockViewHolder(View itemView) {
                super(itemView);
                addText = itemView.findViewById( R.id.EText);
                trigger = itemView.findViewById( R.id.BTrigger);
                conseq = itemView.findViewById( R.id.BConseq);
                bsave=itemView.findViewById(R.id.BSave);
                //  wordItemView .setOnClickListener(this);

            }
        }
        // private final OnClickListener mOnClickListener;//= new OnClickListener();
        private final LayoutInflater mInflater;
        private List<DBlock> mBlocks= Collections.emptyList();; // Cached copy of words
        String s;
        ZBlockListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public BlockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.blocks, parent, false);
            //onBindViewHolder();
    /*    itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
            //OnItemClickListener(getAdapterPosition(),itemView);
            return new BlockViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final BlockViewHolder holder, final int position) {
            s="";
            Context c= mInflater.getContext();
          final CDatabase db= CDatabase.getDatabase(c);

          /*  if (mBlocks != null&& mBlocks.size()!=0) {
                DBlock current = mBlocks.get(position);
                String mem="No current memberships";
                if (db.counter().countBlock(/*current.id)>0)
                {       mem=" : ";
                    List<DBlock> list= db.userModel().findUserAllCurrentMembership(current.id);
                    // List<MemType>memTypes=db.MemTypeModel().findAllMemType();
                    for(int i=0; i<list.size();i++){
                        mem= mem+ db.MemTypeModel().loadMemTypeById(list.get(i).memtypeId)+" : ";
                    }
                } */
           final DBlock current = mBlocks.get(position);
                //berships.toArray().toString()+" : ";
                holder.addText.setText(current.text);
                holder.trigger.setText(current.triggertoString(c));
                holder.conseq.setText((current.conseqtoString(c)));
           /* } else {
                // Covers the case of data not being ready yet.
                holder.addText.setText("No Word");
            }*/
            holder.trigger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mInflater.getContext(),  mBlocks.get(position).text, Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(mInflater.getContext(),ATriggerDetail.class);
                    i.putExtra("blockid",current.blockid);
                    mInflater.getContext().startActivity(i);
                }
            });
            holder.conseq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mInflater.getContext(),  mBlocks.get(position).text, Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(mInflater.getContext(),AConseqDetail.class);
                    i.putExtra("blockid",current.blockid);
                    mInflater.getContext().startActivity(i);
                }
            });
            holder.bsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mInflater.getContext(),  mBlocks.get(position).text, Toast.LENGTH_SHORT).show();
                   String s= holder.addText.getText().toString();
                   current.text=s;
                   db.inserter().addBlock(current);
                }
            });
        }

        void setBlocks(List<DBlock> words){
            mBlocks = words;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mWords has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (mBlocks != null)
                return mBlocks.size();
            else return 0;
        }
    }





