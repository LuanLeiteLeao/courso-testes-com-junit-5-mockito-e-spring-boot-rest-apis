package br.com.dicasdeumdev.api.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String menssage) {
        super(menssage);
    }
 
    
}