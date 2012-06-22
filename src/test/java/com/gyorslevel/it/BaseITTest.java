package com.gyorslevel.it;

import com.gyorslevel.jmx.JMXBean;
import com.gyorslevel.timer.UserExpireController;
import java.lang.annotation.Annotation;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Ignore
public class BaseITTest {

    protected static UserExpireController expireController;
    protected static JMXBean jmxbean;

    /**
     * This can be used by direct call i.e. from methods annotated with @Before
     * or by using the {@link TestResource} annotation on @Test methods
     * @param resourceTypes 
     */
    protected void injectResources(ResourceType[] resourceTypes) {

        ApplicationContext context = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");

        for (ResourceType resourceType : resourceTypes) {
            switch (resourceType) {
                case JMXBean: {
                    jmxbean = (JMXBean) context.getBean("jmxbean");
                }
                case UserExpireController: {
                    expireController = (UserExpireController) context.getBean("expController");
                }
            }
        }

    }

    @Rule
    public MethodRule watchman = new TestWatchman() {

        public void starting(FrameworkMethod method) {

            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof TestResource) {
                    injectResources(((TestResource) annotation).resourceTypes());
                }
            }

        }
    };
    
}