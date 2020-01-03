package com.bcc.cyost;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class DCharacter {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public   Long characterid;
    public  String charactername;
    public  String description;
    public  Long level;
    public  String background;  //user defined? select by dropbox/button
    public  Long charactertype;  //user defined? select by dropbox/button
   // List<DTokens> collection;
   // List<DTokens> achievement;
   // List<DTokens> inventory;
   // List<DTokens> stats;
   @ForeignKey(entity = DStory.class, parentColumns = "storyid", childColumns = "currentstory")
   public Long currentstory;
    @ForeignKey(entity = DChapter.class, parentColumns = "chapterid", childColumns = "currentchapter")
    public  Long currentchapter;
    public  String currentlocation;
    public  String time;


}
