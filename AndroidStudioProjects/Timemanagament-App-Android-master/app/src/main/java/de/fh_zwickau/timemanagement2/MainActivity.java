package de.fh_zwickau.timemanagement2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_POSITION = 0;
    private static final int LINKS_POSITION = 1;
    private static final int ADD_POSITION = 2;
    private static final int QUEST_POSITION = 3;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //fragments.add(new HomeFragment());
        fragments.add(HomeContainerFragment.newInstance());
        fragments.add(new LinksFragment());
        fragments.add(AddFragment.newInstance());
        fragments.add(new QuestBasicFragment());
        initFragments();
        showFragment(HOME_POSITION);
        createTasks();
    }
    public static void goToHome() {
        bottomNav.setSelectedItemId(R.id.nav_home);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            showFragment(HOME_POSITION);
                            break;
                        case R.id.nav_links:
                            showFragment(LINKS_POSITION);
                            break;
                        case  R.id.nav_add:
                            showFragment(ADD_POSITION);
                            break;
                        case  R.id.nav_quest:
                            showFragment(QUEST_POSITION);
                            break;
                    }
                    return true;
                }
            };
    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments().isEmpty()) {
            for (Fragment fragment: fragments) {
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }
    }
    private void showFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                fragmentManager.beginTransaction().show(fragments.get(i)).commit();
            } else {
                fragmentManager.beginTransaction().hide(fragments.get(i)).commit();
            }
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task){
        tasks.add(task);
    }
    public static void deleteTask(Task task) {
        tasks.remove(task);
    }
    private void createTasks() {
        tasks.add(new Task("Android Project", new Date(), Urgency.UI));
        tasks.get(0).setDone(true);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.DAY_OF_MONTH, 5);

        tasks.add(new Task("Prepare for the RE exam", calendar.getTime(), Urgency.NUI));

        calendar.set(Calendar.DAY_OF_MONTH, 10);
        tasks.add(new Task("Prepare for the GdP2 exam", calendar.getTime() , Urgency.UNI));

        calendar.set(Calendar.DAY_OF_MONTH, 12);
        tasks.add(new Task("Prepare for the Netzwerke exam", calendar.getTime() , Urgency.NUI));

        calendar.set(Calendar.DAY_OF_MONTH, 17);
        tasks.add(new Task("Prepare for the Android exam", calendar.getTime() , Urgency.UI));

        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        tasks.add(new Task("Lose weight by 5 kg", calendar.getTime(), Urgency.NUNI));

        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 21);
        tasks.add(new Task("Pay Semesterbeitrag", calendar.getTime(), Urgency.NUNI));

        calendar.set(Calendar.DAY_OF_MONTH, 14);
        tasks.add(new Task("Prepare for the WM-2018", new Date(), Urgency.UNI));

        calendar.set(Calendar.MONTH, 9);
        tasks.add(new Task("Take a shower" , calendar.getTime(), Urgency.UI));



    }
}

