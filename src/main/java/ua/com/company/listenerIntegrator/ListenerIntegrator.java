package ua.com.company.listenerIntegrator;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import ua.com.company.eveListener.MyLoadEventList;
import ua.com.company.eveListener.SaveEventListener;

public class ListenerIntegrator implements Integrator {

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		
		EventListenerRegistry registry = serviceRegistry.getService(EventListenerRegistry.class);
		
		registry.getEventListenerGroup(EventType.SAVE).appendListener(new SaveEventListener());
		registry.getEventListenerGroup(EventType.LOAD).appendListener(new MyLoadEventList());
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

	}

}
