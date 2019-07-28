package de.fh_zwickau.timemanagement2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeContainerFragment extends Fragment implements Serializable {
    private HomeFragment homeFragment;
    public static HomeContainerFragment newInstance() {
        HomeContainerFragment containerFragment = new HomeContainerFragment();
        Bundle args = new Bundle();
        containerFragment.setArguments(args);
        return containerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_container, container, false);
        homeFragment = HomeFragment.newInstance(this);
        getChildFragmentManager().beginTransaction().add(R.id.home_frag_cont, homeFragment).commit();
        getChildFragmentManager().beginTransaction().show(homeFragment).commit();

        return view;
        // return super.onCreateView(R.layout.fragment_home_container, container, savedInstanceState);
    }
    public void addAndShowEditTaskFragment(EditTaskFragment editTaskFragment) {
        getChildFragmentManager().beginTransaction().add(R.id.home_frag_cont, editTaskFragment).commit();
        getChildFragmentManager().beginTransaction().show(editTaskFragment).commit();
        getChildFragmentManager().beginTransaction().hide(homeFragment).commit();
    }
    public void removeEditAndShowHomeFragment(EditTaskFragment editTaskFragment) {
        getChildFragmentManager().beginTransaction().remove(editTaskFragment).commit();
        //getChildFragmentManager().beginTransaction().show(homeFragment).commit();
//        Fragment currentFragment = getFragmentManager().findFragmentByTag("YourFragmentTag");
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.detach(currentFragment);
//        fragmentTransaction.attach(currentFragment);
//        fragmentTransaction.commit();
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }


}
