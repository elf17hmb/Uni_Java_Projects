package de.fh_zwickau.timemanagement2;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddFragment extends Fragment implements Serializable, DatePickerDialog.OnDateSetListener {

    private transient ImageView doneImg;
    private transient TextView taskText;
    private transient ImageView dateImg;
    private transient TextView dateText;
    private transient RadioGroup radioGroup;
    private Date taskDate;
    public static AddFragment newInstance() {
        AddFragment addFragment = new AddFragment();
        Bundle args = new Bundle();
        addFragment.setArguments(args);
        return addFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        taskText = view.findViewById(R.id.add_txt_text);
        dateText = view.findViewById(R.id.add_txt_date);
        doneImg = view.findViewById(R.id.add_img_done);
        radioGroup = view.findViewById(R.id.add_radio_group);
        taskDate = null;
        doneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = taskText.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                Urgency urgency = null;
                if (selectedId == R.id.add_rdb_ui) {
                    urgency = Urgency.UI;
                } else if (selectedId == R.id.add_rdb_uni) {
                    urgency = Urgency.UNI;
                } else if (selectedId == R.id.add_rdb_nui) {
                    urgency = Urgency.NUI;
                } else if (selectedId == R.id.add_rdb_nuni) {
                    urgency = Urgency.NUNI;
                }
                if (taskDate != null) {
                    MainActivity.addTask(new Task(text, taskDate, urgency));
                    MainActivity.goToHome();
                }
            }
        });
        dateImg = view.findViewById(R.id.add_img_calendar);
        dateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = SelectDateFragment.newInstance(AddFragment.this);
                datePicker.show(getActivity().getFragmentManager(), "date_picker");
            }
        });
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        taskDate = calendar.getTime();
        String mon = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        dateText.setText(dayOfMonth + " " + mon);
        dateText.setTextColor(ContextCompat.getColor(getContext(), R.color.date_color));
        doneImg.setImageResource(R.drawable.ic_done_black_24dp);
    }
}
