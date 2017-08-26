package uk.co.trycatchfinallysoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * LifeCycleLoggerListener which logs the phases of Test Execution Life Cycle
 *
 * @author William Brown
 */
public class LifeCycleLoggerListener extends DependencyInjectionTestExecutionListener {
    private final Logger Logger = LoggerFactory.getLogger(getClass());

    public Logger getLogger() {
        return Logger;
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        getLogger().info("Running test class " + testContext.getTestClass().getName());
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        getLogger().info("After test execution " + testContext.getTestInstance().toString());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        getLogger().info("After test method " + testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        getLogger().info("After test class " + testContext.getTestClass().getName());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        getLogger().info("Before test method " + testContext.getTestMethod().getName());
    }
}
