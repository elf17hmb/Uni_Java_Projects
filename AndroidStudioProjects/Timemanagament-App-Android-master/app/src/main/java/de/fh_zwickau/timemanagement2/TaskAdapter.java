package de.fh_zwickau.timemanagement2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial
/**
 * Adapter for Task
 */
public class TaskAdapter extends ArrayAdapter<Task> implements View.OnClickListener, Serializable {
    private ArrayList<Task> tasks;
    transient Context mContext;

    private static class ViewHolder {
        TextView txtText;
        TextView txtDate;
        ImageView imgUrgency;
        ImageView imgDone;
    }

    public TaskAdapter(ArrayList<Task> tasks, @NonNull Context context) {
        super(context, R.layout.row_item, tasks);
        this.tasks = tasks;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        Object object = getItem(position);
        Task task = (Task) object;
        switch (v.getId()) {
            case R.id.imgDone:
                if (task.isDone() == false) {
                    Snackbar.make(v, "Done", Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
                task.setDone(true);
                break;
        }
    }

    private int lastPosition = -1;

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.imgUrgency = convertView.findViewById(R.id.urgencyView);
            viewHolder.txtText = convertView.findViewById(R.id.taskText);
            viewHolder.txtDate = convertView.findViewById(R.id.dateText);
            viewHolder.imgDone = convertView.findViewById(R.id.imgDone);
            viewHolder.imgDone.setBackgroundResource(R.drawable.ic_done_ghost_24dp);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
//        Animation animation = AnimationUtils.loadAnimation(mContext,
//                (position > lastPosition)? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtText.setText(task.getText());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = df.format(task.getDate());
        viewHolder.txtDate.setText(currentDate);
        int color = setUrgencyColor(task.getUrgency());
        viewHolder.imgUrgency.setBackgroundColor(color);
        viewHolder.imgDone = convertView.findViewById(R.id.imgDone);
        viewHolder.imgDone.setOnClickListener(this);
        viewHolder.imgDone.setTag(position);
        if (task.isDone() == true) {
            viewHolder.imgDone.setBackgroundResource(R.drawable.ic_done_green_24dp);
            //viewHolder.imgDone.setBackgroundResource(R.drawable);
        } else {
            viewHolder.imgDone.setBackgroundResource(R.drawable.ic_done_ghost_24dp);
        }
        return convertView;
    }

    /**
     *  Changes int color depends on Urgency
     * @param urgency
     * @return colors number
     */
    private int setUrgencyColor(Urgency urgency) {
        int color = Color.WHITE;
        if (urgency.equals(Urgency.UI)) {
            //RED
            color = Color.parseColor("#FF0000");
        } else if (urgency.equals(Urgency.UNI)) {
            //ORANGE
            color = Color.parseColor("#ffa500");
        } else if (urgency.equals(Urgency.NUI)) {
            //YELLOW
            color = Color.parseColor("#eae22a");
        } else if (urgency.equals(Urgency.NUNI)) {
            //RED
            color = Color.parseColor("#FFFFFF");
        }
        return color;
    }
}
