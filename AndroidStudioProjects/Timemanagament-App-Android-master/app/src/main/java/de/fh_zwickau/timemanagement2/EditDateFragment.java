package de.fh_zwickau.timemanagement2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

public class EditDateFragment extends DialogFragment {
    private static final String EDIT_FRAGMENT_KEY = "de.fh_zwikcau.edit_fragment_key";
    private static final String DATE_KEY = "de.fh_zwickau.date_key";
    public static EditDateFragment newInstance(EditTaskFragment editFragment, Date date) {
        EditDateFragment editDateFragment = new EditDateFragment();
        Bundle args = new Bundle();
        args.putSerializable(EDIT_FRAGMENT_KEY, editFragment);
        args.putSerializable(DATE_KEY, date);
        editDateFragment.setArguments(args);
        return  editDateFragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditTaskFragment editFragment = (EditTaskFragment) getArguments().getSerializable(EDIT_FRAGMENT_KEY);
        Date date = (Date) getArguments().getSerializable(DATE_KEY);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(editFragment.getContext(), (DatePickerDialog.OnDateSetListener) editFragment, yy, mm, dd);
    }
}
