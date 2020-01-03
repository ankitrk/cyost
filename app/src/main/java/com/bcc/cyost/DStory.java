package com.bcc.cyost;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(
      //      foreignKeys = {@ForeignKey(entity = MemType.class, parentColumns = "id", childColumns = "memtype_id"),
     //       @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")})
    //@TypeConverters(DateConverter.class)
    )
    public class DStory {
        // Fields can be public or private with getters and setters.
        @PrimaryKey(autoGenerate = true)
        @NonNull
        public Long storyid;

        public String storyname;
    public String storydesc;
        public String author;
    public Long status;// 0 in progress/1 finished
        //@ColumnInfo(name="memtype_id") public Long memtypeId;
//public ArrayList<DChapter> chapters;
    @ForeignKey(entity = DChapter.class, parentColumns = "chapterid", childColumns = "chapter1id")
public Long chapter1id;

        public String toString(){
            return " Story No."+String.valueOf(storyid)+ " DStory Name: "+storyname+" Author: "+ author+" Description: "+ storydesc+" Status: "+ status;
        }
    }


