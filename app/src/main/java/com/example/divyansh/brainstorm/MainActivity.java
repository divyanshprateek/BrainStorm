package com.example.divyansh.brainstorm;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> arrayList;
    GridLayout gl;
    TextView points;
    TextView tv;
    TextView timer;
    Button btn1;
    Button btn;
    Button play;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView text;
    ConstraintLayout cl;
    int location;
    int incorrect;
    int score = 0;
    int numberofquestions = 0;

    public void generateQuestion()
    {
        arrayList.clear();
        Random rand = new Random();
        int a = rand.nextInt(100);
        int b = rand.nextInt(100);
        tv.setText(Integer.toString(a)+ "+" + Integer.toString(b));
        location = rand.nextInt(4);
        Log.i("loc",Integer.toString(location));
        for (int i=0;i < 4;i++)
        {
            if(i == location)
            {
                arrayList.add(a + b);
            }
            else
            {
                incorrect = rand.nextInt(200);
                while (incorrect == (a+b))
                {
                    incorrect = rand.nextInt(200);
                }
                arrayList.add(incorrect);
            }

        }
        btn1.setText(Integer.toString(arrayList.get(0)));
        btn2.setText(Integer.toString(arrayList.get(1)));
        btn3.setText(Integer.toString(arrayList.get(2)));
        btn4.setText(Integer.toString(arrayList.get(3)));

    }

    public void playAgain(View view)
    {
        score = 0;
        numberofquestions = 0;
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        final View v = view;
        points.setText("0/0");
        timer.setText("30s");
        text.setText("");
        play.setVisibility(view.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                play.setVisibility(v.VISIBLE);
                timer.setText("0s");
                text.setText("Score: "+Integer.toString(score)+"/"+Integer.toString(numberofquestions ));
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
            }
        }.start();
    }
    public void check(View view)
    {
        Log.i("location",Integer.toString(location));
        if((view.getTag()).toString().equals(Integer.toString(location)))
        {
            score++;
            text.setText("CORRECT");
        }
        else
        {
            text.setText("INCORRECT");
        }
        numberofquestions++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestions));
        generateQuestion();
    }
    public void start(View view)
    {
        btn.setVisibility(view.INVISIBLE);
        cl.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.play));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<Integer>();
        points = (TextView) findViewById(R.id.textView3);
        play = (Button)findViewById(R.id.play);
        cl = (ConstraintLayout) findViewById(R.id.cons);
        btn = (Button) findViewById(R.id.btn);
        timer = (TextView)findViewById(R.id.textView);
        tv = (TextView) findViewById(R.id.textView4);
        text = (TextView)findViewById(R.id.text);
        btn1 =(Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }
}
