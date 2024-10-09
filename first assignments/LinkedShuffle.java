import java.util.Scanner;

class Node {
    String val;
    Node next;

    Node(String val)
    {
        this.val = val;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    Node last;
    int size;
    LinkedList()
    {
        head = null;
        last = null;
    }
    void insert_first(Node temp)
    {
        Node node = new Node(temp.val);
        if(head == null)
        {
            head = node;
            last = node;
            size++;
        }
        else
        {
            node.next = head;
            head = node;
            size++;
        }
    }

    void insert_last(Node temp)
    {
        Node node = new Node(temp.val);
        if(head == null)
        {
            head = node;
            last = node;
            size++;
        }
        else
        {
            last.next = node;
            last = node;
            size++;
        }
    }

    public LinkedList doLinkedShuffle() {
        LinkedList half_one = new LinkedList();
        LinkedList half_two = new LinkedList();
        Node slow = head;
        Node fast = head;
        while(fast != null)
        {
            half_one.insert_last(slow);
            slow = slow.next;
            fast = fast.next;
            if(fast != null)
            {
                fast = fast.next;
            }
        }

        while (slow != null)
        {
            half_two.insert_first(slow);
            slow = slow.next;
        }

        LinkedList result = new LinkedList();
        Node pointer_half_one = half_one.head;
        Node pointer_half_two = half_two.head;
        while(pointer_half_one != null && pointer_half_two != null)
        {
            result.insert_last(pointer_half_one);
            result.insert_last(pointer_half_two);
            pointer_half_one = pointer_half_one.next;
            pointer_half_two = pointer_half_two.next;
        }
        while (pointer_half_one != null)
        {
            result.insert_last(pointer_half_one);
            pointer_half_one = pointer_half_one.next;
        }
        while (pointer_half_two != null)
        {
            result.insert_last(pointer_half_two);
            pointer_half_two = pointer_half_two.next;
        }
        return result;
    }
}

public class LinkedShuffle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int m = Integer.parseInt(scanner.nextLine());
        String[] arr = input.split(" -> ");
        LinkedList linkedList = new LinkedList();
        for(int i = 0; i < arr.length; i++)
        {
            Node node = new Node(arr[i]);
            linkedList.insert_last(node);
        }

        LinkedList check = linkedList.doLinkedShuffle();
        int for_mod = 1;
        if(m > n) {
            for (int i = 1; i < n; i++) {
                Node temp1 = linkedList.head;
                Node temp2 = check.head;
                boolean flag = true;
                while (temp1 != null && temp2 != null) {
                    if (!temp1.val.equals(temp2.val)) {
                        flag = false;
                        break;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                if (flag) {
                    for_mod = i;
                    break;
                } else {
                    check = check.doLinkedShuffle();
                }
            }
            LinkedList result = new LinkedList();
            if (m % for_mod != 0) {
                for (int i = 0; i < m % for_mod; i++) {
                    if (i == 0) {
                        result = linkedList.doLinkedShuffle();
                    } else {
                        result = result.doLinkedShuffle();
                    }
                }
            } else {
                result = linkedList;
            }
            Node first = result.head;
            while (first != null) {
                System.out.printf("%s", first.val);
                if (first.next != null) {
                    System.out.printf(" -> ");
                }
                first = first.next;
            }
        }
        else
        {
            LinkedList result = new LinkedList();
            for(int i = 0; i < m; i++)
            {
                if(i == 0)
                {
                    result = linkedList.doLinkedShuffle();
                }
                else
                {
                    result = result.doLinkedShuffle();
                }
            }
            Node first = result.head;
            while (first != null) {
                System.out.printf("%s", first.val);
                if (first.next != null) {
                    System.out.printf(" -> ");
                }
                first = first.next;
            }
        }
    }
}
