package Trees;

import Nodes.Node;
import Validation.Validator;

public class BSTree extends AbstractTree implements ITree {
    public BSTree() {
        super();
        super.Nil = new Node(-1);
    }
    public boolean insert(int v) {
        logger.debug("Attempting to insert " + v + " into BSTree");
        Node parent = findParent(v);
        if (parent == null)
        {
            logger.debug("Value" + v + " already exists. skipping");
            return false;
        }
        Node node = new Node(v);
        node.left = Nil;
        node.right = Nil;
        node.p = parent;
        if (parent == Nil) Root = node;
        else if (v < parent.value) parent.left = node;
        else parent.right = node;
        size++;
        logger.debug("Inserted " + v + " into BSTree under" + (parent == Nil ? "Nil" : parent.value));
        if (VALIDATE) Validator.check(this);
        return true;
    }
    public boolean delete(int v) {
        logger.debug("Attempting to delete " + v + " from BSTree");
        Node z = findNode(v);
        if (z == Nil) {
            logger.debug("Value " + v + " does not exist. Skipping");
            return false;
        }
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
        logger.debug("Deleted " + v + " from BSTree");
        if (VALIDATE) Validator.check(this);
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
