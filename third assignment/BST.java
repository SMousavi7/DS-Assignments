import java.util.Scanner;

class Node
{
    int value;
    Node left, right;
    Node(int value)
    {
        this.value = value;
        left = null;
        right = null;
    }
}

class BST
{
    Node head;
    static int count = 0; // for a specific method
    BST()
    {
        head = null;
    }

    void insert_check(int val)
    {
        head = insert(head, val);
    }
    Node insert(Node head, int val)
    {
        if(head == null)
        {
            head = new Node(val);
            return head;
        }
        if(val >= head.value)
        {
            head.right = insert(head.right, val);
        }
        else
        {
            head.left = insert(head.left, val);
        }
        return head;
    }
    void erase_check(int val)
    {
        getCount(val);
        for (int i = 0; i < count; i++)
        {
            head = erase(head, val);
        }
    }

    Node erase(Node head, int val)
    {
        if(head == null)
        {
            return head;
        }
        if(val > head.value)
        {
            head.right = erase(head.right, val);
        }
        else if(val < head.value)
        {
            head.left = erase(head.left, val);
        }
        else
        {
            if(head.right == null)
            {
                return head.left;
            }
            else if (head.left == null)
            {
                return head.right;
            }
            head.value = min(head);
            head.right = erase(head.right, head.value);
        }
        return head;
    }

    void in_order(Node head)
    {
        if(this.head == null)
        {
            System.out.print("empty");
        }
        else {
            if (head != null) {
                in_order(head.left);
                System.out.print(head.value + " ");
                in_order(head.right);
            }
        }
    }
    void pre_order(Node head)
    {
        if(this.head == null){
            System.out.print("empty");
        }
        else {
            if (head != null) {
                System.out.print(head.value + " ");
                pre_order(head.left);
                pre_order(head.right);
            }
        }
    }
    void post_order(Node head)
    {
        if(this.head == null){
            System.out.print("empty");
        }
        else {
            if (head != null) {
                post_order(head.left);
                post_order(head.right);
                System.out.print(head.value + " ");
            }
        }
    }

    int min(Node head)
    {
        int min = head.value;
        while(head != null)
        {
            min = head.value;
            head = head.left;
        }
        return min;
    }

    void getCount(int val)
    {
        count = 0;
        counter(head, val);
    }

    void counter(Node head, int val)
    {
        if(head != null){
            if(val < head.value)
            {
                counter(head.left, val);
            }
            else
            {
                if(val == head.value)
                {
                    count++;
                    counter(head.right, val);
                }
                else
                {
                    counter(head.right, val);
                }
            }
        }
    }


}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();
        while (scanner.hasNext())
        {
            String[] split = scanner.nextLine().split(" ");
            if(split.length == 2)
            {
                if(split[0].equals("insert"))
                {
                    bst.insert_check(Integer.parseInt(split[1]));
                }
                else
                {
                    bst.erase_check(Integer.parseInt(split[1]));
                }
            }
            else
            {
                if(split[0].equals("print_inorder"))
                {
                    bst.in_order(bst.head);
                }
                else if(split[0].equals("print_preorder"))
                {
                    bst.pre_order(bst.head);
                }
                else
                {
                    bst.post_order(bst.head);
                }
                System.out.println();
            }
        }
    }
}
