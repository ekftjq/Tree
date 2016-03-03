package com.company;

public class Main {

    public static void main(String[] args) {

        Node<String> tree = new Node<>("root");
        Node<String> tree2 = new Node<>("0");

        //System.out.println(tree.getParent()); //returns null
        //System.out.println(tree.getChildren()); //returns []
        //System.out.println(tree.getData());

        tree.addChild("a");
        tree.addChild("b");
        tree.addChild("c");

        /*
            root
          a  b  c

         */

        //System.out.println(tree.getChildren().get(0).getData()); //data of the first child
        //System.out.println(tree.getChildren().size());

        tree.getChildren().get(0).addChild("d");
        tree.getChildren().get(0).addChild("e");
        tree.getChildren().get(1).addChild("f");
        tree.getChildren().get(2).addChild("g");


        /*
                     root
          a           b        c
        d  e          f        g

        root,

         */

        tree2.addChild("1");
        tree2.addChild("4");
        tree2.getChildren().get(0).addChild("2");
        tree2.getChildren().get(0).addChild("3");

        /*
                   0
               1       4
             2   3
                           */

        //System.out.println(printTree(tree2));
        String r = printTree(tree);
        System.out.println(r);

        Node<String> tree3 = StringToTree(r);
        String a = printTree(tree3);
        System.out.println(a);
        //System.out.println(tree3.getChildren().get(0).getData());






	// write your code here
    }


    public static String printTree(Node<String> tree) { //Depth first search
        String result = "";

        if(tree.isLeaf()) {
            return tree.getData();
        }

        result = tree.getData();

        for(int i = 0; i< tree.getChildren().size(); i++) {
            //result = result + printTree(tree.getChildren().get(i));
            //System.out.println(printTree(tree.getChildren().get(i)));
            result = result + "(" + printTree(tree.getChildren().get(i)) + ")";
        }

        return result;

    }

    public static Node<String> StringToTree(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder rest = new StringBuilder();
        int i = 0;
        int len = charArray.length;
        Node<String> root = new Node<>("");

        while(charArray[i] != '(') {
            sb.append(charArray[i]);
            i++;
        }
        root.setData(sb.toString());
        createTree(root, s);
        //System.out.println(i);
        //System.out.println(root.getData());
        return root;
    }

    private static void createTree(Node<String> root, String s) {
        char[] charArray = s.toCharArray();
        int i = 0;
        int len = charArray.length;
        int num1 = 0;
        int num2 = 0;
        int leaf = 0;
        StringBuilder sb = new StringBuilder();
        //System.out.println(charArray[0]);
        //System.out.println(charArray[1]);
        //System.out.println(charArray[2]);
        //System.out.println(s);

        for(int j = 0; j < charArray.length; j++) {
            if(charArray[j] == '(') {
                leaf++;
                //System.out.println(charArray[j]);
            }
        }

        //System.out.println(leaf);

        if(leaf == 1) {
            //System.out.println("added leaf");
            while(i < len) {
                if(charArray[i] != '(' && charArray[i] != ')') {
                    sb.append(charArray[i]);
                    //System.out.println(charArray[i]);
                }
                i++;
            }
            root.addChild(sb.toString());
        }

        while (i < len) {
            if (charArray[i] == '(' && num1 == 0) {
                sb.append(charArray[i]);
            }
            if (num1 != num2) {
                sb.append(charArray[i]);
            }
            if (charArray[i] == '(') {
                num1++;
            }
            if (charArray[i] == ')') {
                num2++;
            }
            if (num1 == num2 && num1 != 0) {
                String data = sb.toString();
                //System.out.println(data);
                runtwo(root, data);
                num1 = 0;
                num2 = 0;
                sb = new StringBuilder();
            }
            i++;
        }
    }

    private static void runtwo(Node root, String s) {
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        if (twoBrackets(s)) {
            int k = 0;
            int[] b = new int[2];
            int count = 0;
            while(k < len && count != 2) {
                if(charArray[k] == '(') {
                    b[count] = k;
                    count++;
                }
                k++;
            }
            StringBuilder cs = new StringBuilder();
            StringBuilder ns = new StringBuilder();
            int start = b[0];
            int end = b[1];
            while(start < end-1) {
                cs.append(charArray[start+1]);
                //System.out.println("asdf");
                start++;
            }
            while(end < len) {
                ns.append(charArray[end]);
                end++;
            }
            String data = cs.toString();
            String css = ns.toString();
            String back = splitString(css);
            //System.out.println(data);
            //System.out.println(back);
            Node<String> child = new Node(data);
            root.addChild(child);
            createTree(child, back);
            //createTree(child,"(2)");
        }
        else
            createTree(root, s);
    }

    private static boolean twoBrackets(String s) {
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        int b = 0;
        int c = 0;
        for(int i = 0; i<len; i++) {
            if(charArray[i] == '(') {
                b++;
            }
            if(charArray[i] == ')') {
                c++;
            }
            if(b == 2 && c == 0) {
                return true;
            }
        }
        return false;
    }

    private static String splitString(String css) {
        String s = "(" + css;
        //System.out.println(s);
        char[] charArray = s.toCharArray();
        int i = 0;
        int len = charArray.length;
        int num1 = 0;
        int num2 = 0;
        StringBuilder sb = new StringBuilder();

        while(i < len) {
            if (num1 != num2 && num2 != 2) {
                sb.append(charArray[i]);
            }
            if (charArray[i] == '(') {
                num1++;
            }
            if (charArray[i] == ')') {
                num2++;
            }
            if(num1 == num2) {
                break;
            }
            i++;
        }
        return sb.toString();
    }

}
