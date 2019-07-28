package de.fh_zwickau.timemanagement2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeFragment extends Fragment implements Serializable {
    //private static final Task[] tasks = {new Task("DO IT"), new Task("DO IT LATER!"),
     //                                 new Task("Write text"), new Task("Find this")};

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Spinner spinner = getView().findViewById(R.id.taskSpinner);
//        //ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, R.array.task_arts_array);
//    }
    private static final String HOME_CONTAINER_FRAGMENT_KEY = "de.fh_zwickau.home_container_fragment_key";
    private HomeContainerFragment containerFragment;
    public static HomeFragment newInstance(HomeContainerFragment containerFragment) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(HOME_CONTAINER_FRAGMENT_KEY, containerFragment);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    static ArrayList<Task> tasks;
    transient ListView listView;
    private TaskAdapter adapter;
    ArrayList<Task> upcomingTasks;
    ArrayList<Task> doneTasks;
    TaskAdapter adapterDone;
    TaskAdapter adapterFalse;
    String [] arrayTasks = {"All Tasks", "Completed Tasks", "Upcoming Tasks"};
    boolean isSortUrg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        containerFragment = (HomeContainerFragment) getArguments().getSerializable(HOME_CONTAINER_FRAGMENT_KEY);
        listView = view.findViewById(R.id.list_tasks);
        tasks = MainActivity.getTasks();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if(t1.getDate().after(t2.getDate())){
                    return 1;
                } else return -1;
            }
        });
        adapter = new TaskAdapter(tasks, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        Spinner spinner = view.findViewById(R.id.spinnerT);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        tasks = MainActivity.getTasks();
                        adapter = new TaskAdapter(tasks,getActivity().getApplicationContext());
                        listView.setAdapter(adapter); break;
                    case 1:
                        tasks = MainActivity.getTasks();
                        doneTasks = new ArrayList<>();
                        for (Task t: tasks) {
                            if(t.isDone() == true) {
                                doneTasks.add(t);
                            }
                        }
                        adapterDone = new TaskAdapter(doneTasks,getActivity().getApplicationContext());
                        listView.setAdapter(adapterDone);
                        break;
                    case 2:
                        tasks = MainActivity.getTasks();
                        upcomingTasks = new ArrayList<>();
                        for (Task t: tasks) {
                            if(t.isDone() == false) {
                                upcomingTasks.add(t);
                            }
                        }
                        adapterFalse = new TaskAdapter(upcomingTasks,getActivity().getApplicationContext());
                        listView.setAdapter(adapterFalse);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this.getActivity(), android.R.layout.simple_list_item_1, arrayTasks);
        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = tasks.get(position);
                EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(task, containerFragment);
                containerFragment.addAndShowEditTaskFragment(editTaskFragment);
            }
        });



        ImageView imgSort = view.findViewById(R.id.img_sort);
        imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder sortDialog = new AlertDialog.Builder(getActivity());
                String sortString [] = {"Date", "Urgency"};
                sortDialog.setTitle("Sort");
                sortDialog.setSingleChoiceItems(sortString, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            if(listView.getAdapter().equals(adapterDone)) {
                                tasks = doneTasks;
                            } else if(listView.getAdapter().equals(adapterFalse)){
                                tasks = upcomingTasks;
                            }
                            isSortUrg = false;

                            } else if (which == 1) {
                                if(listView.getAdapter().equals(adapterDone)) {
                                    tasks = doneTasks;
                                } else if(listView.getAdapter().equals(adapterFalse)) {
                                    tasks = upcomingTasks;
                                }
                                isSortUrg = true;

                        }
                    }
                });
                sortDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sort();
                    }
                });
                sortDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close sortDialog
                    }
                });
                sortDialog.show();

            }
        });
        return view;
    }

    /**
     * Sorting by Urgency/Date.
     */
    public void sort(){
        if(isSortUrg == false){
            Collections.sort(tasks, new Comparator<Task>() {
                @Override
                public int compare(Task t1, Task t2) {
                    if(t1.getDate().after(t2.getDate())) {
                        return 1;
                    } else return -1;
                }
            });

        } else {
            Collections.sort(tasks, new UrgencyComparator());
        }

        adapter = new TaskAdapter(tasks, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
    }

}
