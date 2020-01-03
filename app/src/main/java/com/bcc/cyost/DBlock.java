package com.bcc.cyost;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.TypeConverters;

@TypeConverters(TConverter.class)
@Entity(primaryKeys = {"blockid", "storyid", "chapterid"})
public class DBlock {
    @NonNull
    public Long blockid;
    @ForeignKey(entity = DStory.class, parentColumns = "storyid", childColumns = "storyid")
    @NonNull
    public Long storyid;
    @ForeignKey(entity = DChapter.class, parentColumns = "chapterid", childColumns = "chapterid")
    @NonNull
    public Long chapterid;
    public Long iflogictype;        //and=0,or=1
    public Long nextblockid;
    public Long blocktype;                   //text=0, button=1, coolstuff
static    public int blocktext=0;static public int blockbutton=1 ; static public int blockstuff=2;
    public List<Long> triglogicids;
    public List<Long> conslogicids;
    public String text;
public Long movechapter;
public boolean move;
//optional when bloctype =button
    public String triggertoString(Context c) {
        CDatabase db = CDatabase.getDatabase(c);
        String st;
        int triglogiccount = triglogicids.size();
        st = "| ";
        if (blocktype == 0 || blocktype == 1) {
            if (triglogiccount > 0) {
                st = "Triggering Conditions: ";
                for (int k = 0; k < triglogiccount; k++) {
                    DLogic dl=  db.getter().getLogics(triglogicids.get(k));
                    //if (dl.tokenid ==null){}else
                        st = st +  db.getter().getToken(dl.tokenid).tokenname;
                    String dropdowntext=dl.operationtostring();
                    st = st + dropdowntext;// find out operation to string, find out value to string\
                    st= st+ dl.value+ " | "; //check last condition alive??
                }

            }

        }
        return st;
    }
    public String conseqtoString(Context c) {
        CDatabase db = CDatabase.getDatabase(c);
        String st;
        int conslogiccount = conslogicids.size();
        st = "| ";
        if (blocktype == 0 || blocktype == 1) {
            if (conslogiccount > 0) {
                st = "Consequences: ";
                for (int k = 0; k < conslogiccount; k++) {
                    DLogic dl=  db.getter().getLogics(conslogicids.get(k));
                    st = st +  db.getter().getToken(dl.tokenid).tokenname;
                    String dropdowntext=dl.operationtostring();
                    st = st + dropdowntext;// find out operation to string, find out value to string\
                    st= st+ dl.value+ " | ";
                }
                if(move){st=st+" move to chapter "+ db.getter().getChapter( movechapter) ;}
            }

        }
        return st;}
}
