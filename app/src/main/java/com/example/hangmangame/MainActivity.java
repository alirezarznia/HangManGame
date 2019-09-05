package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public TextView text1;
    public TextView text2;
    public String current;
    public int numWrong=0 ;
    public int numCorrect=0 ;
    public int legnthCurrent =0 ;
    public ArrayList<Button> invButtons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        setBegin();
        Button btnReset = findViewById(R.id.btnreset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBegin();
            }
        });
    }



    public String[] getWords(){
        String[] strings = new String[]{"Game" , "Party" , "Function" , "girl" ,
        "car wash" , "Sponge bob" , "laptop" , "lamp" , "television" , "chair" ,"gravIty"
        , "Store" , "carpet" , "dinner"};
        return strings;
    }




    public void setBegin(){
        ArrayList<String> strings =new ArrayList<>();
        Collections.addAll(strings ,getWords());
        int rand = (int)(Math.random()*strings.size());
        current = strings.get(rand);
        current = current.toLowerCase();
        legnthCurrent = 0;
        for (int i = 0; i < current.length(); i++) {
            if(current.charAt(i)!=' ') legnthCurrent++;
        }
        String setText1 ="";
        String setText2 ="";
        for (int i = 0; i < current.length(); i++) {
            if(current.charAt(i)!=' ')
                    setText1+="-";
            else
                setText1+=" ";
            setText2+=" ";
        }
        text1.setText(setText1);
        text2.setText(setText2);
        numCorrect=0;
        numWrong=0;
        if(invButtons.size()>0) {
            for (Button i : invButtons) i.setVisibility(View.VISIBLE);
        }
        invButtons.clear();
        return;
    }






    public void letterOnClick(View view){
        Button cur = (Button) view;
        cur.setVisibility(View.INVISIBLE);
        String curText = cur.getText().toString();
        String strText1 = text1.getText().toString();
        String strText2 = text2.getText().toString();
        String str =null;
        invButtons.add((Button) view);
        if(current.contains(curText)){
            for (int i = 0; i < current.length() ;i++) {
                if(current.charAt(i)==curText.charAt(0)){
                    numCorrect++;
                    str=  strText1.substring(0, i) + curText.charAt(0) + strText1.substring(i+1);
                    strText1=str;
                }
            }
            text1.setText(str);
            if(numCorrect== legnthCurrent) {
                Toast.makeText(MainActivity.this , "YOU WON" ,Toast.LENGTH_SHORT ).show();
                setBegin();
            }
        }
        else {


            char[] chrText2 = text2.getText().toString().toCharArray();
            char[] chrText1 = text1.getText().toString().toCharArray();
            while(chrText1[numWrong]==' '){
                numWrong++;

            }
            chrText2[numWrong] = curText.charAt(0);
            text2.setText(String.valueOf(chrText2));
            numWrong++;
            if(numWrong== current.length()) {
                Toast.makeText(MainActivity.this , "YOU LOST" ,Toast.LENGTH_SHORT ).show();
                setBegin();
            }

        }
        return;

    }


}
