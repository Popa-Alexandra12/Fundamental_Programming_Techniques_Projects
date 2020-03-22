package DAOClasses;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import DBAccessClass.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;

	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	private String createSelectQuery(String field) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("SELECT ");
		strBuild.append(" * ");
		strBuild.append(" FROM ");
		strBuild.append(type.getSimpleName());
		strBuild.append(" WHERE " + field + " =?");
		return strBuild.toString();
	}
	private String createInsertQuery() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("INSERT ");
		strBuild.append(" INTO ");
		strBuild.append(type.getSimpleName());
		strBuild.append(" VALUES ( ");

		Field[] campuri = type.getDeclaredFields();
		for (int i = 0; i < campuri.length - 1; i++) {
			strBuild.append(" ?, ");
		}
		strBuild.append(" ? )");
		return strBuild.toString();
	}
	public void insertItem(T obj) {
		Connection connect = null;
		PreparedStatement statement = null;
		int k = 0;
		try {
			connect = ConnectionFactory.getConnection();
			statement = connect.prepareStatement(createInsertQuery());
			for (Field field : type.getDeclaredFields()) {
				Object value;
				PropertyDescriptor propertyDescriptor;
				propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				value = method.invoke(obj);
				k++;
				statement.setObject(k, value);
			}
			statement.executeUpdate();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private String createDeleteQuery(String field) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("DELETE ");
		strBuild.append(" FROM ");
		strBuild.append(type.getSimpleName());
		strBuild.append(" WHERE " + field + " =?");
		return strBuild.toString();
	}
	public void deleteItem(int id) {
		Connection connect = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery(type.getDeclaredFields()[0].getName());
		try {
			connect = ConnectionFactory.getConnection();
			statement = connect.prepareStatement(query);
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connect);
		}
	}
	public T findById(int id) {
		Connection connect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(type.getDeclaredFields()[0].getName());
		connect = ConnectionFactory.getConnection();
		try {
			statement = connect.prepareStatement(query);

			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException | IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		ConnectionFactory.close(resultSet);
		ConnectionFactory.close(statement);
		ConnectionFactory.close(connect);
		
		}return null;
	}
	private String createUpdateQuery(String field, String someField) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("UPDATE ");
		strBuild.append(type.getSimpleName());
		strBuild.append(" SET ");
		strBuild.append(field + "=? ");
		strBuild.append(" WHERE " + someField + "=? ");
		return strBuild.toString();
	}
	public T updateItems(String field, String someField, Object obj1, Object obj2) {
		Connection connect = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery(field, someField);
		try {
			connect = ConnectionFactory.getConnection();
			statement = connect.prepareStatement(query);
			statement.setObject(1, obj1);
			statement.setObject(2, obj2);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage()); 
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connect);
		}
		return null;
	}
	private String createViewAllQuery() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("SELECT ");
		strBuild.append(" * ");
		strBuild.append(" FROM ");
		strBuild.append(type.getSimpleName());
		return strBuild.toString();
	}
	public List<T>  listTable(){ 
		Connection connect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createViewAllQuery();
		connect = ConnectionFactory.getConnection();
		try {
			statement = connect.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException  | IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		ConnectionFactory.close(resultSet);
		ConnectionFactory.close(statement);
		ConnectionFactory.close(connect);
		
		 }return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor;
					propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException | IllegalAccessException | SecurityException | SQLException | InvocationTargetException | IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}

}
