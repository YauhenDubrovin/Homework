package example;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class StringOperationsTest3 {

    StringOperations stringOperations = new StringOperations();
    String nullPointer;

    @Test
    public void testReplace() {
        String actualResult = stringOperations.replace("java", "a", "u");
        String expectedResult = "juvu";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testReplace2() {
        String actualResult = stringOperations.replace("jaava", "a+", "u");
        String expectedResult = "juvu";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(enabled = false)
    public void testReplaceNegative() {
        String actualResult = stringOperations.replace("java", "a", "u");
        String expectedResult = "java";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testReplaceNegative2() {
        String actualResult = stringOperations.replace("java", nullPointer, "u");
        String expectedResult = "java";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testReplaceNegative3() {
        String actualResult = stringOperations.replace("java", "a", nullPointer);
        String expectedResult = "java";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getStrings() {
        return new Object[][]{{" aaa "}, {"  aaa "}, {"  aaa   "}, {"aaa"}};
    }

    @Test(dataProvider = "getStrings")
    public void testDeleteSpaces(String str) {
        String actualResult = stringOperations.deleteSpaces(str);
        String expectedResult = "aaa";
        Assert.assertEquals(actualResult, expectedResult);
    }
}