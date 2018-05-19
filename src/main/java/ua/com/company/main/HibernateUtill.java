package ua.com.company.main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import ua.com.company.listenerIntegrator.ListenerIntegrator;

public class HibernateUtill {

	private final static SessionFactory SESSION_FACTORY = buildSessionFactory();

	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		SessionFactory factory = null;
		try {
			BootstrapServiceRegistryBuilder bootstrapServiceRegistryBuilder = new BootstrapServiceRegistryBuilder();
			BootstrapServiceRegistry registry = bootstrapServiceRegistryBuilder
					.applyIntegrator(new ListenerIntegrator()).build();

			/*
			 * якшо не використовувати xml конфігурацію то можна створити Map<String,
			 * String> settings = new HashMap<>(); settings.put("", ""); всі налаштування з
			 * xml файла, замість BootstrapServiceRegistryBuilder створити
			 * StandardServiceRegistryBuilder.applySetting(String settingName, Object value)
			 * що прийме мапу як параметер перехід на StandardServiceRegistryBuilder -->
			 * StandardServiceRegistryBuilder registryBuilder = new
			 * StandardServiceRegistryBuilder(BootstrapServiceRegistryBuilder b);
			 * додати класи-ентіті в обєкт MetadataSources metadataSources = new MetadataSources(serviceRegistry);
			 * metadataSources.addAnnotatedClass(Person.class);
				metadataSources.addAnnotatedClass(Product.class);
			 */
			
			serviceRegistry = new StandardServiceRegistryBuilder(registry).configure("hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		

			factory = metadata.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			System.out.println("session creation failed");
			e.printStackTrace();
		}

		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

	public static void shutDown() {
		if (serviceRegistry != null) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}
	}
}
