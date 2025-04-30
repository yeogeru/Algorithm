#include <iostream>
#include <stack>
using namespace std;

/**
 * @author : Yeogeru
 * @description : Data Structure, Stack
 * @since : 2025-04-30
 */

int main()
{
    stack<int> stack;
    int t;
    cin >> t;
    for (int i = 0; i < t; i++)
    {
        string type;
        int n;
        cin >> type;
        if (type == "push")
        {
            cin >> n;
            stack.push(n);
        }
        else if (type == "pop")
        {
            if (!stack.empty())
            {
                cout << stack.top() << endl;
                stack.pop();
            }
            else {
                cout << -1 << endl;
            }
        }
        else if (type == "size")
        {
            cout << stack.size() << endl;
        }
        else if (type == "empty")
        {
            cout << (stack.empty() ? 1 : 0) << endl;
        }
        else if (type == "top")
        {
            cout << (!stack.empty() ? stack.top() : -1) << endl;
        }
    }
    return 0;
}