package dao.impl;

import dao.DirectorDao;
import dao.conf.MongoDBDataSource;
import entity.Director;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JndiDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.DbUnitUtils.getDataSetByPath;

/**
 * Created on 03-Apr-16.
 *
 * @author Nazar Dub
 */
@RunWith(Arquillian.class)
public class MongoDBDirectorDaoIT {

    @EJB(beanName = "MongoDBDirectorDao")
    private DirectorDao directorDao;

    @EJB
    private MongoDBDataSource mongoDataSource;

    @Deployment
    public static Archive<WebArchive> createDeployment() throws Exception {
        return ShrinkWrap
                .create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory().forTasks("arquillianBuild")
                .importBuildOutput()
                .as(WebArchive.class);
    }

    @Test
    public void testInjection() throws Exception {
        Assert.assertNotNull(directorDao);
        Assert.assertNotNull(mongoDataSource.getDatabase());
    }
}
