#include <cstdio>
#include <utility>
#include <stdlib.h>

using namespace std;

typedef long long ll;

typedef struct treap *cartesian;

struct treap {
    ll key, prior, size;
    cartesian lCh, rCh;
    bool isReversed;
};

cartesian make_treap(ll key) {
    cartesian t = (cartesian) malloc(sizeof(struct treap));
    t->isReversed = false;
    t->prior = rand();
    t->key = key;
    t->lCh = nullptr;
    t->rCh = nullptr;
    t->size = 1;
    return t;
}

ll get_size_safe(cartesian t) {
    if (t == nullptr) {
        return 0;
    } else {
        return t->size;
    }
}

void re_eval(cartesian t) {
    if (t == nullptr)
        return;
    t->size = 1 + get_size_safe(t->lCh) + get_size_safe(t->rCh);
}

void propagate(cartesian t) {
    if (t == nullptr)
        return;
    if (t->isReversed) {
        cartesian temp_treap = t->lCh;
        t->lCh = t->rCh;
        t->rCh = temp_treap;
        t->isReversed = false;
        if (t->lCh != nullptr)
            t->lCh->isReversed ^= true;
        if (t->rCh != nullptr)
            t->rCh->isReversed ^= true;
    }
}

cartesian merge(cartesian L, cartesian R) {
    propagate(L);
    propagate(R);
    if (L == nullptr) {
        re_eval(R);
        return R;
    }
    if (R == nullptr) {
        re_eval(L);
        return L;
    }
    if (L->prior > R->prior) {
        L->rCh = merge(L->rCh, R);
        re_eval(L);
        return L;
    } else {
        R->lCh = merge(L, R->lCh);
        re_eval(R);
        return R;
    }
}

pair<cartesian, cartesian> split(cartesian t, ll n) {
    if (t == nullptr) {
        return make_pair(nullptr, nullptr);
    }
    propagate(t);
    if (get_size_safe(t->lCh) >= n) {
        pair<cartesian, cartesian> p = split(t->lCh, n);
        t->lCh = p.second;
        re_eval(t);
        return make_pair(p.first, t);
    } else {
        pair<cartesian, cartesian> p = split(t->rCh, n - 1 - get_size_safe(t->lCh));
        t->rCh = p.first;
        re_eval(t);
        return make_pair(t, p.second);
    }
}

cartesian reverse(cartesian t, ll l, ll r) {
    pair<cartesian, cartesian> p1 = split(t, l);
    pair<cartesian, cartesian> p2 = split(p1.second, r - l);
    p2.first->isReversed ^= true;
    return merge(merge(p1.first, p2.first), p2.second);
}

cartesian insert(cartesian t, ll n, ll pos) {
    pair<cartesian, cartesian> p = split(t, pos);
    cartesian tmp = make_treap(n);
    tmp = merge(p.first, tmp);
    return merge(tmp, p.second);
}

void printTree(cartesian t) {
    if (t != nullptr) {
        propagate(t);
        printTree(t->lCh);
        printf("%lld ", t->key);
        printTree(t->rCh);
    }
}

int main() {
    ll n, m;
    cartesian root = nullptr;
    scanf("%lld%lld", &n, &m);
    for (ll i = 1; i <= n; i++) {
        root = insert(root, i, i);
    }
    ll l, r;
    for (ll i = 0; i < m; i++) {
        scanf("%lld%lld", &l, &r);
        root = reverse(root, l - 1, r);
    }
    printTree(root);
    return 0;
}