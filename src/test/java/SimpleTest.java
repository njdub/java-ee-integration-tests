import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 14-Apr-16.
 *
 * @author Nazar Dub
 */
public class SimpleTest {

    @Test
    public void testSimple() throws Exception {
        Assert.assertTrue(true);
    }

    @Test(expected = NullPointerException.class)
    public void testException() throws Exception {
        throw new NullPointerException();
    }
}
