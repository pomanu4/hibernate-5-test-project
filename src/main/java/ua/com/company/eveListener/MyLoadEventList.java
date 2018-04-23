package ua.com.company.eveListener;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

public class MyLoadEventList implements LoadEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		System.out.println("load mehod invoke");
		String str = event.getEntityId().toString();
		String sstr = loadType.getName();
		System.out.println(str + " " + sstr);

	}

}
