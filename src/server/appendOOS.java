package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendOOS extends ObjectOutputStream {

    public AppendOOS(OutputStream out) throws IOException {super(out);}
    @Override protected void writeStreamHeader() throws IOException {reset();}
}
