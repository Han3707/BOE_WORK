#include <iostream>
#include <vector>
using namespace std;
vector<int> small_graph[100]; // 자기보다 작은거 기준
vector<int> big_graph[100];   // 자기보다 큰 거 기준
bool visited[100];
int n, m;
int ans = 0;

int dfs1(int x) {
    visited[x] = true;
    int cnt = 0;
    for (auto c : small_graph[x]) {
        if (!visited[c])
            cnt += 1 + dfs1(c);
    }
    return cnt;
}

int dfs2(int x) {
    visited[x] = true;
    int cnt = 0;
    for (auto c : big_graph[x]) {
        if (!visited[c])
            cnt += 1 + dfs2(c);
    }
    return cnt;
}

int main() {
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int big, small;
        cin >> big >> small;
        small_graph[small].push_back(big);
        big_graph[big].push_back(small);
    }

    for (int i = 1; i <= n; i++) {
        fill(visited, visited + n + 1, false);
        int smallCount = dfs1(i);
        
        fill(visited, visited + n + 1, false);
        int bigCount = dfs2(i);

        if (smallCount > n / 2 || bigCount > n/2) ans++;
    }
    cout << ans;
}
