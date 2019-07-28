package de.fh_zwickau.timemanagement2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTaskFragment extends Fragment implements Serializable, DatePickerDialog.OnDateSetListener {

    private static final String TASK_KEY = "de.fh_zwickau.task_key";
    private static final String CONTAINER_FRAGMENT_KEY = "de.fh_zwickau.container_fragment_key";
    private Task task;
    private transient ImageView doneImg;
    private transient EditText taskEditText;
    private transient ImageView dateImg;
    private transient TextView dateText;
    private transient RadioGroup radioGroup;
    private transient ImageView closeImg;
    private transient RadioButton ui_RBtn;
    private transient RadioButton uni_RBtn;
    private transient RadioButton nui_RBtn;
    private transient RadioButton nuni_RBtn;
    private transient ImageView deleteImg;
    private Date oldTaskDate;
    private Date newTaskDate;
    private Urgency taskUrgency;
    private String taskString;
    private HomeContainerFragment containerFragment;
    public static EditTaskFragment newInstance(Task task, HomeContainerFragment containerFragment) {
        EditTaskFragment editTaskFragment = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASK_KEY, task);
        args.putSerializable(CONTAINER_FRAGMENT_KEY, containerFragment);
        editTaskFragment.setArguments(args);
        return editTaskFragment;
        //TODO Delete button
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        task = (Task) getArguments().getSerializable(TASK_KEY);
        containerFragment = (HomeContainerFragment) getArguments().getSerializable(CONTAINER_FRAGMENT_KEY);
        doneImg = view.findViewById(R.id.edit_img_done);
        taskEditText = view.findViewById(R.id.edit_txt_text);
        dateImg = view.findViewById(R.id.edit_img_calendar);
        dateText = view.findViewById(R.id.edit_txt_date);
        radioGroup = view.findViewById(R.id.edit_radio_group);
        closeImg = view.findViewById(R.id.edit_img_close);
        ui_RBtn = view.findViewById(R.id.edit_rdb_ui);
        uni_RBtn = view.findViewById(R.id.edit_rdb_uni);
        nui_RBtn = view.findViewById(R.id.edit_rdb_nui);
        nuni_RBtn = view.findViewById(R.id.edit_rdb_nuni);
        deleteImg = view.findViewById(R.id.edit_img_delete);
        initOldValues();
        setOldValuesToViews();

        doneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = taskEditText.getText().toString();
                Urgency newUrgency = null;
                if (ui_RBtn.isChecked()) {
                    newUrgency = Urgency.UI;
                } else if (uni_RBtn.isChecked()) {
                    newUrgency = Urgency.UNI;
                } else if (nui_RBtn.isChecked()) {
                    newUrgency = Urgency.NUI;
                } else if (nuni_RBtn.isChecked()) {
                    newUrgency = Urgency.NUNI;
                }

                if (newTaskDate == null) {
                    newTaskDate = oldTaskDate;
                }
                task.setDate(newTaskDate);
                task.setText(newText);
                task.setUrgency(newUrgency);
                containerFragment.removeEditAndShowHomeFragment(EditTaskFragment.this);
            }
        });
        dateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = EditDateFragment.newInstance(EditTaskFragment.this, oldTaskDate);
                datePicker.show(getActivity().getFragmentManager(), "date_picker");
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerFragment.removeEditAndShowHomeFragment(EditTaskFragment.this);

            }
        });
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getActivity());
                deleteDialog.setMessage("Do you really want to delete this task?");

                deleteDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.deleteTask(task);
                        containerFragment.removeEditAndShowHomeFragment(EditTaskFragment.this);
                    }
                });

                deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //deleteDialog.
                    }
                });
                //deleteDialog.setNegativeButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface().onc)
                deleteDialog.show();
               // MainActivity.deleteTask(task);
                //containerFragment.removeEditAndShowHomeFragment(EditTaskFragment.this);
            }
        });
        return view;
    }
    private void initOldValues() {
        oldTaskDate = task.getDate();
        taskUrgency = task.getUrgency();
        taskString = task.getText();
    }
    private void setOldValuesToViews() {
        taskEditText.setText(taskString);
        dateText.setText(formatDate(oldTaskDate));
        if (taskUrgency.equals(Urgency.UI)) {
            ui_RBtn.setChecked(true);
        } else if (taskUrgency.equals(Urgency.UNI)) {
            uni_RBtn.setChecked(true);
        } else if (taskUrgency.equals(Urgency.NUI)) {
            nui_RBtn.setChecked(true);
        } else if (taskUrgency.equals(Urgency.NUNI)) {
            nuni_RBtn.setChecked(true);
        }
    }
    private String formatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String mon = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        String dateString = day + " " + mon;
        return dateString;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        newTaskDate = calendar.getTime();
        dateText.setText(formatDate(newTaskDate));
    }
}
