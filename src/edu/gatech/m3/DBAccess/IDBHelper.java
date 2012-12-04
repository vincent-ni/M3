package edu.gatech.m3.DBAccess;

import java.sql.ResultSet;

public interface IDBHelper {
	public ResultSet executeResultSetQuery(String query);
	public int executeUpdateQuery(String query);
	public int executeScalarQuery(String query);
}
