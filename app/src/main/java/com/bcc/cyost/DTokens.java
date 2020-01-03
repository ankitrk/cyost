package com.bcc.cyost;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class DTokens {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Long tokenid;
    @ForeignKey(entity = DStory.class, parentColumns = "storyid", childColumns = "storyid")
    public Long storyid;
    public  String tokenname;
    public  String Description;
    public  Long tokentype;  //0=inventory, 1=status,2= choices, 3=achievement
    public  Long tokenstatus; //0= unallocated, 1=stored, 2=used  //runtime allocated?
    public  Long count; //number //runtime allocated?
    @ForeignKey(entity = DCharacter.class, parentColumns = "characterid", childColumns = "tokenownerid")
  //  public  Long tokenownerid; //runtime allocated?
    public  Long tokenstartvalue;//whenever initialises in story(or auto refreshed each chapter??)
}
