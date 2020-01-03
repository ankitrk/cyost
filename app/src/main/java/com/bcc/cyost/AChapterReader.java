package com.bcc.cyost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AChapterReader extends Activity {
    CDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achapter_reader);
        LinearLayout ll =findViewById(R.id.llreader) ;
        int chapterid= getIntent().getIntExtra("chapterif",0);
       db= CDatabase.getDatabase(this);

       List<DBlock> blocks= db.getter().getBlocks(chapterid).getValue();
        for ( int i = 0; i < blocks.size(); i++) {
            final ArrayList<Integer> triglogics,conslogics ;

            triglogics= db.getter().getTriggerLogicsfromBlock(i);
            conslogics=db.getter().getConseqLogicsfromBlock(i);
            final DBlock current= blocks.get(i);
            Boolean show,verdict;
                if (current.iflogictype==DLogic.iflogicand)  verdict=show=true;//and: show starts as true
                                        else verdict=show=false;                        //or: show starts as false
                for (int j=0;j<triglogics.size();j++)
                       {if(evaluatelogics(triglogics.get(j))!=show){verdict= !show;} }
                if  (verdict && current.blocktype==DBlock.blocktext)
                        {TextView tv= new TextView(AChapterReader.this);
                        String s=current.text;
                         for(int j=0;j<conslogics.size();j++)
                            s= executeconsequences(conslogics.get(j),s);
                         tv.setText(s);
                         ll.addView(tv);}
                if(verdict && current.blocktype==DBlock.blockbutton)
                    {Button b=new Button(AChapterReader.this);
                        b.setText(current.text);
                    b.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int j=0;j<conslogics.size();j++)
                                executeconsequences(conslogics.get(j),"");
                            if (current.move)moveactivity(current.movechapter);
                        }
                                                         });
                    ll.addView(b);
                    }

                    ;
                }
            }
        private Boolean evaluatelogics(int i){
DLogic l= db.getter().getLogics(i);
DTokens t= db.getter().getToken(l.tokenid);
String value= l.value;
if(l.operationtype==DLogic.triggerequal)if( t.count==Integer.parseInt(value)) return true;else return false;
            if(l.operationtype==DLogic.triggermore)if( t.count>Integer.parseInt(value)) return true;else return false;
            if(l.operationtype==DLogic.triggerless)if( t.count<Integer.parseInt(value)) return true;else return false;
            return true;
       }
       private String executeconsequences(int logic, String s) {
           DLogic l = db.getter().getLogics(logic);
           DTokens t = db.getter().getToken(l.tokenid);
           String value = l.value;
           if (l.operationtype == DLogic.conseqset) t.count = Integer.parseInt(value);
           if (l.operationtype == DLogic.conseqmodify) t.count = t.count + Integer.parseInt(value);
           if (l.operationtype == DLogic.conseqshow) {
               s.replaceAll("<" + t.tokenname + ">", value);
           }
          return s ;
       }
       private void moveactivity(int i){
        Intent g = new Intent(AChapterReader.this, AChapterReader.class);
        g.putExtra("chapterid",i);
        startActivity(g);
       }

  /*  private static void test(boolean in1, boolean in2) throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
        Bindings vars = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        vars.put("in1", in1);
        vars.put("in2", in2);
        boolean result = (boolean) engine.eval("in1 && in2");
        System.out.println(result);
    }*/
}