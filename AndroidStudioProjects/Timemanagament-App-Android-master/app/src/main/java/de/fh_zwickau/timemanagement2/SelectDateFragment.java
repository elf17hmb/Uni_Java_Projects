package de.fh_zwickau.timemanagement2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class SelectDateFragment extends DialogFragment {
    private static final String ADD_FRAGMENT_KEY = "de.fh_zwikcau.de.add_fragment_key";
    public static SelectDateFragment newInstance(AddFragment addFragment) {
        SelectDateFragment selectDateFragment = new SelectDateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ADD_FRAGMENT_KEY, addFragment);
        selectDateFragment.setArguments(args);
        return  selectDateFragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AddFragment addFragment = (AddFragment) getArguments().getSerializable(ADD_FRAGMENT_KEY);
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(addFragment.getContext(), (DatePickerDialog.OnDateSetListener) addFragment, yy, mm, dd);
    }

}
