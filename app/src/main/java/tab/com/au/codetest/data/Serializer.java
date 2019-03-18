package tab.com.au.codetest.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {

    public <T> T read(InputStream src, Class<T> clazz) throws IOException;

    public <T> T read(byte[] src, Class<T> clazz) throws IOException;

    public <T> T read(String src, Class<T> clazz) throws IOException;

    public <T> T read(File src, Class<T> clazz) throws IOException;

    public void write(Object data, OutputStream dest) throws IOException;

    public byte[] write(Object data) throws IOException;

    public String writeToString(Object data) throws IOException;

    public void write(Object data, File dest) throws IOException;
}
