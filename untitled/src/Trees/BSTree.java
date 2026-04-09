package Trees;

import Nodes.Node;

public class BSTree extends AbstractTree implements ITree {
    public BSTree() {
        super();
        super.Nil = new Node(-1);
    }
    public boolean insert(int v) {
        Node parent = findParent(v);
        if (parent == null) return false;
        Node node = new Node(v);
        node.left = Nil;
        node.right = Nil;
        node.p = parent;
        if (parent == Nil) Root = node;
        else if (v < parent.value) parent.left = node;
        else parent.right = node;
        size++;
        return true;
    }
    public boolean delete(int v) {
        Node z = findNode(v);
        if (z == Nil) return false;
        if (z.left == Nil) Transplant(z, z.right);
        else if (z.right == Nil) Transplant(z, z.left);
        else {
            Node y = findMin(z.right);
            if (y.p != z) {
                Transplant(y, y.right);
                z.right.p = y;
                y.right = z.right;
            }
            Transplant(z, y);
            y.left = z.left;
            y.left.p = y;
        }
        size--;
        return true;
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
