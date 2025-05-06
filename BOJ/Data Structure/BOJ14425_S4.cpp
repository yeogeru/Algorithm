#include <iostream>
#include <map>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Data Structure, Trie
 * @since : 2025-05-03
 */

struct Trie
{
    map<char, Trie *> node;
    bool isEndofWord;

    Trie()
    {
        isEndofWord = false;
    }

    void insert(string &str, int index)
    {
        if (index == str.length())
        {
            this->isEndofWord = true;
            return;
        }

        if (node.count(str[index]) == 0)
        {
            Trie *child = new Trie;
            node[str[index]] = child;
        }

        node[str[index]]->insert(str, index + 1);
    }

    bool search(string &str, int index)
    {
        if (index == str.length())
            return this->isEndofWord;

        if (node.count(str[index]) == 0)
            return false;

        return node[str[index]]->search(str, index + 1);
    }
};

int main()
{
    Trie trie;
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        trie.insert(str, 0);
    }

    int result = 0;

    for (int i = 0; i < m; i++)
    {
        string str;
        cin >> str;
        if (trie.search(str, 0))
        {
            ++result;
        }
    }

    cout << result << endl;

    return 0;
}