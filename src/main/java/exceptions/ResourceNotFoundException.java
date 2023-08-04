package exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // return 404 not  foundstatues
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(long id)
    {
        super("Resource Not Found for id :" +id); // display message in response of postman
    }


}
