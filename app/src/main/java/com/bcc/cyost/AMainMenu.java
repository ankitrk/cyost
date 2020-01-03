package com.bcc.cyost;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AMainMenu extends Activity {
Button Bchar, Bread, Btell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Bchar=findViewById(R.id.characterbutton);
        Bread=findViewById(R.id.playbutton);
        Btell=findViewById(R.id.tellbutton);
Bchar.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
Bread.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
Btell.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    }
}
