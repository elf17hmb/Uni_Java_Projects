package de.fh_zwickau.timemanagement2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;

public class QuestionFragment extends Fragment implements Serializable {
    private static final String QUESTION_TEXT_KEY = "de.fh-zwickau.question_text_key";
    private static final String POSITION_KEY = "de.fh-zwickau.position_key";
    private static final String NORMAL_SCORED_KEY = "de.fh-zwickau.normal_scored_key";
    private static final String TYPE_KEY = "de.fh-zwickau.type_key";
    private static final String BASIC_FRAGMENT_KEY = "de.fh-zwickau.basic_fragment_key";

    private int position;
    private QuestBasicFragment questBasicFragment;
    private transient View questView;
    private boolean normalScored;
    private transient RadioGroup radioGroup;
    private QuestionType questionType;
    public static QuestionFragment newInstance(String questionText, int position,
                                               boolean normalScored, QuestionType type, QuestBasicFragment basicFragment) {
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_TEXT_KEY, questionText);
        args.putInt(POSITION_KEY, position);
        args.putBoolean(NORMAL_SCORED_KEY, normalScored);
        args.putSerializable(BASIC_FRAGMENT_KEY, basicFragment);
        args.putSerializable(TYPE_KEY, type);
        questionFragment.setArguments(args);
        return questionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        questView = inflater.inflate(R.layout.fragment_quest_1, container, false);
        questBasicFragment = (QuestBasicFragment) getArguments().getSerializable(BASIC_FRAGMENT_KEY);
        position = getArguments().getInt(POSITION_KEY);
        normalScored = getArguments().getBoolean(NORMAL_SCORED_KEY);
        questionType = (QuestionType) getArguments().getSerializable(TYPE_KEY);
        reverseRButtons();
        final TextView headerTxtView = questView.findViewById(R.id.quest1_txt_header);
        headerTxtView.setText("Question " + position + "/" + questBasicFragment.getNumberOfQuestions());
        TextView questTxtView = questView.findViewById(R.id.quest1_txt);
        questTxtView.setText(getArguments().getString(QUESTION_TEXT_KEY));
        ImageView imgViewNext = questView.findViewById(R.id.imgViewNext1);
        radioGroup = questView.findViewById(R.id.radio_group_quest);
        imgViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer();
                checkLastQuestion();
                questBasicFragment.addTypeNumber(questionType);
                questBasicFragment.showFragment(position + 1);
            }
        });
        ImageView imgViewClose = questView.findViewById(R.id.imgViewClose);
        imgViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder endDialog = new AlertDialog.Builder(getActivity());
                //endDialog.setMessage("Do you really want to finish current Questionnaire?\n" +
                //        "All you data (Answers) will be not saved! ");
                TextView msg = new TextView(getActivity());
                msg.setText("Do you really want to finish current Questionnaire?\n" +
                                "All you data (Answers) will be not saved!");
                msg.setTextSize(18);
                msg.setPadding(20, 60, 20, 40);
                msg.setTextColor(getActivity().getResources().getColor(R.color.black));
                msg.setGravity(Gravity.CENTER_HORIZONTAL);
                msg.setGravity(Gravity.CENTER);

                endDialog.setView(msg);

                endDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        questBasicFragment.reset();
                        questBasicFragment.showFragment(0);
                    }
                });

                endDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close imgViewClose
                    }
                });
                endDialog.show();

            }
        });
        return questView;
    }
    private void reverseRButtons() {
        if (!normalScored) {
            RadioButton button_1 = questView.findViewById(R.id.radioButton_1);
            RadioButton button_2 = questView.findViewById(R.id.radioButton_2);
            RadioButton button_4 = questView.findViewById(R.id.radioButton_4);
            RadioButton button_5 = questView.findViewById(R.id.radioButton_5);
            String btn_1_text = button_1.getText().toString();
            String btn_2_text = button_2.getText().toString();
            String btn_4_text = button_4.getText().toString();
            String btn_5_text = button_5.getText().toString();
            button_1.setText(btn_5_text);
            button_2.setText(btn_4_text);
            button_4.setText(btn_2_text);
            button_5.setText(btn_1_text);
        }
    }
    private void sendAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButton_1) {
            questBasicFragment.sendAnswer(0, questionType);
        } else if (selectedId == R.id.radioButton_2) {
            questBasicFragment.sendAnswer(25, questionType);
        } else if (selectedId == R.id.radioButton_3) {
            questBasicFragment.sendAnswer(50, questionType);
        } else if (selectedId == R.id.radioButton_4) {
            questBasicFragment.sendAnswer(75, questionType);
        } else if (selectedId == R.id.radioButton_5) {
            questBasicFragment.sendAnswer(100, questionType);
        }
    }
    private void checkLastQuestion() {
        if (position == questBasicFragment.getNumberOfQuestions()) {
            QuestEndFragment questEndFragment = QuestEndFragment.newInstance(questBasicFragment);
            questBasicFragment.addEndFragment(questEndFragment);
        }
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
