package com.bcc.cyost;

//import androidx.room.Dao;
;
import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;


@Dao
//@TypeConverters(TConverter.class)
 public interface MCount
{
    @Query("SELECT COUNT() From DCharacter ")
    int countCharacter();

    @Query("SELECT COUNT() From DStory")
    int countStory();

    @Query("SELECT COUNT() From DChapter")
    int countChapter();

    @Query("SELECT COUNT() From DBlock where chapterid=:chapterid")
    int countBlock(int chapterid);


    @Query("SELECT COUNT (DISTINCT tokenid) From DTokens")
    int countallToken();
    @Query("SELECT count From DTokens" )
    int countToken();

 //   @Query("SELECT collection From DCHARACTER where characterid=:charid ")
 //   ArrayList getCharToken(int charid);

}
