package de.fh_zwickau.timemanagement2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;

public class QuestStartFragment extends Fragment implements Serializable {
    private static final String QUEST_BASIC_FRAGMENT_KEY = "quest_basic_fragment_key";
    private static final String FIRTS_QUESTION_FRAGMENT_KEY = "first_question_fragment_key";
    public static QuestStartFragment newInstance(QuestBasicFragment questBasicFragment) {
        QuestStartFragment questStartFragment = new QuestStartFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUEST_BASIC_FRAGMENT_KEY, questBasicFragment);
        questStartFragment.setArguments(args);
        return questStartFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View questView = inflater.inflate(R.layout.fragment_quest_start, container, false);
        ImageView nextImg = questView.findViewById(R.id.nexyImgView);
        nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuestBasicFragment questBasicFragment = (QuestBasicFragment) getArguments()
                        .getSerializable(QUEST_BASIC_FRAGMENT_KEY);
                questBasicFragment.showFragment(1);
            }
        });
        return questView;
    }

}
