#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#define getchar() fgetc_unlocked(stdin)
#define irep(E, F) for (int E = 0; E < (F); E++)
#define iper(E, F) for (int E = (F) - 1; E >= 0; E--)
#define rep(E, F) for (E = 0; E < (F); E++)
#define per(E, F) for (E = (F) - 1; E >= 0; E--)
typedef long long ll;
int min(int a, int b) { return a < b ? a : b; }
int max(int a, int b) { return a > b ? a : b; }

int get_int()
{
    int r = 0, c = getchar(), s = 1;
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    while (c == '-') s *= -1, c = getchar();
    while (c >= '0' && c <= '9') r = r*10 + c - '0', c = getchar();
    return s*r;
}

typedef struct { int x, y; } par;
int cmp(const void *p1, const void *p2)
{
    if (((par*)p1)->x == ((par*)p2)->x) return ((par*)p2)->y - ((par*)p1)->y;
    return ((par*)p1)->x - ((par*)p2)->x;
}
#define LSB(E) ((E)&-(E))
int ft_query(int *t, int x)
{
    int r = 0;
    for (x++; x; x -= LSB(x)) r = max(r, t[x]);
    return r;
}
void ft_update(int *t, int x, int y) { for (x++; x <= t[0]; x += LSB(x)) t[x] = max(t[x], y); }
void ft_init(int *t, int n) { irep(i, n + 1) t[i] = i == 0 ? n : 0; }

int lcs(int *a, int *b, int n, int m)
{
    int i, x, y, d[m + 2];
    ft_init(d, m + 1);
    par g[m];
    rep(i, m) g[i] = (par){b[i], i};
    qsort(g, m, sizeof *g, cmp);
    rep(i, n)
    {
        int r = 0, s = m;
        while (r < s)
        {
            int m = (r + s)/2;
            if (g[m].x < a[i]) r = m + 1;
            else s = m;
        }
        for (; g[r].x == a[i]; r++)
        {
            x = ft_query(d, g[r].y - 1), y = ft_query(d, g[r].y);
            if (y < x + 1) ft_update(d, g[r].y, x + 1);
        }
    }
    return ft_query(d, m);
}

int main()
{
    int i, j, n = get_int(), k = get_int(), m;
    m = n*k;
    int a[m], b[m];
    rep(i, m) a[i] = get_int();
    rep(i, m) b[i] = get_int();
    printf("%d\n", lcs(a, b, m, m));
    return 0;
}
