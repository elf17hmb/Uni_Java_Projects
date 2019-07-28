package de.fh_zwickau.timemanagement2;

import java.util.Comparator;

public class UrgencyComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getUrgency().getOrder()>t2.getUrgency().getOrder()) {
            return 1;
        } else {
            return -1;
        }
    }
}
