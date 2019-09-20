package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.EmployeeLOB;
import com.nt.utility.FileUtility;
import com.nt.utility.HibernateUtil;

public class LOBDeleteTest {

	public static void main(String[] args) {
		Session ses=null;
		java.sql.Blob blob=null;
		java.sql.Clob clob=null;
		EmployeeLOB empLOB=null;
		Transaction tx=null;
		boolean flag=false;
		
		//get SEssion session 
		ses=HibernateUtil.getSession();
		  //Load object
		empLOB=ses.get(EmployeeLOB.class,143);
		if(empLOB==null) {
			System.out.println("Record not found");
			return;
		}
		try {
			tx=ses.beginTransaction();
		     //delete obj 
			ses.delete(empLOB);
			flag=true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			 if(flag) {
				 tx.commit();
				 System.out.println("Object is blocked (SoftDeleted)");
			 }
			 else {
				 tx.rollback();
				 System.out.println("Object is not blocked ");
			 }
			//close objs
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class
