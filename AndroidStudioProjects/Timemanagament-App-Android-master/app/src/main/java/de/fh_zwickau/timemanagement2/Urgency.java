package de.fh_zwickau.timemanagement2;

/**
 *
 */
public enum Urgency {
    UI(1), // Urgent & Important
    UNI(2), //Urgent & Not important
    NUI(3), //Not Urgent & Important
    NUNI(4); //Not Urgent & Not important
    private final int order;
    private Urgency(int order){
        this.order = order;
    }
    public int getOrder(){
        return order;
    }
}
