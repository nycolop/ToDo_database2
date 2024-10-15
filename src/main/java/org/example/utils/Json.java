package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Json<T> {
  public String convertToJson(T item) {
      try {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(item);
      } catch (IOException e) {
        e.printStackTrace();
        return "";
      }
  }
}
