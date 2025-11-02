package it.unibo.exceptions.fakenetwork.impl;

import java.io.IOException;

public class NetworkException extends IOException{
    public NetworkException() throws IOException {
        throw new IOException("Network error: no response");
    }
    public NetworkException(final String message)throws IOException{ //1-ary constructor: receive one argument
        throw new IOException("Network error while sending message: "+message);
    }
}