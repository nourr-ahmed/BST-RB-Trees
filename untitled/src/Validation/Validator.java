package Validation;

import Nodes.RBNode;
import Trees.ITree;
import Trees.RBTree;

import static Nodes.RBNode.Color.BLACK;
import static Nodes.RBNode.Color.RED;

public class Validator {
    public static void check(ITree tree) {
        checkBSTProperty(tree);
        if (tree instanceof RBTree) checkRBTProperties((RBTree) tree);
    }

    private static void checkBSTProperty(ITree tree) {
        int[] arr = tree.inOrder();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[i - 1]) throw new RuntimeException("BST property violated");
        }
    }

    private static void checkRBTProperties(RBTree tree) {
        RBNode root = (RBNode) tree.getRoot();
        RBNode nil = (RBNode) tree.getNil();
        if (root.color != RBNode.Color.BLACK)
            throw new RuntimeException("RB property violated: Root is red");
        if (nil.color != RBNode.Color.BLACK)
            throw new RuntimeException("RB property violated: Nil is red");
        checkNoRedRed(root, nil);
        checkBlackLength(root, nil);
    }

    private static void checkNoRedRed(RBNode root, RBNode nil) {
        if (root == nil) return;
        if (root.color == RED) {
            if (((RBNode)root.left).color == RED
                || ((RBNode)root.right).color == RED)
                throw new RuntimeException("RB property violated: Red Red is found");
        }
        checkNoRedRed((RBNode)root.left, nil);
        checkNoRedRed((RBNode)root.right, nil);
    }

    private static int checkBlackLength(RBNode root,  RBNode nil) {
        if (root == nil) return 1;
        int leftLength = checkBlackLength((RBNode)root.left, nil);
        int rightLength = checkBlackLength((RBNode)root.right, nil);
        if (leftLength != rightLength) throw new RuntimeException("RB property violated: Left and Right black lengths are not equal");
        return leftLength + (root.color == BLACK ? 1 : 0);
    }
}
