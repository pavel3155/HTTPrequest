package org.example;

public class HTTPException extends Exception{
    String txtErr ="";

    public HTTPException(String txtErr) {
        this.txtErr = txtErr;
        System.out.println(txtErr);
    }
}
