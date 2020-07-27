package util;

public class Side {
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private int side;

    public Side(int side){
        this.side = side;
    }

    public int getSide(){
        return side;
    }
}