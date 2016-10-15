package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class StockRequestTest {
	
//	@Deployment
//    public static Archive<?> createTestArchive() {
//        return ShrinkWrap.create(WebArchive.class, "test.war")
//                .addClasses(Member.class, MemberRegistration.class, Resources.class)
//                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//                // Deploy our test datasource
//                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
//    }

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class).addAsManifestResource(EmptyAsset.INSTANCE,
				"beans.xml");
		// System.out.println(archive.toString(true));
		return archive;
	}
	
	@Inject
	Logger log;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
