import java.util.*;

//Modify the class header to implement the appropriate interface
public class OctagonComparator implements Comparator<Octagon> { 
    
    public int compare(Octagon o1, Octagon o2) {
        if (o1.getSide() > o2.getSide()) { return 1;}
        else if (o1.getSide() == o2.getSide()) { return 0;}
        else {return -1;}
    }
    
    //then implement the method(s) in the interface

}


