package com.asd.logic;

import com.asd.repository.MapsApi;

public class MST {
    int N;
    MapsApi api;
    Vertex vertex[];
    long adjMatrixArray[][];
    boolean connected[][];
    Vertex currentVertex;

    public MST(MapsApi api) {
        this.api = api;
    }

    public void create(String[] places) {
        this.N = places.length;
        this.vertex = new Vertex[N];
        this.adjMatrixArray = new long[N][N];
        this.connected = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            this.vertex[i] = new Vertex(i, places[i]);
        }
        createMatrix();
    }

    private void createMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                System.out.println(vertex[i].placeName + " " + vertex[j].placeName);
                System.out.println(api.getWeight(vertex[i].placeName, vertex[j].placeName));
                adjMatrixArray[i][j] = api.getWeight(vertex[i].placeName, vertex[j].placeName);
            }
        }
    }

    public long Prim(int start) {
        long totalCost = 0;
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
            for (int i = 0; i < N; i++) {
                if (adjMatrixArray[index][i] != 0 && vertex[i].finished == false
                        && adjMatrixArray[index][i] < vertex[i].key) {
                    vertex[i].parent = vertex[index];
                    vertex[i].key = adjMatrixArray[index][i];
                    currentVertex = vertex[i];
                }
            }
        }

        for (int i = 1; i < N; i++) {
            totalCost += adjMatrixArray[vertex[i].num][vertex[i].parent.num];
        }
        return totalCost;
    }

    public int findNumOfChild(Vertex vertex) {
        if (vertex.parent == null) {
            return 1;
        } else {
            return findNumOfChild(vertex.parent) + 1;
        }

    }

    public Vertex[] getVertices() {
        return this.vertex;
    }

    public String getPath() {
        Vertex curVertex = vertex[N - 1];
        for (int i = 0; i < N; i++) {
            if (findNumOfChild(vertex[i]) == N) {
                curVertex = vertex[i];
                break;
            }
        }

        String path = "";
        while (true) {
            if (curVertex.parent == null) {
                path += curVertex.placeName;
                break;
            }
            path += curVertex.placeName + " <= ";
            curVertex = curVertex.parent;
        }
        return path;
    }

}
