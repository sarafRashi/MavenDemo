package com.employee.maven_project_1.service.serviceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.employee.maven_project_1.DBUtil.DBUtil;
import com.employee.maven_project_1.myException.InvalidIdException;
import com.employee.maven_project_1.myException.ServiceException;
import com.employee.maven_project_1.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService
{
	Scanner sc=new Scanner(System.in);
	
	Statement stmt =null;
	ResultSet rs =null;
	Connection cn =null;

	@Override
	public void showAll() throws ServiceException
	{
		try {
			DBUtil dbutil=new DBUtil();
			cn = dbutil.getConnection();
			
			if(cn!=null)
			{
				stmt=cn.createStatement();
			}
			rs=stmt.executeQuery("select * from EmployeeInfo");

			System.out.println("=========Displaying Employee Details=========\n");
			System.out.println("ID"+ "\t" + "NAME" + "\t\t  " + "AGE" + "\t" + "SALARY");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t  "+rs.getInt(3)+"\t"+rs.getInt(4));
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ResultSet display() throws ServiceException
	{
		try {
			rs=stmt.executeQuery("select * from EmployeeInfo where SALARY > 20000");
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		return rs;
	}
	
	@Override
	public void invalidId(int empId) throws ServiceException
	{
		try {
			rs=stmt.executeQuery("select * from EmployeeInfo where EMP_ID= '"+empId+"'");
			if(rs.next())
			{
				do
				{	
					System.out.println("\n====Displaying Employee Details=====");
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t  "+rs.getInt(3)+"\t"+rs.getInt(4));
				}while(rs.next());
			}
			else
			{
				throw new InvalidIdException("Employee id " +empId+" not present in the database");
			}
		}catch (InvalidIdException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateSalary(int empId) throws ServiceException
	{
		try {
			rs=stmt.executeQuery("select EMP_ID from EmployeeInfo");
			while(rs.next())
			{
				if(rs.getInt("EMP_ID") == empId)
				{
					System.out.print("Enter salary: ");
					int empSal=sc.nextInt();
					
					System.out.println("Update salary for employee id: "+empId);
					
					stmt.executeUpdate("UPDATE EmployeeInfo SET SALARY='"+empSal+"' WHERE EMP_ID = '"+empId+"'");
					System.out.println("Salary Updated successfully!!");
					showAll();
				}
			}
		}
		catch (InvalidIdException e) {
			throw new ServiceException(e);
		}
		
		catch(Exception e) {
			throw new ServiceException(e);
		}
		finally
		{
			try {
					rs.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			
			try {
					stmt.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}

			try {
					cn.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			}	
	}
}