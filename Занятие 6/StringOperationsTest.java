package example;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationsTest {

    StringOperations stringOperations = new StringOperations();
    String nullPointer;

    @Test
    public void testConcatTwo() {
        String actualResult = stringOperations.concatTwo("aa", "bb");
        String expectedResult = "aabb";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConcatTwoNegative() {
        String actualResult = stringOperations.concatTwo("aa", "bb");
        String expectedResult = "aacc";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getStrings() {
        return new Object[][]{
                {"a", "b", "c", "abc"},
                {"", "aa", "", "aa"},
                {"yes", " ", "we can", "yes we can"},
        };
    }

    @Test(dataProvider = "getStrings")
    public void testConcat(String str1, String str2, String str3, String expectedResult) {
        String actualResult = stringOperations.concat(str1, str2, str3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getStringsNegative() {
        return new Object[][]{
                {"a", "b", "c", "acb"},
                {"", "aa", "", " aa "},
                {"yes", " ", "we can", "yes, we can"}
        };
    }

    @Test(dataProvider = "getStringsNegative")
    public void testConcatNegative(String str1, String str2, String str3, String expectedResult) {
        String actualResult = stringOperations.concat(str1, str2, str3);
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] getStringsNullPointer() {
        return new Object[][]{{nullPointer, " ", " ", "  "}};
    }

    @Test(dataProvider = "getStringsNullPointer",
            expectedExceptions = NullPointerException.class)
    public void testConcatNullPointer(String str1, String str2, String str3, String expectedResult) {
        String actualResult = stringOperations.concat(str1, str2, str3);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLength() {
        int expectedResult = 4;
        int actualResult = stringOperations.length("mama");
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLengthNegative() {
        int expectedResult = 4;
        int actualResult = stringOperations.length("daddy");
        Assert.assertNotEquals(actualResult, expectedResult);
    }
}