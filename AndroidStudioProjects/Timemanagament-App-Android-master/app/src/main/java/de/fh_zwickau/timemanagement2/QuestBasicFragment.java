package de.fh_zwickau.timemanagement2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;


public class QuestBasicFragment extends Fragment implements Serializable {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private double overallScore;
    private double shortRangeScore;
    private double attitudesScore;
    private double longRangeScore;

    private double shortRangeNumber;
    private double attitudesNumber;
    private double longRangeNumber;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View questBasicView = inflater.inflate(R.layout.fragment_quest_basic, container, false);
        shortRangeScore = 0;
        attitudesNumber = 0;
        longRangeNumber = 0;
        createFragments();
        addFragments();
        showFragment(0);
        return questBasicView;
    }
    private void createFragments() {
        QuestStartFragment questStartFragment = QuestStartFragment.newInstance(this);
        String textQuestion_1 = getContext().getString(R.string.quest_1);
        String textQuestion_2 = getContext().getString(R.string.quest_2);
        String textQuestion_3 = getContext().getString(R.string.quest_3);
        String textQuestion_4 = getContext().getString(R.string.quest_4);
        String textQuestion_5 = getContext().getString(R.string.quest_5);
        String textQuestion_6 = getContext().getString(R.string.quest_6);
        String textQuestion_7 = getContext().getString(R.string.quest_7);

        String textQuestion_8a = getContext().getString(R.string.quest_8_a);
        String textQuestion_9 = getContext().getString(R.string.quest_9);
        String textQuestion_10a = getContext().getString(R.string.quest_10_a);
        String textQuestion_11a = getContext().getString(R.string.quest_11_a);
        String textQuestion_12 = getContext().getString(R.string.quest_12);
        String textQuestion_13a = getContext().getString(R.string.quest_13_a);

        String textQuestion_14 = getContext().getString(R.string.quest_14);
        String textQuestion_15 = getContext().getString(R.string.quest_15);
        String textQuestion_16a = getContext().getString(R.string.quest_16_a);
        String textQuestion_17 = getContext().getString(R.string.quest_17);
        String textQuestion_18 = getContext().getString(R.string.quest_18);

        QuestionFragment questionFgt_1 = QuestionFragment
                .newInstance(textQuestion_1, 1, true, QuestionType.SHORT_RANGE_PLANNING, this);
        QuestionFragment questionFgt_2 = QuestionFragment
                .newInstance(textQuestion_2, 2, true, QuestionType.SHORT_RANGE_PLANNING,this);
        QuestionFragment questionFgt_3 = QuestionFragment
                .newInstance(textQuestion_3, 3, true, QuestionType.SHORT_RANGE_PLANNING, this);
        QuestionFragment questionFgt_4 = QuestionFragment
                .newInstance(textQuestion_4, 4, true, QuestionType.SHORT_RANGE_PLANNING, this);
        QuestionFragment questionFgt_5 = QuestionFragment
                .newInstance(textQuestion_5, 5, true, QuestionType.SHORT_RANGE_PLANNING, this);
        QuestionFragment questionFgt_6 = QuestionFragment
                .newInstance(textQuestion_6, 6, true, QuestionType.SHORT_RANGE_PLANNING, this);
        QuestionFragment questionFgt_7 = QuestionFragment
                .newInstance(textQuestion_7, 7, true, QuestionType.SHORT_RANGE_PLANNING, this);

        QuestionFragment questionFgt_8 = QuestionFragment
                .newInstance(textQuestion_8a, 8, false, QuestionType.TIME_ATTITUDES, this);
        QuestionFragment questionFgt_9 = QuestionFragment
                .newInstance(textQuestion_9, 9, true, QuestionType.TIME_ATTITUDES, this);
        QuestionFragment questionFgt_10 = QuestionFragment
                .newInstance(textQuestion_10a, 10, false, QuestionType.TIME_ATTITUDES, this);
        QuestionFragment questionFgt_11 = QuestionFragment
                .newInstance(textQuestion_11a, 11, false, QuestionType.TIME_ATTITUDES, this);
        QuestionFragment questionFgt_12 = QuestionFragment
                .newInstance(textQuestion_12, 12, true, QuestionType.TIME_ATTITUDES, this);
        QuestionFragment questionFgt_13 = QuestionFragment
                .newInstance(textQuestion_13a, 13, false, QuestionType.TIME_ATTITUDES, this);

        QuestionFragment questionFgt_14 = QuestionFragment
                .newInstance(textQuestion_14, 14, true, QuestionType.LONG_RANGE_PLANNING, this);
        QuestionFragment questionFgt_15 = QuestionFragment
                .newInstance(textQuestion_15, 15, true, QuestionType.LONG_RANGE_PLANNING, this);
        QuestionFragment questionFgt_16 = QuestionFragment
                .newInstance(textQuestion_16a, 16, false, QuestionType.LONG_RANGE_PLANNING, this);
        QuestionFragment questionFgt_17 = QuestionFragment
                .newInstance(textQuestion_17, 17, true, QuestionType.LONG_RANGE_PLANNING, this);
        QuestionFragment questionFgt_18 = QuestionFragment
                .newInstance(textQuestion_18, 18, true, QuestionType.LONG_RANGE_PLANNING, this);

        fragments.add(0, questStartFragment);
        fragments.add(1, questionFgt_1);
        fragments.add(2, questionFgt_2);
        fragments.add(3, questionFgt_3);
        fragments.add(4, questionFgt_4);
        fragments.add(5, questionFgt_5);
        fragments.add(6, questionFgt_6);
        fragments.add(7, questionFgt_7);
        fragments.add(8, questionFgt_8);
        fragments.add(9, questionFgt_9);
        fragments.add(10, questionFgt_10);
        fragments.add(11, questionFgt_11);
        fragments.add(12, questionFgt_12);
        fragments.add(13, questionFgt_13);
        fragments.add(14, questionFgt_14);
        fragments.add(15, questionFgt_15);
        fragments.add(16, questionFgt_16);
        fragments.add(17, questionFgt_17);
        fragments.add(18, questionFgt_18);
    }
    public void showFragment(int position) {
        FragmentManager fragmentManager = getChildFragmentManager();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                fragmentManager.beginTransaction().show(fragments.get(i)).commit();
            } else {
                fragmentManager.beginTransaction().hide(fragments.get(i)).commit();
            }
        }
    }

    private void addFragments() {
        FragmentManager fragmentManager = getChildFragmentManager();
        for (Fragment fragment: fragments) {
            fragmentManager.beginTransaction().add(R.id.frag_cont_quest, fragment).commit();
        }
    }

    public void sendAnswer(double answer, QuestionType type) {
        overallScore += answer;
        if (type == QuestionType.SHORT_RANGE_PLANNING) {
            shortRangeScore += answer;
        } else if (type == QuestionType.TIME_ATTITUDES) {
            attitudesScore += answer;
        } else if (type == QuestionType.LONG_RANGE_PLANNING) {
            longRangeScore += answer;
        }
    }

    public int getNumberOfQuestions() {
        return fragments.size() - 1;
    }
    public double calculateFinalScore() {
        double finalScore = overallScore / (getNumberOfQuestions() - 1);
        return finalScore;
    }
    public double calculateTypeFinalScore(QuestionType type) {
        double finalScore = 0;
        if (type == QuestionType.SHORT_RANGE_PLANNING) {
            finalScore = shortRangeScore / shortRangeNumber;
        } else if (type == QuestionType.TIME_ATTITUDES) {
            finalScore = attitudesScore / attitudesNumber;
        } else if (type == QuestionType.LONG_RANGE_PLANNING) {
            finalScore = longRangeScore / longRangeNumber;
        }
        return finalScore;
    }
    public void addEndFragment(QuestEndFragment questEndFragment) {
        fragments.add(questEndFragment);
        getChildFragmentManager().beginTransaction().add(R.id.frag_cont_quest, questEndFragment).commit();
    }
    public void addTypeNumber(QuestionType type) {
        if (type == QuestionType.SHORT_RANGE_PLANNING) {
            shortRangeNumber++;
        } else if (type == QuestionType.TIME_ATTITUDES) {
            attitudesNumber++;
        } else if (type == QuestionType.LONG_RANGE_PLANNING) {
            longRangeNumber++;
        }
    }
    public void reset() {
        overallScore = 0;
        shortRangeNumber = 0;
        shortRangeScore = 0;
        attitudesNumber = 0;
        attitudesScore = 0;
        longRangeNumber = 0;
        longRangeScore = 0;
    }
    public void removeEndFragment(QuestEndFragment endFragment) {
        fragments.remove(endFragment);
        getChildFragmentManager().beginTransaction().remove(endFragment).commit();
    }

}
