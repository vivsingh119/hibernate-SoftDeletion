package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.EmployeeLOB;
import com.nt.utility.FileUtility;
import com.nt.utility.HibernateUtil;

public class LOBInsertTest {

	public static void main(String[] args) {
		Session ses=null;
		byte bytes[]=null;
		LobHelper lobHelper=null;
		java.sql.Blob blob=null;
		java.sql.Clob clob=null;
		String fileData=null;
		EmployeeLOB empLOB=null;
		Transaction tx=null;
		boolean flag=false;
		try {
		//get SEssion session 
		ses=HibernateUtil.getSession();
		//convert BLOB file java.sql.Blob obj
		bytes=FileUtility.getBytesArrayFromFile(args[0]);
		lobHelper=ses.getLobHelper();
		blob=lobHelper.createBlob(bytes);
		//convert CLOB file java.sql.Clob obj
		fileData=FileUtility.readFileAsString(args[1]);
		clob=lobHelper.createClob(fileData);
		//create Domain class obj
		 empLOB=new EmployeeLOB();
		 empLOB.setEname("Raja");
		 empLOB.setSalary(80000);
		 empLOB.setEphoto(blob);
		 empLOB.setEresume(clob);
		  tx=ses.beginTransaction();
		    ses.save(empLOB);
		  flag=true;
		}
		catch(HibernateException he) {
			flag=false;
			he.printStackTrace();
		}
		catch(Exception e) {
			flag=false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("object not saved");
			}
			
			//close objs
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class
