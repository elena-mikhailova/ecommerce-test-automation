package com.github.elenamikhailova.automation.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestExecutionWatcher implements
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        TestWatcher {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(TestExecutionWatcher.class);

    private static final String START_TIME = "startTime";
    private static final String DURATION = "duration";

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getRoot()
                .getStore(NAMESPACE);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        String keyId = context.getUniqueId() + START_TIME;
        getStore(context).put(keyId, System.nanoTime());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        long duration = getSavedDuration(context);
        System.out.printf(
                "[PASSED] %s.%s - %d ms%n",
                context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName(),
                duration
        );
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        long duration = getSavedDuration(context);
        System.out.printf(
                "[FAILED] %s.%s - %d ms - %s%n",
                context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName(),
                duration,
                cause.getMessage()
        );
    }

    private long getDuration(ExtensionContext context) {
        String keyId = context.getUniqueId() + START_TIME;
        Long startTime = getStore(context).remove(keyId, Long.class);

        return startTime != null ? (System.nanoTime() - startTime) / 1_000_000 : 0;
    }

    private long getSavedDuration(ExtensionContext context) {
        String keyId = context.getUniqueId() + DURATION;
        Long duration = getStore(context).remove(keyId, Long.class);

        return duration != null ? duration : 0;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        long duration = getDuration(context);
        String keyId = context.getUniqueId() + DURATION;
        getStore(context).put(keyId, duration);
    }
}
