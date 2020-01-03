package com.bcc.cyost;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
//@TypeConverters(TConverter.class)
 public interface MDelete
{
  /*  @Insert()
    int addCharacter(DCharacter character);

    @Insert()
    int addStory(DStory story);

    @Insert()
    int addChapter(DChapter chapter);

    @Insert()
    int addToken(DTokens tokens);

    @Insert()
    int addBlock(DBlock block);*/

    @Delete()
    int deleteLogic(DLogic logic);

}
