package com.bcc.cyost;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
@TypeConverters(TConverter.class)
@androidx.room.Database(entities = {DStory.class,DChapter.class,DTokens.class, DLogic.class, DCharacter.class, DBlock.class}
, version = 1, exportSchema = false)
public abstract class CDatabase extends RoomDatabase {

    private static volatile  CDatabase INSTANCE;
  public abstract MGet getter();
    public abstract MInsert inserter();
    public abstract MCount counter() ;
    public abstract MDelete deleter() ;
    public static CDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), CDatabase.class,"MyDb")
                            //(context.getApplicationContext(), Database.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();

            //DatabaseInitializer.populateAsync(Database.INSTANCE);
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {



        @Override

        public void onCreate( SupportSQLiteDatabase mdb) {
            super.onCreate(mdb);
            CDatabase db= INSTANCE;
            DStory sample= new DStory();
            sample.author= "CYOST";
            sample.status=1L;
            sample.storydesc="A little story for you to check out the capability of this app. After you finish reading it," +
                    " you can tweak the code and try out changes you made to the story";
            sample.storyname="CYOStory";
            Long storyid=db.inserter().addStory(sample);
            DChapter chapter1= new DChapter();
            chapter1.storyid= storyid;
            chapter1.chaptername ="Telling your CYOStory";
            chapter1.place= "Your home";
            chapter1.time="This morning";
            chapter1.scenario="The beginning";
            chapter1.windowtype=1L;
            Long chapterid= db.inserter().addChapter(chapter1);
            DTokens horror,adventure, custompoint,hp,atk,def;
            horror=new DTokens();adventure=new DTokens(); custompoint=new DTokens();
            hp=new DTokens();atk=new DTokens();def=new DTokens();
            horror.tokenstartvalue=0L;
            horror.count=0L;
            horror.tokentype=3L;
            db.inserter().addToken(horror);
            /*
            .tokenstartvalue=0;
            .count=0;
            .tokentype=3;
            db.inserter().addToken();
            */
            DBlock  block1= new DBlock();
            block1.blocktype=0L;
            block1.chapterid=chapterid;
            block1.storyid=storyid;
            block1.text="So, you have just downloaded this new CYOST app and are quite excited to write your " +
                    "very first gamebook. SO what kind of story do you wanna tell today?";
            Long block1id = db.inserter().addBlock(block1);
            DBlock block2= new DBlock();
            block2.blocktype=1L;
            block2.chapterid=chapterid;
            block2.storyid=storyid;
            block2.text="\"One where I get to go about exploring and solving puzzles\"";
            block2.triglogicids=new ArrayList<>();block2.conslogicids=new ArrayList<>();
            block2.triglogicids.add(checktoken(horror,0L,0L).logicid);
            block2.triglogicids.add(checktoken(adventure,0L,0L).logicid);
            block2.iflogictype=0L;
            block2.conslogicids.add(addtoken(horror,0L,1L).logicid);
            Long block2id = db.inserter().addBlock(block2);
            DBlock block3= new DBlock();
            block3.blocktype=1L;
            block3.chapterid=chapterid;
            block3.storyid=storyid;
            block3.text="\"One where I go around killing monsters and collecting loot\"";
            block3.triglogicids=new ArrayList<>();
            block3.conslogicids=new ArrayList<>();
            block3.triglogicids.add(checktoken(horror,0L,0L).logicid);
            block3.triglogicids.add(checktoken(adventure,0L,0L).logicid);
            block3.iflogictype=0L;
            block3.conslogicids.add(addtoken(adventure,0L,1L).logicid);
            Long block3id = db.inserter().addBlock(block3);
            DBlock block4= new DBlock();
            block4.triglogicids=new ArrayList<>();
            block4.conslogicids=new ArrayList<>();
            block4.blocktype=2L;
            block4.chapterid=chapterid;
            block4.storyid=storyid;
            block4.text="Ok, you need to assign stats to customize your character";
            block4.triglogicids.add(checktoken(adventure,0L,1L).logicid);
            block4.conslogicids.add(addtoken(custompoint,1L,10L).logicid);
            block4.conslogicids.add(addtoken(hp,1L,10L).logicid);
            block4.conslogicids.add(addtoken(atk,1L,10L).logicid);
            block4.conslogicids.add(addtoken(def,1L,10L).logicid);
            Long block4id = db.inserter().addBlock(block4);
            DBlock block5= new DBlock();
            block5.blocktype=0L;
            block5.chapterid=chapterid;
            block5.storyid=storyid;
            block5.text="Ok, you are playing a typical person using his wits and resources to explore and solve puzzles";
            block5.triglogicids=new ArrayList<>();
            block5.conslogicids=new ArrayList<>();
            block5.triglogicids.add(checktoken(horror,0L,1L).logicid);
            Long block5id = db.inserter().addBlock(block5);
            DBlock block6= new DBlock();
            block6.blocktype=0L;
            block6.chapterid=chapterid;
            block6.storyid=storyid;
            block6.text="You are Ready to Continue";
            block6.triglogicids=new ArrayList<>();
            block6.conslogicids=new ArrayList<>();
            block6.triglogicids.add(checktoken(adventure,0L,1L).logicid);
            block6.triglogicids.add(checktoken(custompoint,0L,0L).logicid);
            block6.conslogicids.add(proceed(2L).logicid);
            Long block6id = db.inserter().addBlock(block6);
            DBlock block7= new DBlock();
            block7.blocktype=0L;
            block7.chapterid=chapterid;
            block7.storyid=storyid;
            block7.text="You are Ready to Continue";
            block7.triglogicids=new ArrayList<>();
            block7.conslogicids=new ArrayList<>();
            block7.triglogicids.add(checktoken(horror,0L,1L).logicid);
            block7.conslogicids.add(proceed(3L).logicid);
            Long block7id = db.inserter().addBlock(block7);
        }
        DLogic checktoken(DTokens t,Long operation,Long i){
            DLogic dl= new DLogic();
            dl.operationtype=operation;
            dl.tokenid=t.tokenid;
            dl.logictype=0L;
            return dl;
        }
        DLogic addtoken(DTokens t,Long operation,Long i){
            DLogic dl= new DLogic();
            dl.operationtype=operation;
            dl.tokenid=t.tokenid;
            dl.logictype=1L;
            return dl;
        }
        DLogic proceed(Long chid){
            DLogic dl= new DLogic();
            dl.chapterid=chid;
            dl.logictype=2L;
            return dl;
        }
    };
    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract Cursor query(String s);
}