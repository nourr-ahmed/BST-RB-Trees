package Trees;

import Nodes.Node;
import Nodes.RBNode;

public class RBTree extends AbstractTree implements ITree {
    public RBTree() {
        super();
        super.Nil = new RBNode(-1, RBNode.Color.BLACK);
    }

    public boolean insert(int v) {
        Node parent = findParent(v);
        if (parent == null) return false;
        Node node = new RBNode(v, RBNode.Color.RED);
        node.left = Nil;
        node.right = Nil;
        node.p = parent;
        if (parent == Nil) Root = node;
        else if (v < parent.value) parent.left = node;
        else parent.right = node;
        RBInsertFixup(asRB(node));
        return true;
    }

    private void RBInsertFixup(Node z) {
        while (asRB(z.p).color ==  RBNode.Color.RED) {
            if (z.p == z.p.p.left) {
                Node u = z.p.p.right;
                if (asRB(u).color == RBNode.Color.RED) {
                    asRB(z.p).color = RBNode.Color.BLACK;
                    asRB(u).color = RBNode.Color.BLACK;
                    asRB(z.p.p).color = RBNode.Color.RED;
                    z = z.p.p;
                }
                else if (z == z.p.right) {
                    z = z.p;
                    leftRotate(z);
                }
                asRB(z.p).color = RBNode.Color.BLACK;
                asRB(z.p.p).color = RBNode.Color.RED;
                rightRotate(z.p.p);
            }
            else {
                Node u = z.p.p.left;
                if (asRB(u).color == RBNode.Color.RED) {
                    asRB(z.p).color = RBNode.Color.BLACK;
                    asRB(u).color = RBNode.Color.BLACK;
                    asRB(z.p.p).color = RBNode.Color.RED;
                    z = z.p.p;
                }
                else if (z == z.p.left) {
                    z = z.p;
                    rightRotate(z);
                }
                asRB(z.p).color = RBNode.Color.BLACK;
                asRB(z.p.p).color = RBNode.Color.RED;
                leftRotate(z.p.p);
            }
        }
        asRB(Root).color = RBNode.Color.BLACK;
    }
    private RBNode asRB(Node node) {
        return (RBNode) node;
    }
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.p = x;
        y.p  = x.p;
        if (x.p == Nil) Root = y;
        else if (x == x.p.left) x.p.left = y;
        else x.p.right = y;
        y.left = x;
        x.p = y;
    }
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != null) x.right.p = y;
        x.p  = y.p;
        if (y.p == Nil) Root = x;
        else if (y == y.p.right) y.p.right = x;
        else y.p.left = x;
        x.right = y;
        y.p = x;
    }
    public boolean delete(int v) {
        Node z = findNode(v);
        if (z == Nil) return false;

        return true;
    }
    private void RBDeleteFixup (Node z) {

    }
    @Override
    public boolean contains(int v) {return super.contains(v);}
    @Override
    public int[] inOrder() {return super.inOrder();}
    @Override
    public int height()  {return super.height();}
    @Override
    public int size()  {return super.size();}
}
