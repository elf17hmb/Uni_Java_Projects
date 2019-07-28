package de.fh_zwickau.timemanagement2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class QuestEndFragment extends Fragment implements Serializable {
    private static final String QUEST_BASIC_FRAGMENT_KEY = "quest_basic_fragment_key";
    private transient View questEndView;
    public static QuestEndFragment newInstance(QuestBasicFragment questBasicFragment) {
        QuestEndFragment questEndFragment = new QuestEndFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUEST_BASIC_FRAGMENT_KEY, questBasicFragment);
        questEndFragment.setArguments(args);
        return questEndFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        questEndView = inflater.inflate(R.layout.fragment_quest_end, container, false);
        final QuestBasicFragment questBasicFragment =
                (QuestBasicFragment) getArguments().getSerializable(QUEST_BASIC_FRAGMENT_KEY);
        TextView scoreView = questEndView.findViewById(R.id.txt_quest_end_final_score);
        TextView shortScoreView = questEndView.findViewById(R.id.txt_quest_end_factor1_score);
        TextView attitudesScoreView = questEndView.findViewById(R.id.txt_quest_end_factor2_score);
        TextView longScoreView = questEndView.findViewById(R.id.txt_quest_end_factor3_score);
        double finalScore = questBasicFragment.calculateFinalScore();
        String scoreString = getFormatedScore(finalScore);
        scoreView.setText(scoreString + "%");

        double shortRangeScore = questBasicFragment.calculateTypeFinalScore(QuestionType.SHORT_RANGE_PLANNING);
        String shortScore = getFormatedScore(shortRangeScore);
        shortScoreView.setText(shortScore + "%");

        double attitudesScore = questBasicFragment.calculateTypeFinalScore(QuestionType.TIME_ATTITUDES);
        String attScore = getFormatedScore(attitudesScore);
        attitudesScoreView.setText(attScore + "%");

        double longRangeScore = questBasicFragment.calculateTypeFinalScore(QuestionType.LONG_RANGE_PLANNING);
        String longScore = getFormatedScore(longRangeScore);
        longScoreView.setText(longScore + "%");
        ImageView cancelView = questEndView.findViewById(R.id.imgViewAgain);
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questBasicFragment.reset();
                questBasicFragment.removeEndFragment(QuestEndFragment.this);
                questBasicFragment.showFragment(0);
            }
        });
        return questEndView;
    }
    private String getFormatedScore(double score) {
        String scoreString;
        if (score % 1 == 0) {
            scoreString = String.format("%.0f", score);
        } else {
            scoreString = String.format("%.2f", score);
        }
        return scoreString;
    }

}
