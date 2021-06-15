package org.hibernate.bugs;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void warningLoggedForAttributeConverterBackedInterface() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// Do stuff...
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Entity
	static class SampleEntity {

		@GeneratedValue @Id Long id;
		SampleInterface sampleInterface;
	}

	interface SampleInterface {

	}

	static class SampleImpl implements SampleInterface {}

	@Converter(autoApply = true)
	static class SampleInterfaceConverter implements AttributeConverter<SampleInterface, String> {

		/*
		 * (non-Javadoc)
		 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
		 */
		@Override
		public String convertToDatabaseColumn(SampleInterface attribute) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
		 */
		@Override
		public SampleInterface convertToEntityAttribute(String dbData) {
			return null;
		}
	}
}
