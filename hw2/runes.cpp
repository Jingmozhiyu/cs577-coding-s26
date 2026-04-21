#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <list>
#include <map>
#include <iostream>
#include <iomanip>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <unordered_map>
#include <vector>
#define LL long long
#define inf 0x3f3f3f3f
#define INF 0x3f3f3f3f3f3f
#define PI 3.1415926535898
#define F first
#define S second
#define endl '\n'
#define lson  rt << 1
#define rson  rt << 1 | 1
#define lowbit(x) (x &(-x))
#define f(x, y, z) for (int x = (y), __ = (z); x < __; ++x)
#define _rep(i, a, b) for (int i = (a); i <= (b); ++i)
#define _per(i, a, b) for (int i = (a); i >= (b); --i)
using namespace std;

const int maxn = 707;
const int mod = 1e9 + 7;
int n;
string s;
int match[maxn];
LL dp[maxn][maxn][3][3];

int dfs(int l, int r) {
	if (l + 1 == r) {
		dp[l][r][0][1] = dp[l][r][0][2] = dp[l][r][1][0] = dp[l][r][2][0] = 1;
		return 0;
	}
	if (match[l] == r) {
		dfs(l + 1, r - 1);
		_rep(i, 0, 2) {
			_rep(j, 0, 2) {
				if (i != 1) dp[l][r][1][0] = (dp[l][r][1][0] + dp[l + 1][r - 1][i][j]) % mod;
				if (i != 2) dp[l][r][2][0] = (dp[l][r][2][0] + dp[l + 1][r - 1][i][j]) % mod;
				if (j != 1) dp[l][r][0][1] = (dp[l][r][0][1] + dp[l + 1][r - 1][i][j]) % mod;
				if (j != 2) dp[l][r][0][2] = (dp[l][r][0][2] + dp[l + 1][r - 1][i][j]) % mod;
			}
		}
	}
	else {
		dfs(l, match[l]), dfs(match[l] + 1, r);
		_rep(i, 0, 2) {
			_rep(j, 0, 2) {
				_rep(p, 0, 2) {
					_rep(q, 0, 2) {
						if ((j == 1 && p == 1) || (j == 2 && p == 2)) continue;
						dp[l][r][i][q] = (dp[l][r][i][q] + dp[l][match[l]][i][j] * dp[match[l] + 1][r][p][q]) % mod;
					}
				}
			}
		}
	}
	return 0;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cin >> s;
	stack<int> q;
	f(i, 0, s.size()) {
		if (s[i] == '(') q.push(i);
		else {
			match[i] = q.top();
			match[q.top()] = i;
			q.pop();
		}
	}
	dfs(0, s.size() - 1);
	LL ans = 0;
	_rep(i, 0, 2) {
		_rep(j, 0, 2) ans = (ans + dp[0][s.size() - 1][i][j]) % mod;
	}
	cout << ans << endl;
    return 0;
}
