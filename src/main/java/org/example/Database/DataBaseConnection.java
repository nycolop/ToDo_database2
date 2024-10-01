package org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.Task;

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

  public int getUsuario(String nickName, String password) {
    String query = "SELECT * FROM Usuario WHERE nick = ? AND contrasena = ?;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, nickName);
      preparedStatement.setString(2, password);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        return resultSet.getInt("id");
      }
      return -1;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return -1;
    }
  }

  public List<Task> getUserTasks(int userId) {
    String query = "SELECT t.* " + "FROM Usuario u "
        + "JOIN Usuario_Tarea ut ON u.id = ut.usuario_id " + "JOIN Tarea t ON ut.tarea_id = t.id "
        + "WHERE u.id = ?";
    List<Task> tasks = new ArrayList<>();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, userId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String name = resultSet.getString("nombre");
        String description = resultSet.getString("descripcion");
        String priority = resultSet.getString("prioridad");
        String status = resultSet.getString("estado");
        String creationDate = resultSet.getString("fecha_creacion");
        String startDate = resultSet.getString("fecha_inicio");
        String estimatedEndDate = resultSet.getString("fecha_fin_estimado");

        tasks.add(new Task(name, description, priority, status, creationDate, startDate,
            estimatedEndDate));
      }
    } catch (SQLException e) {
      System.out.println("Error en la consulta: " + e.getMessage());
    }

    return tasks;
  }

  public void viewTasks() {
    String query = "SELECT * FROM vista_usuarios_tareas;";

    try {
      ResultSet resultSet = statement.executeQuery(query);
      if (!resultSet.isBeforeFirst()) {
        return;
      }

      while (resultSet.next()) {
        String nombreCompleto = resultSet.getString("nombre_completo");
        int tareaId = resultSet.getInt("tarea_id");
        String nombreTarea = resultSet.getString("nombre_tarea");
        String estado = resultSet.getString("estado");
        String fechaInicio = resultSet.getString("fecha_inicio");
        String fechaFinEstimado = resultSet.getString("fecha_fin_estimado");

        System.out.println("Nombre Completo: " + nombreCompleto);
        System.out.println("Tarea ID: " + tareaId);
        System.out.println("Nombre Tarea: " + nombreTarea);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha Inicio: " + fechaInicio);
        System.out.println("Fecha Fin Estimado: " + fechaFinEstimado);
        System.out.println("---------------------------");
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}