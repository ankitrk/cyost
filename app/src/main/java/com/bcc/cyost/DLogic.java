package com.bcc.cyost;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@TypeConverters(TConverter.class)
@Entity(
        //      foreignKeys = {@ForeignKey(entity = MemType.class, parentColumns = "id", childColumns = "memtype_id"),
        //       @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")})

)
public class DLogic {
    @PrimaryKey(autoGenerate  =true)
    @NonNull
    public Long logicid;
    public String logicname;
    @ForeignKey(entity = DStory.class, parentColumns = "storyid", childColumns = "storyid")
    public Long storyid;
    public Long logictype;//triggerlogic=0, consequence=1 ,move=2, choose=3, input=4
    public static final int ltypetrigger=0;
    public static final int ltypeconseq=1;
    public static final int ltypemove=2;
    public static final int ltypechoose=3;
    public Long iflogic;                 //for logictype=0; 0=and,1=or  dropdownselect
    public static final int iflogicand=0;
    public static final int iflogicor=1;
    @ForeignKey(entity = DTokens.class, parentColumns = "tokenid", childColumns = "tokenid")
    public Long tokenid;              //for ltype =0,1,3 only
    public Long operationtype;              //for ltype =0  :  equal=0,lessthan=1,notlessthan=2
                                            // for ltype =1 :  show =0, modify=1, set=2
    public String value;                       // for ltype =0,1 only typed at creation?? (or blockwise typed if left blank??), ltype=2 maxchoices
    public static final int triggerequal=0;
    public static final int triggerless=1;
    public static final int triggermore=2;
    public static final int conseqshow=0;
    public static final int conseqmodify=1;
    public static final int conseqset=2;
    @ForeignKey(entity = DChapter.class, parentColumns = "chapterid", childColumns = "chapterid")
    public Long chapterid;           //for ltype =0 only, dropdownselect
    public Long choicecount;           //for logictype=3 int= total no of choices

    public String operationtostring() {
        String s="";
      //  if (logictype == ) {
//if (operationtype==)s="";

        if (logictype == 0)
            { if (operationtype==triggerequal)s=" equals to ";
            if (operationtype==triggerless)s=" less than ";
            if (operationtype==triggermore)s=" greater than "; }
        if (logictype == 1)
            {
                if (operationtype==conseqshow)s=" is displayed with units";
                if(operationtype==conseqmodify)s=" is incremented by ";
               if (operationtype==conseqset)s=" is set to ";
            }
       // if (ltypemove) s= " Proceed to chapter ";
        return s;
    }
}
