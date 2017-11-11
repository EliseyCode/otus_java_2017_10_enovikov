
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.enovikow.otus.MyArrayList;

import java.util.*;

public class MyArrayListTest {

    private List<Integer> mainList;
    private List<String> mainListString;
    private List<Integer> subList;

    private static final int TEST_INT_1 = 1;
    private static final int TEST_INT_2 = 2;
    private static final int TEST_INT_3 = 3;
    private static final int TEST_INT_4 = 4;
    private static final int TEST_INT_5 = 5;
    private static final int TEST_INT_6 = 6;
    private static final int TEST_INT_7 = 7;
    private static final int TEST_INT_8 = 8;
    private static final int TEST_INT_9 = 9;
    private static final int TEST_INT_10 = 10;

    private static final String TEST_STRING_A = "a";
    private static final String TEST_STRING_B = "b";
    private static final String TEST_STRING_C = "c";

    public MyArrayListTest() {

    }

    @Before
    public void setUp() throws Exception {
        mainList = new MyArrayList();
        subList = new ArrayList();
        mainListString = new ArrayList();
    }

    @Test
    public void isEmpty() throws Exception {
        boolean isEmpty = mainList.isEmpty();
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void isNotEmpty() throws Exception {
        mainList.add(TEST_INT_1);
        boolean isEmpty = mainList.isEmpty();
        Assert.assertTrue(!isEmpty);
    }

    @Test
    public void add() throws Exception {
        mainList.add(TEST_INT_1);
        mainList.add(TEST_INT_2);
        mainList.add(TEST_INT_3);
        mainList.add(TEST_INT_4);

        int addedElementsCount = 4;

        Assert.assertSame(addedElementsCount, mainList.size());
    }

    @Test
    public void addAll() throws Exception {
        mainList.add(TEST_INT_1);
        Collections.addAll(mainList, TEST_INT_2, TEST_INT_3, TEST_INT_4, TEST_INT_5, TEST_INT_6, TEST_INT_7, TEST_INT_8, TEST_INT_9, TEST_INT_10);
        int addedElementsCount = 10;

        Assert.assertSame(addedElementsCount, mainList.size());
    }

    @Test
    public void copy() throws Exception {
        Collections.addAll(mainList, TEST_INT_1, TEST_INT_2, TEST_INT_3, TEST_INT_4, TEST_INT_5);
        Collections.addAll(subList, TEST_INT_6, TEST_INT_7, TEST_INT_8, TEST_INT_9, TEST_INT_10);

        Collections.copy(mainList, subList);

        for (int i = 0; i < mainList.size(); i++) {
            Assert.assertEquals(mainList.get(i), subList.get(i));
        }
    }

    @Test
    public void sort() throws Exception {
        Collections.addAll(mainList, TEST_INT_10, TEST_INT_9, TEST_INT_8, TEST_INT_7, TEST_INT_6, TEST_INT_5, TEST_INT_4, TEST_INT_3, TEST_INT_2, TEST_INT_1);

        Collections.sort(mainList, Integer::compareTo);

        for (int i = 0; i < mainList.size(); i++) {
            Assert.assertEquals(i + 1, (int) mainList.get(i));
        }

        Collections.addAll(mainListString, TEST_STRING_C, TEST_STRING_B, TEST_STRING_A);

        Collections.sort(mainListString, String::compareTo);

        for (int i = 0; i < mainListString.size(); i++) {
            if (i == 0) {
                Assert.assertEquals(mainListString.get(i), TEST_STRING_A);
            }
            if (i == 1) {
                Assert.assertEquals(mainListString.get(i), TEST_STRING_B);
            }
            if (i == 2) {
                Assert.assertEquals(mainListString.get(i), TEST_STRING_C);
            }

        }
    }
}
