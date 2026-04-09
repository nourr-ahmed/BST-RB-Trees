package Tests;


import Trees.BSTree;
import Trees.ITree;
import Validation.Validator;
import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
    }

    @Test
    public void InsertAndContainTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.insert(6);

        assertTrue(tree.contains(6));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(7));

//        insert existing
        tree.insert(6);
        Validator.check(tree);

    }

    @Test
    public void DeleteTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.delete(5);
        assertFalse(tree.contains(5));

//        delete non existing
        tree.delete(6);
    }

    @Test
    public void DeleteNodeWithOneChildTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(3);
        tree.delete(2);

        Validator.check(tree);
    }

    @Test
    public void DeleteNodeWithTwoChildrenTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(4);
        tree.insert(1);
        tree.insert(3);
        tree.delete(2);

        Validator.check(tree);
    }

    @Test
    public void InorderTest() {
        ITree tree = new BSTree();
        for (int i = 1; i < 10; i++) {
            tree.insert(i);
        }
        Validator.check(tree);
    }

    @Test
    public void sizeTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.insert(6);
        assertEquals(2, tree.size());
        tree.delete(5);
        assertEquals(1, tree.size());

        tree.insert(6);
        assertEquals(1, tree.size());

        tree.delete(5);
        assertEquals(1, tree.size());

    }

    @Test
    public void heightTest() {
        ITree tree = new BSTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        assertEquals(3, tree.height());
        tree.insert(4);
        assertEquals(3, tree.height());
    }
}
