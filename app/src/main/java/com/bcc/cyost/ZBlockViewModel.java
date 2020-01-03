package com.bcc.cyost;

import android.app.Application;

import java.util.List;
import androidx.lifecycle.LiveData;


    public class ZBlockViewModel extends androidx.lifecycle.AndroidViewModel {


        private LiveData<List<DBlock>> mAllBlocks;
       CDatabase db;
        public ZBlockViewModel (Application application) {
            super(application);
            // mRepository = new WordRepository(application);
            //mAllWords = mRepository.getAllWords();

            db = CDatabase.getDatabase(application);
            //Membership(new Date(System.currentTimeMillis()));

        }

        public  LiveData<List<DBlock>> getBlocks( int chapterid) {
            if(db.counter().countBlock(chapterid)>0)
                mAllBlocks=db.getter().getBlocks(chapterid);
            return mAllBlocks; }

        //public void insert(MemWithUserAndMemtype word) { db.membershipModel().insertMembership(word); }
    }

