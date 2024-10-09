import java.util.Scanner;

class outNode{
    String name;
    outNode left;
    outNode right;
    inNode in_node;
    int bedehkar_ast;
    int talabkar_ast;
    long balanced;
    outNode(String name)
    {
        this.name = name;
        left = null;
        right = null;
        in_node = new inNode(name);
        bedehkar_ast = 0;
        talabkar_ast = 0;
        balanced = 0;
    }

}


class outBST{
    outNode head;
    boolean flag;

    outBST()
    {
        head = null;
        flag = false;
    }

    outNode add(outNode head, String name)
    {
        if(head == null && !flag)
        {
            this.head = new outNode(name);
            flag = true;
            return head;
        }
        if(head == null && flag)
        {
            return new outNode(name);
        }
        if(head.name.compareTo(name) > 0)
        {
            head.left = add(head.left, name);
        }
        else if(head.name.compareTo(name) < 0)
        {
            head.right = add(head.right, name);
        }
        return head;
    }

    outNode search(outNode head, String name)
    {
        if(head == null || head.name.equals(name))
        {
            return head;
        }
        if(head.name.compareTo(name) < 0)
        {
            return search(head.right, name);
        }
        return search(head.left, name);
    }

    void print(outNode head)
    {
        if(head != null)
        {
            print(head.left);
            System.out.printf("%s ", head.name);
            print(head.right);
        }
    }
}


class inNode
{
    String name;
    long fund;
    inNode left;
    inNode right;

    inBST in_bst;

    inNode(String name)
    {
        this.name = name;
        fund = 0;
        left = null;
        right = null;
        in_bst = new inBST();
        in_bst.head = this;
    }
}

class inBST{
    inNode head;
    boolean flag;

    inBST()
    {
        head = null;
        flag = false;
    }

    inNode add(inNode head, String name)
    {
        if(head == null && !flag)
        {
            this.head = new inNode(name);
            flag = true;
            return head;
        }
        if(head == null && flag)
        {
            return new inNode(name);
        }
        if(head.name.compareTo(name) > 0)
        {
            head.left = add(head.left, name);
        }
        else if(head.name.compareTo(name) < 0)
        {
            head.right = add(head.right, name);
        }
        return head;
    }

    inNode search(inNode head, String name)
    {
        if(head == null || head.name.equals(name))
        {
            return head;
        }
        if(head.name.compareTo(name) < 0)
        {
            return search(head.right, name);
        }
        return search(head.left, name);
    }

    int tarakonesh(inNode head, String name, long amount)
    {
        if(head == null || head.name.equals(name))
        {
            if(head == null)
                return -1;

            boolean flag = false;
            boolean flagT = false;
            if(head.fund >= 0)
            {
                flag = true;
                if(head.fund == 0)
                {
                    flagT = true;
                }
            }

            head.fund = head.fund + amount;
            if(head.fund > 0 && flag && flagT)
            {
                return 1;
            }
            else if(flag && !flagT && head.fund == 0)
            {
                return 2;
            }
            else if(flag && !flagT && head.fund < 0)
            {
                return 3;
            }
            else if(!flag && !flagT && head.fund == 0)
            {
                return 4;
            }
            else if(!flag && !flagT && head.fund > 0)
            {
                return 5;
            }
            else if(flag && flagT && head.fund < 0)
            {
                return 6;
            }
            return 0;
        }
        if(head.name.compareTo(name) < 0)
        {
            return tarakonesh(head.right, name, amount);
        }
        return tarakonesh(head.left, name, amount);
    }
    void print(inNode head)
    {
        if(head != null)
        {
            print(head.left);
            System.out.printf("%s ", head.name);
            print(head.right);
        }
    }

}

class balancedNode{
    String name;
    long balanced;
    balancedNode right;
    balancedNode left;

    balancedNode(String name , long balanced)
    {
        this.name = name;
        this.balanced = balanced;
        right = null;
        left = null;
    }
}

class balancedBST{
    balancedNode head;
    boolean flag;
    balancedBST()
    {
        head = null;
        flag = false;
    }

    void add_with_change(String name, long balanced)
    {
        if (head == null) {
            head = new balancedNode(name, balanced);
        } else {
            head = add_without_change(head, name, balanced);
        }
    }

    balancedNode add_without_change(balancedNode head, String name, long balanced)
    {
        if(head == null)
        {
            head = new balancedNode(name, balanced);
            return head;
        }

        if(head.balanced > balanced)
        {
            head.left = add_without_change(head.left, name, balanced);
        }
        else if(head.balanced < balanced)
        {
            head.right = add_without_change(head.right, name, balanced);
        }
        else
        {
            if(head.name.compareTo(name) < 0)
            {
                head.right = add_without_change(head.right, name, balanced);
            }
            else if(head.name.compareTo(name) > 0)
            {
                head.left = add_without_change(head.left, name, balanced);
            }
            else
            {
                return head;
            }
        }

        return head;
    }


    balancedNode delete(balancedNode head, String name, long balanced) {
        if (head == null) {
            return head;
        }

        if (head.balanced > balanced) {
            head.left = delete(head.left, name, balanced);
        }
        else if (head.balanced < balanced) {
            head.right = delete(head.right, name, balanced);
        }
        else
        {
            int aya_mosavi_ast_ya_kheir = head.name.compareTo(name);
            if(aya_mosavi_ast_ya_kheir < 0)
            {
                head.right = delete(head.right, name, balanced);
            }
            else if(aya_mosavi_ast_ya_kheir > 0)
            {
                head.left = delete(head.left, name, balanced);
            }
            else {
                if (head.left == null) {
                    balancedNode temp = head.right;
                    return temp;
                } else if (head.right == null) {
                    balancedNode temp = head.left;
                    return temp;
                } else {

                    balancedNode succParent = head;
                    balancedNode succ = head.right;
                    while (succ.left != null) {
                        succParent = succ;
                        succ = succ.left;
                    }
                    if (succParent != head)
                        succParent.left = succ.right;
                    else
                        succParent.right = succ.right;


                    head.name = succ.name;
                    head.balanced = succ.balanced;
                }
            }
            return head;
        }
        return head;
    }

    balancedNode search(balancedNode head, String name,long balanced)
    {
        if(head == null)
        {
            return head;
        }
        if(head.balanced < balanced)
        {
            return search(head.right, name, balanced);
        }
        else if(head.balanced == balanced)
        {
            if(head.name.compareTo(name) < 0)
            {
                return search(head.right, name, balanced);
            }
            else if(head.name.compareTo(name) > 0)
            {
                return search(head.left, name, balanced);
            }
            else
            {
                return head;
            }
        }
        return search(head.left, name, balanced);
    }

    balancedNode getMin(balancedNode head)
    {
        if(head.left == null)
        {
            return head;
        }
        return getMin(head.left);
    }

    balancedNode getMax(balancedNode head)
    {
        if(head.right == null)
        {
            return head;
        }
        return getMax(head.right);
    }

    balancedNode Max_less_lex(balancedNode head, long balanced)
    {
        if(head == null)
        {
            return head;
        }

        if(head.balanced == balanced)
        {
            balancedNode temp = Max_less_lex(head.left, balanced);
            if(temp == null)
            {
                return head;
            }
            else {
                return temp;
            }
        }
        else
        {
            return Max_less_lex(head.right, balanced);
        }
    }

    void print(balancedNode head)
    {
        if (head == null) {
            return;
        }
        print(head.left);
        System.out.print(head.name + ": " + head.balanced + ",\n");
        print(head.right);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        outBST out_bst = new outBST();
        balancedBST balanced_bst = new balancedBST();

        int n = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < n; i++)
        {
            String[] split = scanner.nextLine().split(" ");

            if(split[0].equals("1"))
            {
                String ye_esm_ashgahle_lanati[] = split[3].split("\\.");
                long money = Long.parseLong(ye_esm_ashgahle_lanati[0]) * 100 + Long.parseLong(ye_esm_ashgahle_lanati[1]);
                outNode check = out_bst.search(out_bst.head, split[1]);
                if(check == null)
                {
                    out_bst.add(out_bst.head, split[1]);
                }

                check = out_bst.search(out_bst.head, split[2]);
                if(check == null)
                {
                    out_bst.add(out_bst.head, split[2]);
                }

                outNode first = out_bst.search(out_bst.head, split[1]);
                outNode second = out_bst.search(out_bst.head, split[2]);
                inNode temp = null;

                temp = first.in_node.in_bst.search(first.in_node.in_bst.head, split[2]);
                if(temp == null)
                {
                    first.in_node.in_bst.add(first.in_node.in_bst.head, split[2]);
                }

                temp = second.in_node.in_bst.search(second.in_node.in_bst.head, split[1]);
                if(temp == null)
                {
                    second.in_node.in_bst.add(second.in_node.in_bst.head, split[1]);
                }

                int temp1 = first.in_node.in_bst.tarakonesh(first.in_node.in_bst.head, split[2], money);
                int temp2 = second.in_node.in_bst.tarakonesh(second.in_node.in_bst.head, split[1], -money);
                switch (temp1)
                {
                    case 1:
                        first.talabkar_ast += 1;
                        break;
                    case 2:
                        first.talabkar_ast -= 1;
                        break;
                    case 3:
                        first.talabkar_ast -= 1;
                        first.bedehkar_ast += 1;
                        break;
                    case 4:
                        first.bedehkar_ast -= 1;
                        break;
                    case 5:
                        first.bedehkar_ast -= 1;
                        first.talabkar_ast += 1;
                        break;
                    case 6:
                        first.bedehkar_ast += 1;
                        break;
                }

                switch (temp2) {
                    case 1:
                        second.talabkar_ast += 1;
                        break;
                    case 2:
                        second.talabkar_ast -= 1;
                        break;
                    case 3:
                        second.talabkar_ast -= 1;
                        second.bedehkar_ast += 1;
                        break;
                    case 4:
                        second.bedehkar_ast -= 1;
                        break;
                    case 5:
                        second.bedehkar_ast -= 1;
                        second.talabkar_ast += 1;
                        break;
                    case 6:
                        second.bedehkar_ast += 1;
                        break;
                }
                balanced_bst.head = balanced_bst.delete(balanced_bst.head, first.name, first.balanced);
                balanced_bst.head = balanced_bst.delete(balanced_bst.head, second.name, second.balanced);
                first.balanced -= money;
                second.balanced += money;
                balanced_bst.add_with_change(first.name, first.balanced);
                balanced_bst.add_with_change(second.name, second.balanced);

            }
            else if(split[0].equals("2"))
            {
                try {
                    balancedNode temp = balanced_bst.Max_less_lex(balanced_bst.head, balanced_bst.getMax(balanced_bst.head).balanced);
                    if (temp.balanced <= 0) {
                        System.out.println("-1");
                    } else {
                        System.out.println(temp.name);
                    }
                }catch (Exception e)
                {
                    System.out.println("-1");
                }
            }

            else if(split[0].equals("3"))
            {
                try {
                    balancedNode temp = balanced_bst.getMin(balanced_bst.head);
                    if (temp.balanced >= 0) {
                        System.out.println("-1");
                    } else {
                        System.out.println(temp.name);
                    }
                }catch (Exception e)
                {
                    System.out.println("-1");
                }
            }

            else if(split[0].equals("4"))
            {
                outNode check = out_bst.search(out_bst.head, split[1]);
                System.out.println(check.bedehkar_ast);

            }
            else if (split[0].equals("5"))
            {
                outNode check = out_bst.search(out_bst.head, split[1]);
                System.out.println(check.talabkar_ast);
            }
            else if(split[0].equals("6"))
            {
                outNode first = out_bst.search(out_bst.head, split[1]);
                outNode second = out_bst.search(out_bst.head, split[2]);
                if(first == null || second == null)
                {
                    System.out.println("0.00");
                }
                inNode ans = first.in_node.in_bst.search(first.in_node.in_bst.head, split[2]);
                if(ans == null)
                {
                    System.out.println("0.00");
                }
                else
                {
                    long dollor = -ans.fund / 100;
                    int cent = (int) (-ans.fund % 100);
                    boolean minus = false;
                    if (dollor == 0 && cent < 0) {
                        minus = true;
                    }
                    if(cent < 0)
                    {
                        cent = -cent;
                    }
                    System.out.println((minus ? "-" : "") + dollor + "." + (cent < 10 ? "0" + cent : cent));
                }
            }
        }

    }
}

