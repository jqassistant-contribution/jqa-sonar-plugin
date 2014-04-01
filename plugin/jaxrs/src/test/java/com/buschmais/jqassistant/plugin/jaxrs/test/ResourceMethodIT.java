package com.buschmais.jqassistant.plugin.jaxrs.test;

import com.buschmais.jqassistant.core.analysis.api.AnalyzerException;
import com.buschmais.jqassistant.plugin.common.test.AbstractPluginIT;
import com.buschmais.jqassistant.plugin.jaxrs.test.set.beans.MyRestResource;
import org.junit.Test;

import java.io.IOException;

import static com.buschmais.jqassistant.plugin.java.test.matcher.MethodDescriptorMatcher.methodDescriptor;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Test to verify JAX-RS Resource method concepts.
 * 
 * @author Aparna Chaudhary
 */
public class ResourceMethodIT extends AbstractPluginIT {

	/**
	 * Verifies the concept {@code jaxrs:GetResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_GetResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:GetResourceMethod");
		store.beginTransaction();
		assertThat("Expected GetResourceMethod",
				query("MATCH (resourceMethod:JaxRS:GetResourceMethod) RETURN resourceMethod").getColumn("resourceMethod"),
				hasItem(methodDescriptor(MyRestResource.class, "testGet")));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:PutResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_PutResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:PutResourceMethod");
		store.beginTransaction();
		assertThat("Expected PutResourceMethod",
				query("MATCH (resourceMethod:JaxRS:PutResourceMethod) RETURN resourceMethod").getColumn("resourceMethod"),
				hasItem(methodDescriptor(MyRestResource.class, "testPut")));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:PostResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_PostResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:PostResourceMethod");
		store.beginTransaction();
		assertThat("Expected PostResourceMethod",
				query("MATCH (resourceMethod:JaxRS:PostResourceMethod) RETURN resourceMethod").getColumn("resourceMethod"),
				hasItem(methodDescriptor(MyRestResource.class, "testPost", String.class)));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:DeleteResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_DeleteResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:DeleteResourceMethod");
		store.beginTransaction();
		assertThat("Expected DeleteResourceMethod", query("MATCH (resourceMethod:JaxRS:DeleteResourceMethod) RETURN resourceMethod")
				.getColumn("resourceMethod"), hasItem(methodDescriptor(MyRestResource.class, "testDelete")));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:HeadResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_HeadResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:HeadResourceMethod");
		store.beginTransaction();
		assertThat("Expected HeadResourceMethod",
				query("MATCH (resourceMethod:JaxRS:HeadResourceMethod) RETURN resourceMethod").getColumn("resourceMethod"),
				hasItem(methodDescriptor(MyRestResource.class, "testHead")));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:OptionsResourceMethod}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_OptionsResourceMethod_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:OptionsResourceMethod");
		store.beginTransaction();
		assertThat("Expected OptionsResourceMethod", query("MATCH (resourceMethod:JaxRS:OptionsResourceMethod) RETURN resourceMethod")
				.getColumn("resourceMethod"), hasItem(methodDescriptor(MyRestResource.class, "testOptions")));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:SubResourceLocator}.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_SubResourceLocator_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:SubResourceLocator");
		store.beginTransaction();
		assertThat("Expected SubResourceLocator",
				query("MATCH (resourceMethod:JaxRS:SubResourceLocator) RETURN resourceMethod").getColumn("resourceMethod"),
				hasItem(methodDescriptor(MyRestResource.class, "getMySubResource", String.class)));
		assertThat("Expected SubResourceLocator",
				query("MATCH (resourceMethod:JaxRS:SubResourceLocator) RETURN resourceMethod").getColumn("resourceMethod"),
				not(hasItem(methodDescriptor(MyRestResource.class, "testGet"))));
		store.commitTransaction();
	}

	/**
	 * Verifies the concept {@code jaxrs:SubResourceLocator} is not applied to resource methods with entity parameters.
	 * 
	 * @throws java.io.IOException
	 *             If the test fails.
	 * @throws AnalyzerException
	 *             If the test fails.
	 * @throws NoSuchMethodException
	 *             If the test fails.
	 */
	@Test
	public void test_invalid_SubResourceLocator_Concept() throws IOException, AnalyzerException, NoSuchMethodException {
		scanClasses(MyRestResource.class);
		applyConcept("jaxrs:SubResourceLocator");
		store.beginTransaction();
		assertThat("Expected SubResourceLocator",
				query("MATCH (resourceMethod:JaxRS:SubResourceLocator) RETURN resourceMethod").getColumn("resourceMethod"),
				not(hasItem(methodDescriptor(MyRestResource.class, "getMyInvalidSubResource", String.class))));
		store.commitTransaction();
	}
}