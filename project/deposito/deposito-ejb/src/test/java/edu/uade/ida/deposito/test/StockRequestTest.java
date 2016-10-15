package edu.uade.ida.deposito.test;

import static org.junit.Assert.*;

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

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class).addAsManifestResource(EmptyAsset.INSTANCE,
				"beans.xml");
		// System.out.println(archive.toString(true));
		return archive;
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
