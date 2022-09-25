
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
typedef vector<vector<int>> vvi;

string in, out1, out2;
vector<int> deg2;

bool isDeg2(int j) {
  for (int i : deg2) {
    if (i == j) {
      return true;
    }
  }
  return false;
}

class code {
public:
  void run(string s) {
    int n = s.length();
    int m = 2;
    while ((int)(ceil(log(n + m + 1) / log(2))) != m) {
      m++;
    }
    vvi a(m + 2);
    int tmp = 0;
    for (int i = 0; i < m + 2; i++) {
      a[i].resize(n + m + 1);
      if (i == 0) {
        for (int j = 1; j <= n + m; j++) {
          a[i][j] = j;
        }
        continue;
      }
      if (i == 1) {
        for (int j = 1; j <= n + m; j++) {
          if (!isDeg2(j)) {
            a[i][j] = s[tmp++] - 48;
          }
        }
        continue;
      }
      for (int j = 1; j <= n + m; j++) {
        a[i][j] = a[0][j] % 2;
        a[0][j] /= 2;
      }
    }
    for (int j = 0; j < m; j++) {
      int ans = 0;
      for (int i = 1; i <= n + m; i++) {
        ans = (ans + a[1][i] * a[j + 2][i]) % 2;
      }
      a[1][pow(2, j)] = ans;
    }
    for (int i = 1; i <= n + m; i++) {
      cout << a[1][i];
    }
  }
};

class decode {
public:
  void run(string s) {
    int n = s.length();
    int m = (int)(ceil(log(n + 1) / log(2)));
    n -= m;
    vvi a(m + 2);
    for (int i = 0; i < m + 2; i++) {
      a[i].resize(n + m + 1);
      if (i == 0) {
        for (int j = 1; j <= n + m; j++) {
          a[i][j] = j;
        }
        continue;
      }
      if (i == 1) {
        for (int j = 1; j <= n + m; j++) {
          a[i][j] = s[j - 1] - 48;
        }
        continue;
      }
      for (int j = 1; j <= n + m; j++) {
        a[i][j] = a[0][j] % 2;
        a[0][j] /= 2;
      }
    }
    int q = 0, tmp = 1;
    for (int j = 0; j < m; j++) {
      int ans = 0;
      for (int i = 1; i <= n + m; i++) {
        ans = (ans + a[1][i] * a[j + 2][i]) % 2;
      }
      q += tmp * ans;
      tmp *= 2;
    }
    for (int j = 1; j <= n + m; j++) {
      if (!isDeg2(j)) {
        if (j == q) {
          cout << (char)(97 - s[j - 1]);
        }
        else {
          cout << s[j - 1];
        }
      }
    }
  }
};

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(0);
  cout.tie(0);

  deg2.push_back(1);
  for (int i = 0; i < 17; i++) {
    deg2.push_back(deg2[i] * 2);
  }
  int t;
  string s;
  cin >> t >> s;
  if (t == 1) {
    code().run(s);
  }
  else {
    decode().run(s);
  }
  cout << '\n';

  return 0;
}
