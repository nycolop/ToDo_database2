package org.example.graphicInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import org.example.Database.DataBaseConnection;

public class JavaConnector {
  private final WebEngine we;
  private DataBaseConnection database;

  public JavaConnector(WebEngine webEngine) {
    this.we = webEngine;

    try {
      this.database = new DataBaseConnection();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void loadLoginView() {
    Platform.runLater(() -> we.load(getClass().getResource("/UI/login/index.html").toExternalForm()));
  }

  public void loadHomeView() {
    Platform.runLater(() -> we.load(getClass().getResource("/UI/home/index.html").toExternalForm()));
  }

  public int login(String name, String lastName, String password) {
    int userId = database.getUsuario(name, lastName, password);
    return userId;
  }
}
