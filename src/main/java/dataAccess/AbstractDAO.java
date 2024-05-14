package dataAccess;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
 protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());
    /**
     * Clasa generica care va contine metodele CRUD generate prin Reflection
     *
     * @param <T> Tipul generic care va fi prelucrat
     */
 private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * @param field Campul dupa care se face interogarea
     * @return String-ul interogarii
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    private String createSelectQuery() {
        StringBuilder selectQueryString = new StringBuilder();
        selectQueryString.append("SELECT * FROM " + type.getSimpleName().toLowerCase());
        return selectQueryString.toString();
    }

    /**
     * @param t Obiectul care se cauta in baza de date
     * @return ID-ul obiectului
     */

    public int findID(T t) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(t.getClass().getDeclaredFields()[0].getName());
        try {
            Field f = t.getClass().getDeclaredFields()[0];
            f.setAccessible(true);
            dbConnection = ConnectionFactory.getConnection();
            statement = dbConnection.prepareStatement(query);
            statement.setObject(1, f.get(t));
            resultSet = statement.executeQuery();
            while (resultSet.next())
                return resultSet.getInt(1);

        } catch (IllegalArgumentException | IllegalAccessException | SQLException ex) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findId " + ex.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return -1;
    }
    /**
     * @param resultSet Setul de rezultate din care se va genera lista de obiecte
     * @return O lista de obiecte care vor fi generate
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (SecurityException | IllegalArgumentException | SQLException | IntrospectionException
                 | InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<T> findAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            dbConnection = ConnectionFactory.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException ex) {

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
    /**
     * @param id ID-ul obiectului care este cautat intr-o tabela
     * @return Un obiect concret generat daca acesta se gaseste in tabela, altfel NULL
     */
    public T findById(int id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            dbConnection = ConnectionFactory.getConnection();
            statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException ex) {

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
    /**
     * @param t Obiectul folosit pentru a prelua numele campurilor
     * @return String-ul interogarii de insert
     */
    private String createInsertQuery(T t) {
        StringBuilder buildInsertQueryString = new StringBuilder();
        int numberOfFields = t.getClass().getDeclaredFields().length - 1;
        int indexOfField = 0;

        buildInsertQueryString.append("INSERT INTO " + type.getSimpleName().toLowerCase() + "(");
        for (Field field : t.getClass().getDeclaredFields()) {
            if (indexOfField < numberOfFields)
                buildInsertQueryString.append(field.getName() + ",");
            else
                buildInsertQueryString.append(field.getName() + ")");
            indexOfField++;
        }
        buildInsertQueryString.append(" VALUES (");
        indexOfField = 0;

        for (@SuppressWarnings("unused") Field field : t.getClass().getDeclaredFields()) {
            if (indexOfField < numberOfFields)
                buildInsertQueryString.append("?,");
            else
                buildInsertQueryString.append("?);");
            indexOfField++;
        }
        return buildInsertQueryString.toString();
    }
    /**
     * @param t Obiectul care urmeaza a fi inserat in baza de date
     */
    public void insert(T t) {
        String insertQuery = createInsertQuery(t);
        try (Connection dbConnection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            int parameterIndex = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object fieldValue = field.get(t);
                insertStatement.setObject(parameterIndex++, fieldValue);
            }
            insertStatement.executeUpdate();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            LOGGER.log(Level.WARNING, "Error in insert method: " + ex.getMessage(), ex);
        }
    }

    /**
     * @param t Obiectul folosit pentru a prelua numele campurilor
     * @return String-ul interogarii
     */

    private String createUpdateQuery(T t) {
        StringBuilder updateQueryString = new StringBuilder();
        updateQueryString.append("UPDATE " + type.getSimpleName().toLowerCase() + " SET ");
        int numberOfFields = t.getClass().getDeclaredFields().length - 1;
        int indexOfField = 0;
        for (Field f : t.getClass().getDeclaredFields()) {
            if (indexOfField < numberOfFields)
                updateQueryString.append(f.getName() + " = ?, ");
            else
                updateQueryString.append(f.getName() + " = ?");
            indexOfField++;
        }
        updateQueryString.append(" WHERE " + t.getClass().getDeclaredFields()[0].getName() + " = ?");
        return updateQueryString.toString();
    }
    /**
     * @param t    Obiectul folosit pentru a prelua numele campurilor
     * @param name Campul care va fi folosit pentru clauza WHERE
     */
    public void update(T t, String name) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateQuery = createUpdateQuery(t);
        try {
            updateStatement = dbConnection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object fieldValue = field.get(t);
                updateStatement.setObject(parameterIndex, fieldValue);
                parameterIndex++;
            }
            updateStatement.setObject(parameterIndex, name);
            updateStatement.executeUpdate();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            LOGGER.log(Level.WARNING, "AbstractDAO:update " + ex.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }


    public void select() {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        String selectQuery = createSelectQuery();
        try {
            selectStatement = dbConnection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            resultSet = selectStatement.executeQuery();

        } catch (SQLException | IllegalArgumentException | SecurityException ex) {
            LOGGER.log(Level.WARNING, "AbstractDAO:select " + ex.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    /**
     * @param t Obiectul folosit pentru a prelua numele campurilor
     * @return String-ul interogarii
     */
    private String createDeleteQuery(T t) {
        StringBuilder deleteQueryString = new StringBuilder();
        deleteQueryString.append("DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE "
                + t.getClass().getDeclaredFields()[0].getName() + " = ?");
        return deleteQueryString.toString();
    }
    /**
     * @param t Obiectul folosit pentru a prelua numele campurilor
     */
    public void delete(T t) {
        String deleteQuery = createDeleteQuery(t);
        try (Connection dbConnection = ConnectionFactory.getConnection();
             PreparedStatement deleteStatement = dbConnection.prepareStatement(deleteQuery)) {
            Field f = t.getClass().getDeclaredFields()[0];
            f.setAccessible(true);
            Object fieldValue = f.get(t);
            deleteStatement.setObject(1, fieldValue);
            deleteStatement.executeUpdate();
        } catch (SQLException | IllegalArgumentException | IllegalAccessException ex) {
            LOGGER.log(Level.WARNING, "Error in delete method: " + ex.getMessage(), ex);
        }
    }

}
