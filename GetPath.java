
import java.util.ArrayList;

    
    //Fill in your answer for this method 
    public class GetPath {

    public static ArrayList<Point> ppp;
        
    public static boolean getPath(int r, int c, ArrayList<Point> path, final int[][] grid) {
        getPathHelper(r,c,path,grid);
        
        if (path!=null){
            path.clear();
        }
        
        if (ppp!=null){
            for (int i = 0; i < ppp.size(); i++) {
                path.add(ppp.get(i));
            }
            ppp.clear();
        }
        
        if (path!=null){
            if (path.size()!=0){
                return true;
            } 
        }
        return false;
    }



    //Fill in your answer for this method
    public static void getPathHelper(int r, int c, ArrayList<Point> path, final int[][] grid) {

        if (path.isEmpty()) {
            path.add(new Point(0, 0));
        }

        //get variables of current Point
        Point currentPoint = path.get(path.size() - 1);
        int currentX = currentPoint.x;
        int currentY = currentPoint.y;

        //found it
        if (currentX==r && currentY==c){
            ppp = path;
            return;
        }

        //hit a block
        if (grid[currentX][currentY]==1){
            return;
        }


        //make 2 new paths for recursive
        ArrayList<Point> pathRight = new ArrayList<Point>();
        ArrayList<Point> pathDown = new ArrayList<Point>();
        for (int i = 0; i < path.size(); i++) {
            pathRight.add(path.get(i));
            pathDown.add(path.get(i));
        }
        
        //check and move downwards
        if (currentX < r) {
            pathRight.add(new Point(currentX + 1, currentY));
            getPathHelper(r, c, pathRight, grid);
        }
        
        //check and move rightwards
        if (currentY < c) {
            pathDown.add(new Point(currentX, currentY + 1));
            getPathHelper(r, c, pathDown, grid);
        }
    }

    
}


//You do not need to change any code below. 
class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x=x;
        this.y=y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

