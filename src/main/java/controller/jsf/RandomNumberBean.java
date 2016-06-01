package controller.jsf;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Random;

/**
 * Created on 02-Jun-16.
 *
 * @author Nazar Dub
 */
@ManagedBean
@ApplicationScoped
public class RandomNumberBean {
    private Random random = new Random();

    public int getNextInt() {
        return random.nextInt(8) + 1;
    }

}
