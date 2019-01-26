package com.example.a2019soshay.alarmgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MathQuiz extends AppCompatActivity {

    int spree = 3;
    TextView math_q;
    EditText math_a;
    int correct_answer;
    String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] operations = {"+", "*", "-", "/"};
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);

        math_q  = (TextView)findViewById(R.id.textView3);
        math_a  = (EditText)findViewById(R.id.editText);

        math_q.setText(generate_expression());

        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
    }

    String generate_expression(){
        String ra = digits[r.nextInt(9)] + operations[r.nextInt(4)] + digits[r.nextInt(9)] + operations[r.nextInt(4)] + digits[r.nextInt(9)];
        double ira = getCorrect_answer(ra);
        while(ira != (int)ira){
            ra = digits[r.nextInt(9)] + operations[r.nextInt(4)] + digits[r.nextInt(9)] + operations[r.nextInt(4)] + digits[r.nextInt(9)];
            ira = getCorrect_answer(ra);
        }
        return ra;
    }

    public void generate_q(){
        math_q.setText(generate_expression());
    }

    double getCorrect_answer(String expression){
        double i1 = (double)Character.getNumericValue(expression.charAt(0));
        double i2 = (double)Character.getNumericValue(expression.charAt(2));
        double i3 = (double)Character.getNumericValue(expression.charAt(4));

        char o1 = expression.charAt(1);
        char o2 = expression.charAt(3);

        if(o1=='+'){
            if(o2=='*'){
                return i1+i2*i3;
            }
            if(o2=='/'){
                return i1+i2/i3;
            }
            if(o2=='+'){
                return i1+i2+i3;
            }
            if(o2=='-'){
                return i1+i2-i3;
            }
        }else if(o1=='-'){
            if(o2=='*'){
                return i1-i2*i3;
            }
            if(o2=='/'){
                return i1-i2/i3;
            }
            if(o2=='+'){
                return i1-i2+i3;
            }
            if(o2=='-'){
                return i1-i2-i3;
            }
        }else if(o1=='*'){
            if(o2=='*'){
                return i1*i2*i3;
            }
            if(o2=='/'){
                return i1*i2/i3;
            }
            if(o2=='+'){
                return i1*i2+i3;
            }
            if(o2=='-'){
                return i1*i2-i3;
            }
        }else{
            if(o2=='*'){
                return i1/i2*i3;
            }
            if(o2=='/'){
                return i1/i2/i3;
            }
            if(o2=='+'){
                return i1/i2+i3;
            }
            if(o2=='-'){
                return i1/i2-i3;
            }
        }
        return 0.0;
    }

    public void check_answer(View view) {
        if(Integer.parseInt(math_a.getText().toString()) == getCorrect_answer(math_q.getText().toString())){
            Toast.makeText(this, "GJ", Toast.LENGTH_SHORT).show();
            spree -= 1;
            if(spree == 0){
                Toast.makeText(this, "CONGRATS, ALARM HAS STOPPED", Toast.LENGTH_SHORT).show();
                math_q.setText("Completed Task");
            }else {
                generate_q();
            }
        }else {
            Toast.makeText(this, "RIP", Toast.LENGTH_SHORT).show();
            spree = 3;
            generate_q();
        }
    }
}
