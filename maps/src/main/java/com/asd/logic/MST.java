package com.asd.logic;

import java.util.Comparator;
import java.util.Queue;

import com.asd.repository.MapsApi;

import java.util.LinkedList;
import java.util.Map;

class Vertex {
    long key;
    boolean finished;
    Vertex parent;
    int num;
    String placeName;

    public Vertex(int num, String placeName) {
        this.num = num;
        key = Integer.MAX_VALUE;
        finished = false;
        this.placeName = placeName;
    }

    public Vertex(Vertex vertex) {
        this.key = vertex.key;
        this.finished = vertex.finished;
        this.parent = vertex.parent;
        this.num = vertex.num;
    }

   
}
public class MST {
    int N;
    MapsApi api;
    Vertex vertex[];
    long adjMatrixArray[][];
    boolean connected[][];
    int totalCost = 0;

    public MST(int N, MapsApi api) {
        this.api = api;
        this.N = N;
        vertex = new Vertex[N];
        adjMatrixArray = new long[N][N];
        connected = new boolean[N][N];
    }

    private void createMatrix() {
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.println(vertex[i].placeName + " " + vertex[j].placeName);
                System.out.println( api.getWeight(vertex[i].placeName, vertex[j].placeName));
                adjMatrixArray[i][j] = api.getWeight(vertex[i].placeName, vertex[j].placeName);
            }
        }
    }

    public void create(String[] places){
        for (int i = 0; i<N; i++) {
            vertex[i] = new Vertex(i, places[i]);
        }
        createMatrix();
    }



    public void Prim(int start) {
        vertex[start].key = 0;
        vertex[start].parent = null;

        for (int j = 0; j < N; j++) {
            int index = -1;
        long minValue = Long.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (vertex[i].key < minValue && vertex[i].finished == false) {
                    index = i;
                    minValue = vertex[i].key;
                }
            }
            vertex[index].finished = true;
            for (int i = 0; i<N ;i++){
                if(adjMatrixArray[index][i] != 0 && vertex[i].finished == false && adjMatrixArray[index][i] < vertex[i].key){
                    vertex[i].parent = vertex[index];
                    vertex[i].key = adjMatrixArray[index][i];
                }
            }          
        }
        
        System.out.println("Edge \t Weight");
        for(int i =1; i<N; i++){
            totalCost += adjMatrixArray[vertex[i].num][vertex[i].parent.num];
            System.out.println((vertex[i].parent.placeName) + " - " + (vertex[i].placeName) + '\t' +adjMatrixArray[vertex[i].num][vertex[i].parent.num]/3600.0);
        }
        System.out.println("Total Cost : "  + totalCost/3600.0);
    }


}

