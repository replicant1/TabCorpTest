package tab.com.au.codetest.data;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.inject.Singleton;

@Singleton
public class JsonSerializer implements Serializer {

    protected ObjectMapper objectMapper;

    protected Map<Class<?>, ObjectReader> objectReaderMap;

    protected ReadWriteLock objectReaderMapLock;

    protected ObjectWriter objectWriter;

    public JsonSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule("TABModule")
                .addSerializer(DateTime.class, new DateTimeSerializer())
                .addDeserializer(DateTime.class, new DateTimeDeserializer())
        );
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectReaderMap = new HashMap<Class<?>, ObjectReader>();
        objectReaderMapLock = new ReentrantReadWriteLock();
        objectWriter = objectMapper.writer();
    }


    public <T> T read(InputStream src, TypeReference<T> typeReference) throws IOException {
        return getObjectReader(typeReference).readValue(src);
    }

    @Override
    public <T> T read(InputStream src, Class<T> clazz) throws IOException {
        return getObjectReader(clazz).readValue(src);
    }

    @Override
    public <T> T read(byte[] src, Class<T> clazz) throws IOException {
        return getObjectReader(clazz).readValue(src);
    }

    @Override
    public <T> T read(String src, Class<T> clazz) throws IOException {
        return getObjectReader(clazz).readValue(src);
    }

    @Override
    public <T> T read(File file, Class<T> clazz) throws IOException {
        return getObjectReader(clazz).readValue(file);
    }

    @Override
    public void write(Object data, OutputStream dest) throws IOException {
        getObjectWriter().writeValue(dest, data);
    }

    @Override
    public void write(Object data, File dest) throws IOException {
        getObjectWriter().writeValue(dest, data);
    }

    @Override
    public byte[] write(Object data) throws IOException {
        return getObjectWriter().writeValueAsBytes(data);
    }

    @Override
    public String writeToString(Object data) throws IOException {
        return getObjectWriter().writeValueAsString(data);
    }

    protected ObjectReader getObjectReader(Class<?> clazz) {
        objectReaderMapLock.readLock().lock();
        try {
            if (objectReaderMap.containsKey(clazz)) {
                return objectReaderMap.get(clazz);
            }
        } finally {
            objectReaderMapLock.readLock().unlock();
        }
        ObjectReader reader = objectMapper.reader(clazz);
        objectReaderMapLock.writeLock().lock();
        try {
            objectReaderMap.put(clazz, reader);
        } finally {
            objectReaderMapLock.writeLock().unlock();
        }
        return reader;
    }

    protected ObjectReader getObjectReader(TypeReference<?> typeReference) {
        return objectMapper.reader(typeReference);
    }

    protected ObjectWriter getObjectWriter() {
        return objectWriter;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static class DateTimeDeserializer extends StdScalarDeserializer<DateTime> {

        protected DateTimeDeserializer() {
            super(DateTime.class);
        }

        @Override
        public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonToken curr = jp.getCurrentToken();
            if (curr == JsonToken.VALUE_STRING) {
                return DateTime.parse(jp.getText());
            } else {
                String text = jp.getValueAsString();
                if (text != null) {
                    return DateTime.parse(text);
                }
            }
            throw ctxt.mappingException(_valueClass, curr);
        }

        @Override
        public DateTime deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
            return deserialize(jp, ctxt);
        }
    }

    public static class DateTimeSerializer extends StdScalarSerializer<DateTime> {

        protected DateTimeSerializer() {
            super(DateTime.class);
        }

        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeString(value.toString());
        }
    }
}
