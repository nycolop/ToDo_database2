package org.example.graphicInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker;

public class GraphicInterface extends Application {
  final private WebView wv;
  final private String htmlPath;
  final private Scene scene;
  final private BorderPane root;
  final private WebEngine we;
  final private JavaConnector javaConnector;

  public GraphicInterface() {
    this.wv = new WebView();
    this.htmlPath = getClass().getResource("/UI/login/index.html").toExternalForm();
    this.root = new BorderPane();
    this.scene = new Scene(root);
    this.we = this.wv.getEngine();
    this.javaConnector = new JavaConnector(this.we);
  }

  @Override
  public void start(Stage stage) {
    we.setJavaScriptEnabled(true);

    we.setOnError(event -> {
      System.out.println("JavaScript Error: " + event.getMessage());
    });

    we.setOnAlert(event -> {
      System.out.println("JavaScript Alert: " + event.getData());
    });

    we.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == Worker.State.SUCCEEDED) {
        JSObject window = (JSObject) we.executeScript("window");
        window.setMember("app", javaConnector);
        we.executeScript("document.dispatchEvent(new Event('appReady'));");
      }
    });

    we.load(htmlPath);
    loadPanel(stage);
  }

  private void loadPanel(Stage stage) {
    root.setCenter(wv);
    stage.setScene(scene);
    stage.setTitle("ToDo DB2 final project - Group D");
    stage.setMaximized(true);
    stage.show();
  }
}
