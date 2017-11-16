import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.enovikow.otus.MyArrayList;

import java.util.*;

public class MyArrayListTest {

    private List<Integer> mainList;
    private List<String> mainListString;
    private List<Integer> subList;

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
        mainList.add(1);
        boolean isEmpty = mainList.isEmpty();
        Assert.assertTrue(!isEmpty);
    }

    @Test
    public void add() throws Exception {
        mainList.add(1);
        mainList.add(2);
        mainList.add(3);
        mainList.add(4);

        int addedElementsCount = 4;

        Assert.assertSame(addedElementsCount, mainList.size());
    }

    @Test
    public void addAll() throws Exception {
        mainList.add(1);
        Collections.addAll(mainList, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int addedElementsCount = 10;

        Assert.assertSame(addedElementsCount, mainList.size());
    }

    @Test
    public void copy() throws Exception {
        Collections.addAll(mainList, 1, 2, 3, 4, 5);
        Collections.addAll(subList, 6, 7, 8, 9, 10);

        Collections.copy(mainList, subList);

        for (int i = 0; i < mainList.size(); i++) {
            Assert.assertEquals(mainList.get(i), subList.get(i));
        }
    }

    @Test
    public void sort() throws Exception {
        Collections.addAll(mainList, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        Collections.sort(mainList, Integer::compareTo);

        for (int i = 0; i < mainList.size(); i++) {
            Assert.assertEquals(i + 1, (int) mainList.get(i));
        }

        Collections.addAll(mainListString, "c", "b", "a");

        Collections.sort(mainListString, String::compareTo);

        for (int i = 0; i < mainListString.size(); i++) {
            switch (i) {
                case 0: {
                    Assert.assertEquals(mainListString.get(i), "a");
                    break;
                }
                case (1): {
                    Assert.assertEquals(mainListString.get(i), "b");
                    break;
                }
                case (2): {
                    Assert.assertEquals(mainListString.get(i), "c");
                    break;
                }
            }

        }
    }
}
