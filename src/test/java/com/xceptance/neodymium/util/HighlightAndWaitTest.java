package com.xceptance.neodymium.util;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.NeodymiumRunner;
import com.xceptance.neodymium.module.statement.browser.multibrowser.Browser;

@RunWith(NeodymiumRunner.class)
@Browser("Chrome_1024x768")
public class HighlightAndWaitTest
{
    @Test
    public void testHighlighting()
    {
        Context.get().configuration.setProperty("implicitWait", "1000");
        Selenide.open("https://blog.xceptance.com/");
        HighlightAndWait.setUp();

        final List<WebElement> list = $("body").findElements(By.cssSelector("#masthead"));
        HighlightAndWait.highlightElements(list, Context.get().driver);
        $(".neo-highlight-box").shouldBe(visible);

        HighlightAndWait.resetHighlight();
        $(".neo-highlight-box").shouldNot(exist);

        final List<WebElement> list2 = $("body").findElements(By.cssSelector("#content article"));
        HighlightAndWait.highlightElements(list2, Context.get().driver);
        $$(".neo-highlight-box").shouldHaveSize(10);

        HighlightAndWait.resetHighlight();
        $(".neo-highlight-box").shouldNot(exist);
    }

    @Test
    public void testWaiting()
    {
        final long waitingTime = 2000;
        Context.get().configuration.setProperty("implicitWait", Long.toString(waitingTime));
        Context.get().configuration.setProperty("highlightSelectors", "true");

        long beforeAction = 0;
        long afterAction = 0;

        // one wait due to navigation
        beforeAction = new Date().getTime();
        Selenide.open("https://blog.xceptance.com/");
        afterAction = new Date().getTime();
        Assert.assertTrue(waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 2 * waitingTime);

        // one wait due to find
        beforeAction = new Date().getTime();
        $("body #masthead").should(exist);
        afterAction = new Date().getTime();
        Assert.assertTrue(waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 2 * waitingTime);

        // two waits due to chain finding
        beforeAction = new Date().getTime();
        $("body").findElements(By.cssSelector("#content article"));
        afterAction = new Date().getTime();
        Assert.assertTrue(2 * waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 3 * waitingTime);

        // two waits due to find and click
        beforeAction = new Date().getTime();
        $("#text-3 h1").click();
        afterAction = new Date().getTime();
        Assert.assertTrue(2 * waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 3 * waitingTime);

        $("#masthead .search-toggle").click();

        // three waits due to find and change value (consumes 2 waits)
        beforeAction = new Date().getTime();
        $("#search-container .search-form input.search-field").val("abc");
        afterAction = new Date().getTime();
        Assert.assertTrue(3 * waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 4 * waitingTime);

        // two waits due to find and press enter
        beforeAction = new Date().getTime();
        $("#search-container .search-form input.search-field").pressEnter();
        afterAction = new Date().getTime();
        Assert.assertTrue(2 * waitingTime < afterAction - beforeAction);
        Assert.assertTrue(afterAction - beforeAction < 3 * waitingTime);
    }
}
