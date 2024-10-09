#include <iostream>
#include <sstream>

class Node {
public:
    int value;
    Node* left, *right;

    Node(int value) : value(value), left(nullptr), right(nullptr) {}
};

class BST {
public:
    Node* head;
    static int count;

    BST() : head(nullptr) {}

    void insertCheck(int val) {
        head = insert(head, val);
    }

    Node* insert(Node* head, int val) {
        if (head == nullptr) {
            head = new Node(val);
            return head;
        }
        if (val >= head->value) {
            head->right = insert(head->right, val);
        } else {
            head->left = insert(head->left, val);
        }
        return head;
    }

    void eraseCheck(int val) {
        getCount(val);
        for (int i = 0; i < count; i++) {
            head = erase(head, val);
        }
    }

    Node* erase(Node* head, int val) {
        if (head == nullptr) {
            return head;
        }
        if (val > head->value) {
            head->right = erase(head->right, val);
        } else if (val < head->value) {
            head->left = erase(head->left, val);
        } else {
            if (head->right == nullptr) {
                return head->left;
            } else if (head->left == nullptr) {
                return head->right;
            }
            head->value = findMin(head->right);
            head->right = erase(head->right, head->value);
        }
        return head;
    }

    void inOrder(Node* head) {
        if (this->head == nullptr) {
            std::cout << "empty";
        } else {
            if (head != nullptr) {
                inOrder(head->left);
                std::cout << head->value << " ";
                inOrder(head->right);
            }
        }
    }

    void preOrder(Node* head) {
        if (this->head == nullptr) {
            std::cout << "empty";
        } else {
            if (head != nullptr) {
                std::cout << head->value << " ";
                preOrder(head->left);
                preOrder(head->right);
            }
        }
    }

    void postOrder(Node* head) {
        if (this->head == nullptr) {
            std::cout << "empty";
        } else {
            if (head != nullptr) {
                postOrder(head->left);
                postOrder(head->right);
                std::cout << head->value << " ";
            }
        }
    }

    int findMin(Node* head) {
        int min = head->value;
        while (head != nullptr) {
            min = head->value;
            head = head->left;
        }
        return min;
    }

    void getCount(int val) {
        count = 0;
        counter(head, val);
    }

    void counter(Node* head, int val) {
        if (head != nullptr) {
            if (val < head->value) {
                counter(head->left, val);
            } else {
                if (val == head->value) {
                    count++;
                    counter(head->right, val);
                } else {
                    counter(head->right, val);
                }
            }
        }
    }
};

int BST::count = 0;

int main() {
    std::string line;
    BST bst;

    while (std::getline(std::cin, line)) {
        std::istringstream iss(line);
        std::string command;
        iss >> command;

        if (command == "insert") {
            int val;
            iss >> val;
            bst.insertCheck(val);
        } else if (command == "erase") {
            int val;
            iss >> val;
            bst.eraseCheck(val);
        } else if (command == "print_inorder") {
            bst.inOrder(bst.head);
            std::cout << std::endl;
        } else if (command == "print_preorder") {
            bst.preOrder(bst.head);
            std::cout << std::endl;
        } else if (command == "print_postorder") {
            bst.postOrder(bst.head);
            std::cout << std::endl;
        }
    }

    return 0;
}
