package com.rfjavacertified.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rfjavacertified.R;
import com.rfjavacertified.beans.Answer;
import com.rfjavacertified.beans.Question;
import com.rfjavacertified.beans.QuestionAnswer;
import com.rfjavacertified.constantes.IConstantesApp;
import com.rfjavacertified.utils.JsonResolver;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.media.rf.com.rfmediacore.utils.RFUtilsFile;

/**
 * Created by diego on 04/09/2017.
 */

public class JavaCertified7Activity extends AppCompatActivity {

    private QuestionAnswer questionAnswers;
    private List<Question> questions;
    private List<Question> responseQuestions;
    private Question actualQuestion;
    private int actualIndexQuestion;
    RadioGroup radioGroup;
    Button buttonNext;
    Button buttonCheck;
    Button buttonReload;
    Button buttonFinish;
    TextView asnwersLabel;
    TextView explanation;
    TextView questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_certified_seven_activity);
        this.init();
    }

    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_answers);
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonCheck = (Button) findViewById(R.id.button_check);
        buttonFinish = (Button) findViewById(R.id.button_finish);
        buttonReload = (Button) findViewById(R.id.button_reload);
        asnwersLabel = (TextView) findViewById(R.id.answers_label) ;
        questionText = (TextView) findViewById(R.id.text_view_question) ;
        explanation = (TextView) findViewById(R.id.explanation) ;
        this.buttonNext.setVisibility(View.GONE);
        this.buttonCheck.setVisibility(View.GONE);
        this.buttonFinish.setVisibility(View.GONE);
        this.radioGroup.setVisibility(View.GONE);
        this.asnwersLabel.setVisibility(View.GONE);
        this.explanation.setVisibility(View.GONE);
        this.buttonReload.setVisibility(View.GONE);
        this.questionText.setVisibility(View.GONE);
        this.initTest();
    }

    private void initTest(){
        File root = RFUtilsFile.getRootFolder(IConstantesApp.APP_NAME);
        File fileJson = null;

        if (root != null && root.isDirectory()) {
            fileJson = new File(root.getAbsoluteFile() + RFUtilsFile.FILE_SEPARATOR + IConstantesApp.FILE_JSON_JAVA_7);
            if (fileJson.isFile() && fileJson.exists()) {
                try {
                    if(questionAnswers == null) {
                        questionAnswers = JsonResolver.resolveQuestionAnswer(fileJson.getAbsolutePath());
                    }
                    questions = questionAnswers.getQuestions();
                    Collections.shuffle(questions);
                    if(questions != null && questions.size() > 0){
                        actualQuestion = questions.get(0);
                        actualIndexQuestion = 0;
                    }
                    responseQuestions = new ArrayList<Question>();
                    this.loadQuestion();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        if(questions == null){
            Toast.makeText(this, R.string.no_questions, Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void loadRadioButtons(){
        List<Answer> answers = actualQuestion.getAnswers();
        Collections.shuffle(answers);
        int index = 0;
        RadioButton rb = null;
        radioGroup.removeAllViews();
        for (Answer answer: answers){
            rb = new RadioButton(this);
            rb.setText(answer.getText());
            radioGroup.addView(rb);
            if(index == 0){
                rb.setSelected(true);
            }
            index++;

        }
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
    }

    private void loadQuestion(){
        this.loadRadioButtons();
        this.buttonNext.setVisibility(View.GONE);
        this.buttonCheck.setVisibility(View.VISIBLE);
        this.buttonFinish.setVisibility(View.VISIBLE);
        this.radioGroup.setVisibility(View.VISIBLE);
        this.asnwersLabel.setVisibility(View.VISIBLE);
        this.explanation.setVisibility(View.GONE);
        this.questionText.setVisibility(View.VISIBLE);
        this.questionText.setText(actualQuestion.getText().trim());
    }

    public void resolveQuestion(){
        String explanationTxt;
        int indexRadioSelect = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
        actualQuestion.setSelectAnswer(indexRadioSelect);
        if(actualQuestion.getAnswers().get(actualQuestion.getSelectAnswer()).getIndex() == actualQuestion.getIndexSuccesQuestion()) {
            explanationTxt = getResources().getString(R.string.certificado_succes_answer)+"\n\t"
                    +getResources().getString(R.string.certificado_explanation)+":\n\t\t"+actualQuestion.getExplanation()+"\n";
        }else {
            Answer realAnswer = null;
            for (Answer answer : actualQuestion.getAnswers()){
                if(answer.getIndex() == actualQuestion.getIndexSuccesQuestion()){
                    realAnswer = answer;
                    break;
                }
            }
            explanationTxt = getResources().getString(R.string.certificado_wrong_answer)+"\n\t"+
                    getResources().getString(R.string.certificado_correct_answer)+":\n\t\t"+realAnswer.getText()+"\n"+
                    getResources().getString(R.string.certificado_explanation)+":\n\t\t"+actualQuestion.getExplanation()+"\n";
        }
        this.explanation.setText(explanationTxt);
        responseQuestions.add(actualQuestion);
    }

    public void next(View view){
        actualIndexQuestion++;
        if(actualIndexQuestion >= questions.size()){
            this.finish(null);
        }else{
            actualQuestion = questions.get(actualIndexQuestion);
            this.loadQuestion();
        }
    }

    public void check(View view){
        resolveQuestion();
        this.buttonNext.setVisibility(View.VISIBLE);
        this.buttonCheck.setVisibility(View.GONE);
        this.buttonFinish.setVisibility(View.VISIBLE);
        this.radioGroup.setVisibility(View.GONE);
        this.asnwersLabel.setVisibility(View.GONE);
        this.explanation.setVisibility(View.VISIBLE);
        this.questionText.setVisibility(View.GONE);
    }

    public void finish(View view){

        if(this.buttonReload.getVisibility() == View.VISIBLE ){
            this.setResult(0);
            this.finish();
        }else{
            int succesResponse = 0;
            int wrongResponse = responseQuestions.size();
            Answer answer = null;
            for (Question question : responseQuestions) {
                answer = question.getAnswers().get(question.getSelectAnswer());
                if (answer.getIndex() == question.getIndexSuccesQuestion()) {
                    succesResponse++;
                    wrongResponse--;
                }

            }
            String result = getResources().getString(R.string.certificado_final_result)+":\n"+
                    getResources().getString(R.string.certificado_total_answers)+": "+responseQuestions.size()+":\n"+
                    getResources().getString(R.string.certificado_succes_answers)+": "+succesResponse+":\n"+
                    getResources().getString(R.string.certificado_wrong_answers)+": "+wrongResponse+":\n";
            this.explanation.setText(result);
            this.buttonNext.setVisibility(View.GONE);
            this.buttonCheck.setVisibility(View.GONE);
            this.buttonFinish.setVisibility(View.VISIBLE);
            this.radioGroup.setVisibility(View.GONE);
            this.asnwersLabel.setVisibility(View.GONE);
            this.explanation.setVisibility(View.VISIBLE);
            this.buttonReload.setVisibility(View.VISIBLE);
            this.questionText.setVisibility(View.GONE);
        }
    }

    public void reload(View v){
        this.buttonReload.setVisibility(View.GONE);
        this.initTest();
    }


}
