package ch.heigvd.amt.gamification.Util;

import ch.heigvd.amt.gamification.Model.entity.Developer;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DeveloperValidator {

    private HttpServletRequest request;

    private Map<String, String> errors = new HashMap<>();

    private Developer base;

    public DeveloperValidator(HttpServletRequest request) {
        this.request = request;
        this.base = new Developer();
    }

    public DeveloperValidator(HttpServletRequest request, Developer base) {
        this(request);
        this.base = base;
    }

    public Developer populate() {

        String email        = getOrThrow("email");
        String password     = getOrThrow("password");
        String city         = getOrThrow("city");
        String npa          = getOrThrow("npa");
        String street       = getOrThrow("street");
        String firstName    = getOrThrow("firstName");
        String lastName     = getOrThrow("lastName");

        if(!EmailValidator.getInstance().isValid(email))
            errors.put("email", "Not an email address");

        // If new account, check password
        if(password.length() < 8)
            errors.put("password", "Password should be at least 8 characters long");

        Integer nnpa = npa.isEmpty() ? null : Integer.valueOf(npa);
        base.setEmail(email);
        base.setPassword(password);
        base.setCity(city);
        base.setNpa(nnpa);
        base.setStreet(street);
        base.setFirstName(firstName);
        base.setLastName(lastName);

        return base;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private String getOrThrow(String field) {

        String item = request.getParameter(field);

        if(item == null || item.isEmpty()) {
            errors.put(field, "This field is mandatory");
            return "";
        }
        else
            return item;
    }
}
