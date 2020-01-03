package com.bcc.cyost;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"storyid", "chapterid"})
public class DChapter {

@NonNull
    public Long chapterid;
    @ForeignKey(entity = DStory.class, parentColumns = "storyid", childColumns = "storyid")
    @NonNull
    public Long storyid;
    public  String time; //get input
    public  String place; //get input
    public String chaptername; //get input
    public String scenario; //get input
    //public  ArrayList<String> text;
   // public  String piclink;
   // public ArrayList<String> textlogics;
   // public  ArrayList<String> logics;
   // public  ArrayList<Integer> destinationchapterid;
    public  Long windowtype; //0=charactercreation/upgrade, 1= story, 2 = combat, 3= market etc, 4=puzzle, 5=death, 6= ending...
    @ForeignKey(entity = DBlock.class, parentColumns = "blockid", childColumns = "block1id")
    public  Long block1id;
}
