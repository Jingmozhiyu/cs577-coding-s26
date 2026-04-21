#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n, w, h;
    if (!(cin >> m >> n >> w >> h)) return 0;

    long long lo = INT_MAX;
    long long hi = INT_MIN;
    vector<vector<int>> arr(m, vector<int>(n));

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
            if (arr[i][j] < lo) lo = arr[i][j];
            if (arr[i][j] > hi) hi = arr[i][j];
        }
    }

    vector<vector<int>> prefix(m + 1, vector<int>(n + 1, 0));

    while (lo < hi) {
        long long mid = lo + (hi - lo) / 2;


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i - 1][j - 1] <= mid) {
                    prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
                } else {
                    prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + 1;
                }
            }
        }

        bool valid = true;
        for (int i = 1; i <= m - w + 1; i++) {
            for (int j = 1; j <= n - h + 1; j++) {
                int cnt = prefix[i + w - 1][j + h - 1] - prefix[i - 1][j + h - 1] - prefix[i + w - 1][j - 1] + prefix[i - 1][j - 1];


                if (cnt > w * h / 2) {
                    valid = false;
                    lo = mid + 1;
                    break;
                }
            }
            if (!valid) break;
        }

        if (valid) {
            hi = mid;
        }
    }

    cout << lo << "\n";
    return 0;
}