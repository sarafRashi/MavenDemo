package com.employee.maven_project_1.service;

import java.sql.ResultSet;

import com.employee.maven_project_1.myException.ServiceException;

public interface EmployeeService 
{
	public void showAll() throws ServiceException;
	public ResultSet display() throws ServiceException;//displaying employee details whose salary>20000
	public void invalidId(int empId) throws ServiceException;
	public void updateSalary(int empId) throws ServiceException;
}