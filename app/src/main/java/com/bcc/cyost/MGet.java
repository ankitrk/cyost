package com.bcc.cyost;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
//@TypeConverters(TConverter.class)
 public interface MGet
{
    @Query("SELECT * From DCharacter ")
    List<DCharacter> getCharacter();

    @Query("SELECT * From DStory")
    List<DStory> getStory();

    @Query("SELECT * From DChapter")
    List<DChapter> getChapters();

    @Query("SELECT * From DChapter where chapterid=:chapterid ")
    DChapter getChapter( Long chapterid);

    @Query("SELECT * From DBlock where chapterid= :chapterid")
    LiveData<List<DBlock>> getBlocks(Long chapterid);
    @Query("SELECT * From DBlock where blockid= :blockid")
    DBlock getBlock(Long blockid);
  //  @Query("SELECT * From DLogic where chapterid= :chapterid AND logictype=:type ")
 //   List<DLogic> getLogics(int chapterid, int type);

    @Query("SELECT conslogicids From DBlock where blockid= :blockid ")
    List<String> getConseqLogicsfromBlock(Long blockid);

    @Query("SELECT triglogicids From DBlock where blockid= :blockid ")
    List<String> getTriggerLogicsfromBlock(Long blockid);

    @Query("SELECT * From DLogic where logicid= :logicid")
    DLogic getLogics(Long logicid);

    @Query("SELECT * From DTokens where tokenid= :tokenid ")
    DTokens getToken( Long tokenid);

    @Query("SELECT * From DTokens")
    List<DTokens> getTokens();

}
