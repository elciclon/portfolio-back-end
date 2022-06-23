package ar.com.portfoliobackend.api.exception;

public class ResourceNotFoundException extends Throwable{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
