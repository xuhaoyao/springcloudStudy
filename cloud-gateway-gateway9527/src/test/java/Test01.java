import org.junit.Test;

import java.time.ZonedDateTime;

public class Test01 {

    @Test
    public void test(){
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
    }

}
