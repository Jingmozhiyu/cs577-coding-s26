#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    if (!(cin >> n >> k)) return 0;

    vector<int> w(n);
    for (int i = 0; i < n; i++) {
        cin >> w[i];
    }

    int s = 1 << n;
    vector<pair<int, int>> dp(s, {INF, INF});
    dp[0] = {1, 0};

    for (int mask = 0; mask < s; mask++) {
        if (dp[mask].first == INF) continue;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue;
            int nextMask = mask | (1 << i);

            if (nextMask >= s) continue;

            if (w[i] <= k - dp[mask].second) {
                int currTrip = dp[mask].first;
                int currWeight = dp[mask].second + w[i];
                if (currTrip < dp[nextMask].first) {
                    dp[nextMask] = {currTrip, currWeight};
                } else if (currTrip == dp[nextMask].first) {
                    if (currWeight < dp[nextMask].second) {
                        dp[nextMask] = {currTrip, currWeight};
                    }
                }
            } else {
                int currTrip = dp[mask].first + 1;
                int currWeight = w[i];
                if (currTrip < dp[nextMask].first) {
                    dp[nextMask] = {currTrip, currWeight};
                } else if (currTrip == dp[nextMask].first) {
                    if (currWeight < dp[nextMask].second) {
                        dp[nextMask] = {currTrip, currWeight};
                    }
                }
            }
        }
    }

    cout << dp[s - 1].first << "\n";
    return 0;
}