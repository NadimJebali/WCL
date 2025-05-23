package com.example.backend.exception.modelsexception;

public class NotFound_404 extends RuntimeException {
    
    public NotFound_404(String msg)
    {
        super(msg);
    }
}
