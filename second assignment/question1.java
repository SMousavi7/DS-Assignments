import java.util.Scanner;


class stack
{
    int tedad;
    int[] elements;
    int pointer;
    stack(int n)
    {
        tedad = n;
        elements = new int[tedad];
        pointer = 0;
    }

    void push(int a)
    {
        elements[pointer] = a;
        pointer++;
    }

    int pop()
    {
        if(pointer == -1)
        {
            return 10;
        }
        if(pointer == tedad)
        {
            pointer--;
        }
        int res = elements[pointer];
        pointer--;
        return res;
    }
}

class queue
{
    int tedad;
    int[] students;
    int rear;
    int front;
    int zero;
    int one;
    queue(int n)
    {
        tedad = n;
        students = new int[tedad];
        rear = -1;
        front = 0;
        zero = 0;
        one = 0;
    }

    void enQueue(int a)
    {
        rear++;
        students[rear] = a;
        if(a == 0)
        {
            zero++;
        }
        else
        {
            one++;
        }
    }

    int deQueue()
    {
        int res = students[front];
        if(students.length == 1)
        {
            return res;
        }
        int[] temp = new int[students.length - 1];
        for(int i = 1; i < temp.length; i++)
        {
            temp[i - 1] = students[i];
        }
        temp[temp.length - 1] = students[temp.length];
        students = temp;
        return res;
    }

    void addEnd(int a)
    {
        int[] temp = new int[students.length + 1];
        for(int i = 0; i < students.length; i++)
        {
            temp[i] = students[i];
        }
        temp[students.length] = a;
        students = temp;
    }
}


public class DooghYaNooshabe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] students = scanner.nextLine().split(",");
        String[] beverages = scanner.nextLine().split(",");
        String[] temp = students[0].split("\\[");
        students[0] = temp[1];
        temp = students[students.length - 1].split("\\]");
        students[students.length - 1] = temp[0];
        temp = beverages[0].split("\\[");
        beverages[0] = temp[1];
        temp = beverages[beverages.length - 1].split("\\]");
        beverages[beverages.length - 1] = temp[0];
        stack s = new stack(beverages.length);
        queue q = new queue(students.length);
        for (int i = 0; i < students.length; i++) {
            q.enQueue(Integer.parseInt(students[i]));
        }
        for(int i = beverages.length - 1; i >= 0; i--)
        {
            s.push(Integer.parseInt(beverages[i]));
        }
        boolean flag = false;
        boolean for_changing_test = false;
        int test = -1;
        while(true)
        {
            test = s.pop();
            if(test == 10)
            {
                break;
            }
            if(test == 0 && q.zero != 0)
            {
                q.zero--;
            }
            else if(test == 1 && q.one != 0)
            {
                q.one--;
            }
            else
            {
                break;
            }
        }
        System.out.println(q.zero + q.one);
    }
}
