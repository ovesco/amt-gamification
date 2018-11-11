package ch.heigvd.amt.gamification.services.security;

import ch.heigvd.amt.gamification.Model.entity.Account;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class AccountChecker implements IAccountCheckerLocal {

    public static final String EMAIL        = "email";
    public static final String FIRST_NAME   = "firstName";
    public static final String LAST_NAME    = "lastName";
    public static final String STREET       = "street";
    public static final String NPA          = "npa";
    public static final String CITY         = "city";

    public Map<String, String> validate(Account account) {

        Map<String, String> errors  = new HashMap<>();
        String[] fields             = new String[]{
            EMAIL, FIRST_NAME, LAST_NAME, STREET, NPA, CITY
        };

        for(String field : fields) {
            try {
                String methodName = "get" + field.substring(0,1).toUpperCase() + field.substring(1);
                if(Account.class.getMethod(methodName).invoke(account) == null)
            } catch (NoSuchMethodException e) {
                System.out.println("Failed field " + field);
            }
        }
    }

    public void populate(HttpServletRequest request, Account account) {

        account.setEmail(request.getParameter(EMAIL));
        account.setCity(request.getParameter(CITY));
        account.setNpa(Integer.valueOf(request.getParameter(NPA)));
        account.setStreet(request.getParameter(STREET));
        account.setFirstName(request.getParameter(FIRST_NAME));
        account.setLastName(request.getParameter(LAST_NAME));
    }
}
