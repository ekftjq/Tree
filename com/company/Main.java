package com.company;

public class Main {

    public static void main(String[] args) {


        Node<String> advancedTree = new Node<>("a");
        Node<String> child1 = new Node<>("b");
        Node<String> child2 = new Node<>("c");
        Node<String> child3 = new Node<>("d");
        Node<String> child4 = new Node<>("e");
        Node<String> child5 = new Node<>("f");
        Node<String> child6 = new Node<>("g");
        Node<String> child7 = new Node<>("h");
        Node<String> child8 = new Node<>("i");
        Node<String> child9 = new Node<>("j");
        Node<String> child10 = new Node<>("k");
        Node<String> child11 = new Node<>("l");
        Node<String> child12 = new Node<>("m");
        Node<String> child13 = new Node<>("n");
        Node<String> child14 = new Node<>("o");

        advancedTree.addChild(child1);
        advancedTree.addChild(child2);
        advancedTree.addChild(child3);

        child1.addChild(child4);
        child1.addChild(child5);

        child2.addChild(child6);
        child2.addChild(child7);

        child3.addChild(child8);
        child3.addChild(child9);

        child4.addChild(child10);
        child4.addChild(child11);

        child5.addChild(child12);

        child6.addChild(child13);

        child7.addChild(child14);

        String a = printTree(advancedTree);
        System.out.println("The string created from the advanced Tree is : " +a);

        Node<String> b = StringToTree(a);
        System.out.println("Printing the tree that was converted from a string : " + printTree(b));



    }


    public static String printTree(Node<String> tree) { //Depth first search
        StringBuilder sb = new StringBuilder();
        sb.append(tree.getData());

        if (tree.hasChildren()) {
            for (Node child : tree.getChildren()) {
                sb.append("(");
                sb.append(printTree(child));
                sb.append(")");
            }
        }

        return sb.toString();
    }

    public static Node<String> StringToTree(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
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
            //tring back = splitString(css);
            //System.out.println("the front is :" +data);
            //System.out.println("the back is :" + css);
            Node<String> child = new Node(data);
            root.addChild(child);
            createTree(child, css);
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
        System.out.println("split string is :" +s);
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
