package org.example.graphicInterface;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import org.example.Database.DataBaseConnection;
import org.example.Task;
import org.example.User;
import org.example.utils.Json;

public class JavaConnector {

  private final WebEngine we;
  private DataBaseConnection database;
  private User loggedUser;

  public JavaConnector(WebEngine webEngine) {
    this.we = webEngine;

    try {
      this.database = new DataBaseConnection();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void loadLoginView() {
    Platform.runLater(
        () -> we.load(getClass().getResource("/UI/login/index.html").toExternalForm()));
  }

  public void loadHomeView() {
    Platform.runLater(
        () -> we.load(getClass().getResource("/UI/homev2/index-home.html").toExternalForm()));
  }

  public void loadRegisterView() {
    Platform.runLater(
        () -> we.load(getClass().getResource("/UI/user_register/registro.html").toExternalForm()));
  }

  public void logout() {
    loggedUser = null;
    Platform.runLater(
        () -> we.load(getClass().getResource("/UI/login/index.html").toExternalForm()));
  }

  public String login(String nickName, String password) {
    loggedUser = database.getUsuario(nickName, password);
    Json<User> json = new Json<>();
    return json.convertToJson(loggedUser);
  }

  public String getUserTasks() {
    List<Task> tasks = database.getUserTasks(loggedUser.getId());
    Json<List<Task>> json = new Json<>();
    return json.convertToJson(tasks);
  }

  public String getLoggedUser() {
    Json<User> json = new Json<>();
    return json.convertToJson(loggedUser);
  }

  public void createTask(int id, String name, String description, String priority, String status,
      String startDate, String estimatedEndDate) {
    Task newTask = new Task(id, name, description, priority, status, "", startDate, estimatedEndDate);
    database.createTask(newTask, loggedUser.getId());
  }

  public void deleteTask(int taskId) {
    database.deleteTask(taskId);
  }

  public int registerUser(String nombre, String apellido, String contrasena) {
    boolean success = database.insertarUsuario(nombre, apellido, contrasena);

    if (success) {
      return database.getLastInsertedId();
    } else {
      return -1;
    }
  }

  public void updateTask(int id, String name, String description, String priority, String status,
      String startDate, String estimatedEndDate) {
    Task updatedTask = new Task(id, name, description, priority, status, "", startDate, estimatedEndDate);
    database.updateTask(updatedTask);
  }
}
