package com.tilldawn.Models.Map.AStar;

import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Models.Map.Map;

import java.util.*;


public class AStarPathFinder {
    private final Map map;
    public AStarPathFinder(Map map) {
        this.map = map;
    }
    public List<Vector2> findPath(float startX, float startY, float goalX, float goalY) {
        PriorityQueue<AStarNode> openSet = new PriorityQueue<>(Comparator.comparingDouble(AStarNode::getFCost));
        java.util.Map<String, AStarNode> visited=new HashMap<>();
        AStarNode start=new AStarNode(startX,startY,0,heuristic(startX,startY,goalX,goalY),null);
        openSet.add(start);
        while(!openSet.isEmpty()){
            AStarNode current = openSet.poll();
            if((int)current.x == (int)goalX && (int)current.y == (int)goalY) return reconstructPath(current);
            int[] dx={1,0,1,0,1,1,-1,-1};
            int[] dy={0,1,0,-1,1,-1,1,-1};
            for(int i=0; i<dx.length; i++){
                float nx=current.x+dx[i];
                float ny=current.y+dy[i];
                if(!map.isWalkable(nx,ny)) continue;
                float newG=current.gCost+1;
                AStarNode neighbor=new AStarNode(nx,ny,newG, current.hCost, current);
                String key=nx+","+ny;
                AStarNode existing=visited.get(key);
                if(existing==null || newG<existing.gCost) {
                    visited.put(key,neighbor);
                    openSet.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }
    private float heuristic(float x1, float y1, float x2, float y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan
    }
    private List<Vector2> reconstructPath(AStarNode node){
        List<Vector2> path=new LinkedList<>();
        while(node!=null){
            path.add(0,new Vector2(node.x,node.y));
            node=node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
