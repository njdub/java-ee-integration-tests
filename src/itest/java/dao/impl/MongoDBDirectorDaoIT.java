package dao.impl;

import dao.DirectorDao;
import dao.conf.MongoDBDataSource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

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
