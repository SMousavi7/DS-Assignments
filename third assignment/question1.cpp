#include <iostream>
#include <bits/stdc++.h>
#include <vector>

using namespace std;

struct MinHeapNode {
    int element;
    int i;
    int j;
};

void swap(MinHeapNode* x, MinHeapNode* y);

class MinHeap {
    MinHeapNode* harr;
    int heap_size;

public:
    MinHeap(MinHeapNode a[], int size);
    void MinHeapify(int);
    int left(int i) { return (2 * i + 1); }
    int right(int i) { return (2 * i + 2); }
    MinHeapNode getMin() { return harr[0]; }
    void replaceMin(MinHeapNode x);
};

MinHeap::MinHeap(MinHeapNode a[], int size) {
    heap_size = size;
    harr = a;
    int i = (heap_size - 1) / 2;
    while (i >= 0) {
        MinHeapify(i);
        i--;
    }
}

void MinHeap::MinHeapify(int i) {
    int l = left(i);
    int r = right(i);
    int smallest = i;

    if (l < heap_size && harr[l].element < harr[i].element)
        smallest = l;

    if (r < heap_size && harr[r].element < harr[smallest].element)
        smallest = r;

    if (smallest != i) {
        swap(harr[i], harr[smallest]);
        MinHeapify(smallest);
    }
}

void MinHeap::replaceMin(MinHeapNode x) {
    harr[0] = x;
    MinHeapify(0);
}

void findSmallestRange(vector<vector<int>>& arr, int K) {
    int range = INT_MAX;
    int min = INT_MAX, max = INT_MIN;
    int start, end;

    MinHeapNode* harr = new MinHeapNode[K];
    for (int i = 0; i < K; i++) {
        harr[i].element = arr[i][0];
        harr[i].i = i;
        harr[i].j = 1;
        if (harr[i].element > max)
            max = harr[i].element;
    }

    MinHeap hp(harr, K);

    while (1) {
        MinHeapNode root = hp.getMin();
        min = hp.getMin().element;

        if (range > max - min + 1) {
            range = max - min + 1;
            start = min;
            end = max;
        }

        if (root.j < arr[root.i].size()) {
            root.element = arr[root.i][root.j];
            root.j += 1;
            if (root.element > max)
                max = root.element;
        } else
            break;

        hp.replaceMin(root);
    }

    cout << start << " " << end;
}

int main() {
    int numLists;
    cin >> numLists;

    vector<int> listSizes(numLists);
    for (int i = 0; i < numLists; i++) {
        cin >> listSizes[i];
    }

    vector<vector<int>> array(numLists);
    for (int i = 0; i < numLists; i++) {
        array[i].resize(listSizes[i]);
        for (int j = 0; j < listSizes[i]; j++) {
            cin >> array[i][j];
        }
    }

    findSmallestRange(array, numLists);

    return 0;
}
