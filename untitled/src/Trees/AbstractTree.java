package Trees;

import Nodes.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractTree {
    static final boolean VALIDATE = false;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public Node getRoot() {
        return Root;
    }

    public Node getNil() {
        return Nil;
    }

    protected Node Root;
    protected Node Nil;
    protected int size;



    public AbstractTree()
    {
        this.size = 0;
    }

    Node findNode(int value) {
        Node x =  Root;
        while (x != Nil) {
            if (value < x.value) x = x.left;
            else if (value > x.value) x = x.right;
            else return x;
        }
        return Nil;
    }

    Node findParent(int value) {
        Node x =  Root;
        Node parent = Nil;
        while (x != Nil) {
            parent = x;
            if (value < x.value) x = x.left;
            else if (value > x.value) x = x.right;
            else return null;
        }
        return parent;
    }

    Node findMin(Node root) {
        logger.debug("Finding min in " + (root == Nil ? "Nil" : root.value) + "'s subtree");
        Node x = root;
        while (x.left != Nil) x = x.left;
        logger.debug("Min: " + (x == Nil ? "Nil" : x.value));
        return x;
    }

    void Transplant(Node u, Node v) {
        if (u.p == Nil) Root = v;
        else if (u == u.p.left) u.p.left = v;
        else u.p.right = v;
        v.p = u.p;
        logger.debug("Transplant: " + (v == Nil ? "Nil" : v.value) + " -> " + (u== Nil ? "Nil" : u.value));
    }

    boolean contains(int v) {
        return findNode(v) != Nil;
    }

    int[] inOrder() {
        List<Integer> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node x = Root;
        while (x != Nil || !stack.isEmpty()) {
            while (x != Nil) {
                stack.push(x);
                x = x.left;
            }
            x = stack.pop();
            result.add(x.value);
            x = x.right;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    int height() {
        if (Root == Nil) return 0;
        Queue<Node> q = new LinkedList<Node>();
        q.add(Root);
        int height = 0;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                Node x =  q.poll();
                if (x.left != Nil) q.add(x.left);
                if (x.right != Nil) q.add(x.right);
            }
            height++;
        }
        return height;
    }

    int size() {
        return this.size;
    }

}
