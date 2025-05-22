package com.tilldawn.Models.Map.AStar;

public class AStarNode {
    float x,y;
    float gCost,hCost;
    AStarNode parent;
    public AStarNode(float x, float y, float gCost, float hCost, AStarNode parent) {
        this.x = x;
        this.y = y;
        this.gCost = gCost;
        this.hCost = hCost;
        this.parent = parent;
    }
    public float getFCost(){
        return gCost+hCost;
    }
}
