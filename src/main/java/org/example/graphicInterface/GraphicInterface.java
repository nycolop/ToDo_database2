package org.example.graphicInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GraphicInterface extends Application {
  final private WebView wv;
  final private String htmlPath;
  final private Scene scene;
  final private BorderPane root;

  public GraphicInterface() {
    this.wv = new WebView();
    this.htmlPath = getClass().getResource("/UI/index.html").toExternalForm();
    this.root = new BorderPane();
    this.scene = new Scene(root);
  }

  @Override
  public void start(Stage stage) {
    wv.getEngine().load(htmlPath);
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
