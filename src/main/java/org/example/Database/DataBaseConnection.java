package org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.Task;
import org.example.User;

public class DataBaseConnection {

  public static Statement statement;
  private static final String url = "jdbc:mysql://localhost:3306/proyectoToDo?useUnicode=true&characterEncoding=UTF-8";
  private static final String user = "root";
  private static final String password = "root";
  private Connection connection;

  public DataBaseConnection() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);
      statement = connection.createStatement();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      System.out.println("Conexion establecida");
    }
  }

  public User getUsuario(String nickName, String password) {
    String query = "{CALL get_user(?, ?)}";

    try {
      CallableStatement prepareCall = connection.prepareCall(query);
      prepareCall.setString(1, nickName);
      prepareCall.setString(2, password);

      ResultSet resultSet = prepareCall.executeQuery();

      if (resultSet.next()) {
        int userId = resultSet.getInt("id");
        String userName = resultSet.getString("nombre");
        String userLastName = resultSet.getString("apellido");
        String userNickName = resultSet.getString("nick");
        String userPassword = resultSet.getString("contrasena");

        return new User(userId, userName, userLastName, userNickName, userPassword);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

  public List<Task> getUserTasks(int userId) {
    String query =
        "SELECT t.* " + "FROM Usuario u " + "JOIN Usuario_Tarea ut ON u.id = ut.usuario_id "
            + "JOIN Tarea t ON ut.tarea_id = t.id " + "WHERE u.id = ?";
    List<Task> tasks = new ArrayList<>();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("nombre");
        String description = resultSet.getString("descripcion");
        String priority = resultSet.getString("prioridad");
        String status = resultSet.getString("estado");
        String creationDate = resultSet.getString("fecha_creacion");
        String startDate = resultSet.getString("fecha_inicio");
        String estimatedEndDate = resultSet.getString("fecha_fin_estimado");

        tasks.add(new Task(id, name, description, priority, status, creationDate, startDate,
            estimatedEndDate));
      }
    } catch (SQLException e) {
      System.out.println("Error en la consulta: " + e.getMessage());
    }

    return tasks;
  }

  public void createTask(Task task, int userId) {
    String query = "INSERT INTO Tarea (nombre, estado, descripcion, prioridad, fecha_inicio, fecha_fin_estimado, fecha_final) VALUES (?, ?, ?, ?, ?, ?, NULL)";
    String query2 = "INSERT INTO Usuario_Tarea (usuario_id, tarea_id) VALUES (?, ?)";

    try {
      PreparedStatement insertTaskStatement = connection.prepareStatement(query);
      PreparedStatement joinUserTaskStatement = connection.prepareStatement(query2);

      ResultSet tasks = statement.executeQuery("SELECT id FROM Tarea;");
      int newTaskId = 0;

      while (tasks.next()) {
        newTaskId = tasks.getInt("id");
      }

      newTaskId += 1;

      insertTaskStatement.setString(1, task.getName());
      insertTaskStatement.setString(2, task.getStatus());
      insertTaskStatement.setString(3, task.getDescription());
      insertTaskStatement.setString(4, task.getPriority());
      insertTaskStatement.setTimestamp(5, Timestamp.valueOf(task.getStartDate()));
      insertTaskStatement.setTimestamp(6, Timestamp.valueOf(task.getEstimatedEndDate()));

      joinUserTaskStatement.setInt(1, userId);
      joinUserTaskStatement.setInt(2, newTaskId);

      insertTaskStatement.executeUpdate();
      joinUserTaskStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteTask(int taskId) {
    String query = "DELETE FROM Tarea WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, taskId);
      int rowsAffected = statement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Task deleted successfully.");
      } else {
        System.out.println("Task not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int getLastInsertedId() {
    int lastId = -1;
    String sql = "SELECT LAST_INSERT_ID()";

    try (PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      if (rs.next()) {
        lastId = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return lastId;
  }


  public boolean insertarUsuario(String nombre, String apellido, String contrasena) {
    String sql = "INSERT INTO Usuario (nombre, apellido, contrasena) VALUES (?, ?, ?)";

    try {
      PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, nombre);
      pstmt.setString(2, apellido);
      pstmt.setString(3, contrasena);
      int rowsAffected = pstmt.executeUpdate();

      if (rowsAffected > 0) {
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            return true;
          }
        }
      }

      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}