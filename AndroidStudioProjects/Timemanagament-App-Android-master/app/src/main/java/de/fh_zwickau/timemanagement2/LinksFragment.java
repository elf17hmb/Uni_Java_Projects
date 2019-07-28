package de.fh_zwickau.timemanagement2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

public class LinksFragment extends Fragment implements Serializable {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        TextView link1 = view.findViewById(R.id.link1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView link2 = view.findViewById(R.id.link2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView link3 = view.findViewById(R.id.link3);
        link3.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}
