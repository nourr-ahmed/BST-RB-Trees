package Trees;

import javax.swing.tree.TreeNode;

public interface ITree {
    public boolean insert(int v);
    public boolean delete(int v);
    public boolean contains(int v);
    public int[] inOrder();
    public int height();
    public int size();

    public String getName();
}
