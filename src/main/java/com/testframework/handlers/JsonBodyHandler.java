package com.testframework.handlers;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.ByteArrayInputStream;
import java.net.http.HttpResponse;

public class JsonBodyHandler<T> implements HttpResponse.BodyHandler {

   private final Jsonb json;
   private final Class<T> type;

    public JsonBodyHandler(Jsonb json, Class<T> type) {
        this.json = json;
        this.type = type;
    }

    public static <T> JsonBodyHandler<T> jsonBodyHandler(final Class<T> type){
        return new JsonBodyHandler<>(JsonbBuilder.create(),type);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(final HttpResponse.ResponseInfo responseInfo) {
        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofByteArray(),
                byteArray-> this.json.fromJson(new ByteArrayInputStream(byteArray),this.type));
    }
}
