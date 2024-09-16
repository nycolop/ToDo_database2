package org.example.graphicInterface;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher {
  private final Path path;
  private final Runnable callback;

  public FileWatcher(Path path, Runnable callback) {
    this.path = path;
    this.callback = callback;
  }

  public void start() throws IOException {
    WatchService watchService = FileSystems.getDefault().newWatchService();
    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

    new Thread(() -> {
      try {
        WatchKey key;
        while ((key = watchService.take()) != null) {
          for (WatchEvent<?> event : key.pollEvents()) {
            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
              callback.run();
            }
          }
          key.reset();
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }).start();
  }
}