package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nt.utility.HibernateUtil;

public class LOBRetriveTest {

	public static void main(String[] args) {
		Session ses=null;
		Query query=null;
		List<Object[]> list=null;
		try {
		//get SEssion session 
		ses=HibernateUtil.getSession();
		  //Load object
		query=ses.createQuery(" select eid,ename,salary from EmployeeLOB");
		list=query.list();
		list.forEach(row->{
			for(Object val:row) {
				System.out.print(val+" ");
			}
			System.out.println();
		});
		
		
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class
