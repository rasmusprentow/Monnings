package com.dyto.monnings;

public enum Direction {

    LEFT, RIGHT, STILL;
    
    public Direction getOpposite(){
        switch(this)
        {
        case LEFT: 
            return RIGHT;
        case RIGHT:
            return LEFT;
        default:
            return STILL;
        }
    }

    public int getNumeralValue() {
        switch(this)
        {
        case LEFT: 
            return -1;
        case RIGHT:
            return 1;
        default:
            return 0;
        }
    }
}
