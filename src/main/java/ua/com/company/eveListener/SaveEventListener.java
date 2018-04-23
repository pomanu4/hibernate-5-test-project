package ua.com.company.eveListener;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import ua.com.company.entity.Person;

public class SaveEventListener implements SaveOrUpdateEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {

		System.out.println("save method invoke");
		Person person = (Person) event.getEntity();
		System.out.println(person);

	}

}
