package Tests;

import Trees.BSTree;
import Trees.ITree;
import Trees.RBTree;
import Validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RBTTest {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
    }

    @Test
    public void InsertAndContainTest() {
        ITree tree = new RBTree();
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
    public void InsertFUCase1Test() {
        ITree tree = new RBTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);
        tree.insert(3);

        Validator.check(tree);
    }

    @Test
    public void InsertFUCase2Test() {
        ITree tree = new RBTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);

        Validator.check(tree);
    }

    @Test
    public void InsertFUCase3Test() {
        ITree tree = new RBTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(4);

        Validator.check(tree);
    }

    @Test
    public void DeleteTest() {
        ITree tree = new RBTree();
        tree.insert(5);
        tree.delete(5);
        assertFalse(tree.contains(5));

//        delete non existing
        tree.delete(6);

    }

    @Test
    public void DeleteRed() {
        ITree tree = new RBTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.delete(4);

        Validator.check(tree);
    }

    @Test
    public void DeleteFUCasesTest() {
        ITree tree = new RBTree();
        tree.insert(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.insert(19);
        tree.insert(8);
        tree.insert(35);
        tree.insert(32);
        tree.insert(34);
        tree.insert(33);
        tree.insert(10);
        tree.insert(9);

        tree.delete(19);
        Validator.check(tree);

        tree.delete(32);
        Validator.check(tree);

        tree.delete(9);
        Validator.check(tree);

    }


    @Test
    public void InorderTest() {
        ITree tree = new RBTree();
        for (int i = 1; i < 10; i++) {
            tree.insert(i);
        }
        Validator.check(tree);
    }

    @Test
    public void sizeTest() {
        ITree tree = new RBTree();
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
        ITree tree = new RBTree();
        tree.insert(5);
        tree.insert(6);
        assertEquals(2, tree.height());
        tree.insert(7);
        assertEquals(2, tree.height());
        tree.insert(4);
        assertEquals(3, tree.height());
    }
}
