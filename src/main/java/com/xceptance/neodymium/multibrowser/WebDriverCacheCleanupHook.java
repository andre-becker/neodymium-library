package com.xceptance.neodymium.multibrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xceptance.neodymium.NeodymiumBrowserRunner;

public class WebDriverCacheCleanupHook extends Thread
{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverCacheCleanupHook.class);

    @Override
    public void run()
    {
        LOGGER.debug("All tests finished. Quit cached browser");
        NeodymiumBrowserRunner.quitCachedBrowser();
    }
}
