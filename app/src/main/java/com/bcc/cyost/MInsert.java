package com.bcc.cyost;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.TypeConverters;

@Dao
//@TypeConverters(TConverter.class)
 public interface MInsert
{
    @Insert()
    Long addCharacter(DCharacter character);

    @Insert()
    Long addStory(DStory story);

    @Insert()
    Long addChapter(DChapter chapter);

    @Insert()
    Long addToken(DTokens tokens);

    @Insert()
    Long addBlock(DBlock block);

    @Insert()
    Long addLogic(DLogic logic);

}
