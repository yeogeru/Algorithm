#include <iostream>
#include <unordered_map>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Data Structure, Trie
 * @since : 2025-05-03
 */

struct Node
{
    unordered_map<char, Node *> children;
    bool isEndOfWord;

    Node()
    {
        isEndOfWord = false;
    }
};

struct Trie
{
    Node *root;

    Trie()
    {
        root = new Node();
        
    }

    void insert(const string &str)
    {
        Node *node = root;

        for (char c : str)
        {
            if (node->children.count(c) == 0)
            {
                node->children[c] = new Node();
            }
            node = node->children[c];
        }
        node->isEndOfWord = true;
    }

    bool search(const string &str)
    {
        Node *node = root;
        for (char c : str)
        {
            if (node->children.count(c) == 0)
                return false;
            node = node->children[c];
        }
        return node->isEndOfWord;
    }

    ~Trie()
    {
        erase(root);
    }

    void erase(Node *node)
    {
        for (auto &pair : node->children)
        {
            erase(pair.second);
        }
        delete node;
    }
};

int main()
{
    Trie trie;
    int n, m;
    cin >> n >> m;
    for(int i = 0 ; i < n ; i++) {
        string str;
        cin >> str;
        trie.insert(str);
    }

    int result = 0;

    for(int i = 0 ; i < m ; i++) {
        string str;
        cin >> str;
        if(trie.search(str)) ++result;
    }

    cout << result << endl;

    return 0;
}