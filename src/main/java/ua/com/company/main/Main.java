package ua.com.company.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ua.com.company.entity.Person;
import ua.com.company.entity.Product;

public class Main {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger(Main.class);
		log.debug("start main class");
		
		SessionFactory factory = HibernateUtill.getSessionFactory(); 
		Session session = factory.openSession();
		session.getTransaction().begin();
		
		List<Product> productList = new ArrayList<>();
		
		Product product1 = new Product("semki", "low");
		Product product2 = new Product("pivas", "medium");
		Product product3 = new Product("papirosu", "low");
		
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		
		
		Person person = new Person("doe", "jons");
		person.setProductList(productList);
//		session.save(person);
		
		Person person2 = session.get(Person.class, 1);
		System.out.println(person2);
		
		String querry= "SELECT p FROM Person p WHERE p.nicname = (:nickname)";
//		Query queryObj = session.createQuery(querry);
		TypedQuery<Person> queryObj = session.createQuery(querry);
		queryObj.setParameter("nickname", "doe");
		List<Person> resultList = queryObj.getResultList();
		Person persFromDB = queryObj.getSingleResult();
		System.out.println(persFromDB);
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		
		criteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
		// to extract one column value use:
		criteriaQuery.select(root.get("name"));
		// --> this will return List<String> with all names in table Person
		List<Person> resultList2 = session.createQuery(criteriaQuery).getResultList();
		
		System.out.println(resultList2);
		
		
		session.getTransaction().commit();
		
		
		session.close();
		HibernateUtill.shutDown();
	}

}
