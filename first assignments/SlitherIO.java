import java.util.Scanner;
class Node {
    int data;
    Node next;
    Node(int data)
    {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    public static int len;
    LinkedList()
    {
        this.head = null;
    }

    public void addAll(int[] array) {
        len = array.length;
        Node hold = null;
        for(int i = 0; i < array.length; i++)
        {
            Node temp = new Node(array[i]);
            if(head == null)
            {
                this.head = temp;
                hold = head;
            }
            else
            {
                head.next = temp;
                head = head.next;
            }
        }
        head = hold;
    }
}

public class SlitherIO {
    public static int[][] playCircleOfDeath(int r, int c, Node head) {
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = -1;
            }
        }

        Node temp = head;
        int limit = 0;
        boolean rast = true;
        boolean paeen = false;
        boolean chap = false;
        boolean bala = false;
        boolean flag = false;
        int i = 0;
        int j = 0;
        while (limit <= LinkedList.len) {
            if(rast)
            {
                while(j < c)
                {
                    if(temp == null)
                    {
                        flag = true;
                        break;
                    }
                    matrix[i][j] = temp.data;
                    temp = temp.next;
                    j++;
                    if(j == c || matrix[i][j] != -1)
                    {
                        j--;
                        i++;
                        break;
                    }
                }
                rast = false;
                paeen = true;
            }
            else if(paeen)
            {
                while(i < r)
                {
                    if(temp == null)
                    {
                        flag = true;
                        break;
                    }
                    matrix[i][j] = temp.data;
                    temp = temp.next;
                    i++;
                    if(i == r || matrix[i][j] != -1)
                    {
                        i--;
                        j--;
                        break;
                    }
                }
                paeen = false;
                chap = true;
            }

            else if(chap)
            {
                while(j >= 0)
                {
                    if(temp == null)
                    {
                        flag = true;
                        break;
                    }
                    matrix[i][j] = temp.data;
                    temp = temp.next;
                    j--;
                    if(j == -1 || matrix[i][j] != -1)
                    {
                        j++;
                        i--;
                        break;
                    }
                }
                chap = false;
                bala = true;
            }

            else if(bala)
            {
                while(i >= 0)
                {
                    if(temp == null)
                    {
                        flag = true;
                        break;
                    }
                    matrix[i][j] = temp.data;
                    temp = temp.next;
                    i--;
                    if(i == -1 || matrix[i][j] != -1)
                    {
                        i++;
                        j++;
                        break;
                    }
                }
                bala = false;
                rast = true;
            }
            else {}
            limit++;
            if(flag)
            {
                break;
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList linkedList = new LinkedList();
        int r = 0;
        int c = 0;
        String[] row_col = scanner.nextLine().split(" ");
        r = Integer.parseInt(row_col[0]);
        c = Integer.parseInt(row_col[1]);
        String input = scanner.nextLine();

        String[] splited = input.split(" ");
        int[] arr = new int[splited.length];
        for(int i = 0; i < splited.length; i++)
        {
            arr[i] = Integer.parseInt(splited[i]);
        }
        linkedList.addAll(arr);
        int[][] res = SlitherIO.playCircleOfDeath(r, c, linkedList.head);
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                System.out.printf("%d ", res[i][j]);
            }
            System.out.println();
        }
    }
}
