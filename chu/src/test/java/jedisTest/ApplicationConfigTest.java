package jedisTest;


import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chu.redisTest.ApplicationConfig;

/**
 * @author Jon Brisbin
 */
public class ApplicationConfigTest {

    @Test
    public void boostrapFromJavaConfig() {
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Assert.assertTrue(appCtx!=null);
    }

}
