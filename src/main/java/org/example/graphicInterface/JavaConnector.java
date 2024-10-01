package org.example.graphicInterface;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import org.example.Database.DataBaseConnection;
import org.example.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JavaConnector {

  private final WebEngine we;
  private DataBaseConnection database;
  int userId;

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
        () -> we.load(getClass().getResource("/UI/home/index.html").toExternalForm()));
  }

  public int login(String nickName, String password) {
    userId = database.getUsuario(nickName, password);
    return userId;
  }

  public String getUserTasks() {
    try {
      List<Task> tasks = database.getUserTasks(userId);
      ObjectMapper mapper = new ObjectMapper();

      return mapper.writeValueAsString(tasks);
    } catch(IOException e) {
      e.printStackTrace();
      return "[]";
    }
  }
}
