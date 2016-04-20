package utils;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * Created on 20-Apr-16.
 *
 * @author Nazar Dub
 */
public class DbUnitUtils {
    public static IDataSet getDataSetByPath(String path) throws DataSetException {
        return new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(path));
    }
}
