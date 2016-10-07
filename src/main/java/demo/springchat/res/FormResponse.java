package demo.springchat.res;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Created by nathaniel.a.camomot on 11/12/2015.
 */
public class FormResponse<T> {

    private boolean success;
    private Map<String, String> errors;
    private T payload;

    public FormResponse(BindingResult result, MessageSource messageSource) {
        success = false;

        for (ObjectError error : result.getAllErrors()) {

            if (errors == null) {
                errors = new HashMap<String, String>();
            }

            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errors.put(fieldError.getField(), message);
            } else {
                errors.put(error.getObjectName(), message);
            }
        }
    }

    public FormResponse(T payload) {
        success = true;
        this.payload = payload;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Boolean getSuccess() {
        return success;
    }

    public T getPayload() {
        return payload;
    }
}
