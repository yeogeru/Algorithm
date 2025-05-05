#include <iostream>
#include <map>
#include <vector>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Data Structure, Trie
 * @since : 2025-05-05
 */

struct Trie
{
    map<string, Trie *> node;

    void insert(vector<string> &vec, int index)
    {
        if (index == vec.size())
            return;

        if (node.count(vec[index]) == 0)
        {
            Trie *child = new Trie;
            node[vec[index]] = child;
        }

        node[vec[index]]->insert(vec, index + 1);
    }

    void print(int depth)
    {
        for (auto &i : node)
        {
            for (int j = 0; j < depth; j++)
                cout << "--";
            cout << i.first << endl;
            i.second->print(depth + 1);
        }
    }


};

int main()
{
    int n;
    cin >> n;

    Trie *trie = new Trie;
    for (int i = 0; i < n; i++)
    {
        int k;
        cin >> k;

        vector<string> vec(k);
        for (int j = 0; j < k; j++)
        {
            cin >> vec[j];
        }
        trie->insert(vec, 0);
    }

    trie->print(0);

    return 0;
}