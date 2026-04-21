#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

struct SegmentTree {
    int n;
    vector<long long> tree;
    vector<int> idx;
    vector<long long> lazyAdd;
    vector<long long> lazySet;
    vector<bool> isSet;

    SegmentTree(const vector<int>& arr) {
        n = arr.size();
        tree.assign(n * 4, 0);
        idx.assign(n * 4, 0);
        lazyAdd.assign(n * 4, 0);
        lazySet.assign(n * 4, 0);
        isSet.assign(n * 4, false);
        build(arr, 1, 0, n - 1);
    }

    void pushUp(int node) {
        tree[node] = max(tree[node * 2], tree[node * 2 + 1]);
        idx[node] = tree[node * 2] >= tree[node * 2 + 1] ? idx[node * 2] : idx[node * 2 + 1];
    }

    void pushDown(int node, int l, int r) {
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        if (isSet[node]) {
            long long val = lazySet[node];

            isSet[leftNode] = true;
            lazySet[leftNode] = val;
            lazyAdd[leftNode] = 0;
            tree[leftNode] = val;

            isSet[rightNode] = true;
            lazySet[rightNode] = val;
            lazyAdd[rightNode] = 0;
            tree[rightNode] = val;

            isSet[node] = false;
            lazySet[node] = 0;
        }

        if (lazyAdd[node] != 0) {
            long long val = lazyAdd[node];

            if (isSet[leftNode]) {
                lazySet[leftNode] += val;
            } else {
                lazyAdd[leftNode] += val;
            }
            tree[leftNode] += val;

            if (isSet[rightNode]) {
                lazySet[rightNode] += val;
            } else {
                lazyAdd[rightNode] += val;
            }
            tree[rightNode] += val;

            lazyAdd[node] = 0;
        }
    }

    void build(const vector<int>& arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            idx[node] = l;
            return;
        }
        int mid = l + (r - l) / 2;
        build(arr, node * 2, l, mid);
        build(arr, node * 2 + 1, mid + 1, r);
        pushUp(node);
    }

    void updateSet(int L, int R, int val) {
        updateSet(1, 0, n - 1, L, R, val);
    }

    void updateSet(int node, int l, int r, int L, int R, int val) {
        if (L <= l && r <= R) {
            isSet[node] = true;
            lazySet[node] = val;
            lazyAdd[node] = 0;
            tree[node] = val;
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (L <= mid) updateSet(node * 2, l, mid, L, R, val);
        if (R > mid) updateSet(node * 2 + 1, mid + 1, r, L, R, val);
        pushUp(node);
    }

    void updateAdd(int L, int R, int val) {
        updateAdd(1, 0, n - 1, L, R, val);
    }

    void updateAdd(int node, int l, int r, int L, int R, int val) {
        if (L <= l && r <= R) {
            if (isSet[node]) {
                lazySet[node] += val;
            } else {
                lazyAdd[node] += val;
            }
            tree[node] += val;
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (L <= mid) updateAdd(node * 2, l, mid, L, R, val);
        if (R > mid) updateAdd(node * 2 + 1, mid + 1, r, L, R, val);
        pushUp(node);
    }

    pair<long long, int> query(int L, int R) {
        return query(1, 0, n - 1, L, R);
    }

    pair<long long, int> query(int node, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            return {tree[node], idx[node]};
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        pair<long long, int> res = {LLONG_MIN, 0};

        if (L <= mid) {
            auto q = query(node * 2, l, mid, L, R);
            if (q.first > res.first) {
                res = q;
            }
        }
        if (R > mid) {
            auto q = query(node * 2 + 1, mid + 1, r, L, R);
            if (q.first > res.first) {
                res = q;
            }
        }
        return res;
    }
};

long long totalMov = 0;

void solve(int L, int R, SegmentTree& s, int mov, int prev) {
    if (L > R) return;
    pair<long long, int> q = s.query(L, R);
    int h = (int)q.first;
    int idx = q.second;

    if (prev <= h) return;

    mov++;
    totalMov = max(totalMov, (long long)mov);

    if (L == R) return;
    solve(L, idx - 1, s, mov, h);
    solve(idx + 1, R, s, mov, h);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    if (!(cin >> t)) return 0;

    vector<int> arr(t);
    for (int i = 0; i < t; i++) {
        cin >> arr[i];
    }

    SegmentTree s(arr);
    solve(0, t - 1, s, 0, INT_MAX);

    cout << totalMov << "\n";
    return 0;
}