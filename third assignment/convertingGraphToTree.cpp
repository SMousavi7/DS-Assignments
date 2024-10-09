#include<iostream>
#include<bits/stdc++.h>
using namespace std;
void DFS(int v, int parent);
void DFS_for_connected_components(int vertex, int index);

const int MaxN = 2000 + 10;
int n, m, visited[MaxN];
vector<int> adj[MaxN];
int removed = 0;
vector<int> for_components[MaxN];
int visiting[MaxN];
int matrix[MaxN][MaxN];

int main()
{
    cin >> n >> m;
    for(int i = 0; i < m; i++)
    {
        int x, y;
        cin >> x >> y;
        adj[x].push_back(y);
        adj[y].push_back(x);
        matrix[x][y] = matrix[y][x] = 1;
    }



    for(int i = 1; i <= n; i++)
    {
        if(!visited[i])
        {
            DFS(i, -1);
        }
    }
    
    cout << removed / 2 << '\n';
   for(int i = 0; i <= n; i++)
   {
        for(int j = i + 1; j <= n; j++)
        {
            if(matrix[i][j] == -1)
            {
                cout << i << ' ' << j << '\n';
            }
        }
   }

    int index = 0;
    for(int i = 1; i <= n; i++)
    {
        if(!visiting[i])
        {
            DFS_for_connected_components(i, index);
            index++;
        }
    }
    cout << index - 1 << '\n';
    for(int i = 1; i < index; i++)
    {
        cout << for_components[0].at(0) << ' ' << for_components[i].at(0) << '\n';
    }

    return 0;
}

void DFS(int v, int parent)
{
    visited[v] = 1;
    for(int vertex: adj[v])
    {
        if(!visited[vertex])
        {
            DFS(vertex, v);
        }
        else
        {
            if(vertex != parent)
            {
                matrix[vertex][v] = matrix[v][vertex] = -1;
                removed++;
            }
        }
    }
}

void DFS_for_connected_components(int vertex, int index)
{
    visiting[vertex] = 1;
    for_components[index].push_back(vertex);
    for(int i = 0; i < adj[vertex].size(); i++)
    {
        int v = adj[vertex].at(i);
        if(!visiting[v])
        {
            DFS_for_connected_components(v, index);
        }
    }
} 
