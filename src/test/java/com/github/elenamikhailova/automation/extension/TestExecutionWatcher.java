package com.github.elenamikhailova.automation.extension;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestExecutionWatcher implements BeforeTestExecutionCallback, TestWatcher {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(TestExecutionWatcher.class);

    private static final String START_TIME = "startTime";

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getParent()
                .orElseThrow(() ->
                        new IllegalStateException("Parent test context is not available"))
                .getStore(NAMESPACE);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        getStore(context).put(START_TIME, System.nanoTime());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        long duration = getDuration(context);
        System.out.printf(
                "[PASSED] %s.%s - %d ms%n",
                context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName(),
                duration
        );
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        long duration = getDuration(context);
        System.out.printf(
                "[FAILED] %s.%s - %d ms - %s%n",
                context.getRequiredTestClass().getSimpleName(),
                context.getDisplayName(),
                duration,
                cause.getMessage()
        );
    }

    private long getDuration(ExtensionContext context) {
        Long startTime = getStore(context).remove(START_TIME, Long.class);

        if (startTime == null) {
            return 0;
        }
        return (System.nanoTime() - startTime) / 1_000_000;
    }
}
