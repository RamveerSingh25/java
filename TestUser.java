package in.co.rays;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

public class TestUser {

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		// testList();
		// testQuery();
		// testCriteria();
		 //testProjections();
		// testProjectionsA();
		//testfirstLevelCache();
		testsecondLevelCache();
	}

	private static void testAdd() {
		User pojo = new User();

		pojo.setUserId("Login");
		pojo.setPassword("852");
		pojo.setFirstName("Amit");
		pojo.setLastName("Rathore");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		s.save(pojo);

		tx.commit();
		s.close();
	}

	private static void testUpdate() {
		User pojo = new User();

		pojo.setId(6);
		pojo.setUserId("login");
		pojo.setPassword("963");
		pojo.setFirstName("Ranveer");
		pojo.setLastName("Singh");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		s.update(pojo);

		tx.commit();
		s.close();

		System.out.println("Record Updated");
	}

	private static void testDelete() {
		User pojo = new User();

		pojo.setId(7);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		s.delete(pojo);

		tx.commit();
		s.close();

		System.out.println("Record Deleted");
	}

	private static void testGet() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();

		User pojo = (User) s.get(User.class, 4);

		System.out.println(pojo.getId());
		System.out.println(pojo.getFirstName());
		System.out.println(pojo.getLastName());

		s.close();
	}

	private static void testList() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query q = s.createQuery("from User");

		List l = q.list();
		Iterator it = l.iterator();

		User pojo;
		while (it.hasNext()) {
			pojo = (User) it.next();
			System.out.println(pojo.getId());
			System.out.println(pojo.getFirstName());
			System.out.println(pojo.getLastName());
		}
		s.close();
	}

	private static void testQuery() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query query = s.createQuery("select u.id,u.UserId from User u");

		List rows = query.list();
		Iterator it = rows.iterator();

		Object[] columns;

		System.out.println("id\tFirstName\tLastName");
		while (it.hasNext()) {
			columns = (Object[]) it.next();
			Integer id = (Integer) columns[0];
			String userid = (String) columns[1];
			// String firstname=(String)columns[3];
			// String lastname=(String)columns[4];
			System.out.println(id + "\t" + userid);
		}
		s.close();
	}

	private static void testCriteria() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query query = s.createQuery("from User");

		Criteria crit = s.createCriteria(User.class);

		List l = crit.list();

		Iterator it = l.iterator();

		User pojo;
		while (it.hasNext()) {
			pojo = (User) it.next();
			System.out.println(pojo.getId());
			System.out.println(pojo.getFirstName());
			System.out.println(pojo.getLastName());
		}
	}

	private static void testProjections() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query query = s.createQuery("from User");

		Criteria crit = s.createCriteria(User.class);

		ProjectionList p = Projections.projectionList();

		p.add(Projections.property("firstName"));
		crit.setProjection(p);

		List l = crit.list();

		Iterator it = l.iterator();

		while (it.hasNext()) {
			String o = (String) it.next();
			System.out.println(o);
		}
	}

	private static void testProjectionsA() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query query = s.createQuery("from User");

		Criteria crit = s.createCriteria(User.class);

		ProjectionList p = Projections.projectionList();

		p.add(Projections.property("id"));
		p.add(Projections.property("firstName"));
		p.add(Projections.property("lastName"));
		crit.setProjection(p);

		List l = crit.list();

		Iterator it = l.iterator();

		Object[] columns;

		while (it.hasNext()) {
			columns = (Object[]) it.next();
			Integer id = (Integer) columns[0];
			String firstname = (String) columns[1];
			String lastname = (String) columns[2];
			System.out.println(id + "\t" + firstname + "\t" + lastname);
		}
	}

	private static void testfirstLevelCache() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query q = s.createQuery("from User");

		List l = q.list();
		
		Iterator it = l.iterator();

		User pojo;
		while (it.hasNext()) {
			pojo = (User) it.next();
			System.out.println(pojo.getId());
			System.out.println(pojo.getFirstName());
			System.out.println(pojo.getLastName());
		}
		
		Iterator it1 = l.iterator();

		User pojo1;
		while (it1.hasNext()) {
			pojo1 = (User) it1.next();
			System.out.println(pojo1.getId());
			System.out.println(pojo1.getFirstName());
			System.out.println(pojo1.getLastName());
		}
		s.close();
	}
	
	private static void testsecondLevelCache() {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		Session s = sessionFactory.openSession();

		Query q = s.createQuery("from User");

		List l = q.list();
		
		Iterator it = l.iterator();

		User pojo;
		while (it.hasNext()) {
			pojo = (User) it.next();
			System.out.println(pojo.getId());
			System.out.println(pojo.getFirstName());
			System.out.println(pojo.getLastName());
		}
		
		sessionFactory.close();
		s.close();
		
		Iterator it1 = l.iterator();

		User pojo1;
		while (it1.hasNext()) {
			pojo1 = (User) it1.next();
			System.out.println(pojo1.getId());
			System.out.println(pojo1.getFirstName());
			System.out.println(pojo1.getLastName());
		}
		//s.close();
	}

}