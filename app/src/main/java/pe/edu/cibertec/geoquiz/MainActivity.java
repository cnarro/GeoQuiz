package pe.edu.cibertec.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnYes, btnNo, btnPrevious, btnNext;
    TextView tvQuestion;
    ArrayList<Question> questions;
    int actualPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        tvQuestion = findViewById(R.id.tvQuestion);

        actualPosition = 0;

        questions = new ArrayList<>();
        loadQuestions();

        showActualQuestion();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResponse(true);

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResponse(false);
                Question actualQuestion = questions.get(actualPosition);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition += 1;
                if(actualPosition == questions.size()){
                    actualPosition = 0;
                }
                showActualQuestion();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualPosition -= 1;
                if(actualPosition == -1){
                    actualPosition = questions.size() - 1;
                }
                showActualQuestion();
            }
        });

    }

    private void verifyResponse(boolean option) {
        Question actualQuestion = questions.get(actualPosition);
        if (option == actualQuestion.isResponse()){
            Toast.makeText(MainActivity.this, getString(R.string.answer_correct), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, getString(R.string.answer_incorrect), Toast.LENGTH_SHORT).show();
        }
    }

    private void showActualQuestion() {
        tvQuestion.setText(questions.get(actualPosition).getName());
    }

    private void loadQuestions() {
        Question questionPeru = new Question(getString(R.string.peru_question),true);
        questions.add(questionPeru);

        Question questionChile = new Question(getString(R.string.chile_question),false);
        questions.add(questionChile);

        Question questionColombia = new Question(getString(R.string.colombia_question),false);
        questions.add(questionColombia);
    }
}
