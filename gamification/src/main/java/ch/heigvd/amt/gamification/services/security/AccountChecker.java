package ch.heigvd.amt.gamification.services.security;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Stateless
public class AccountChecker implements IAccountCheckerLocal {

    public static final String EMAIL        = "email";
    public static final String FIRST_NAME   = "firstName";
    public static final String LAST_NAME    = "lastName";
    public static final String STREET       = "street";
    public static final String NPA          = "npa";
    public static final String CITY         = "city";
    public static final String PASSWORD     = "password";

    @EJB
    private IAccountDAOLocal accountDAO;

    public void validateNotEmpty(Map<String, String> errors, Account account) {

        String[] fields             = new String[]{
            EMAIL, FIRST_NAME, LAST_NAME, STREET, NPA, CITY
        };

        // Check emptiness
        for(String field : fields) {
            try {
                String methodName = "get" + field.substring(0,1).toUpperCase() + field.substring(1);
                if(Account.class.getMethod(methodName).invoke(account) == null)
                    errors.put(field, "This field is mandatory");

            } catch (NoSuchMethodException e) {
                System.out.println("FAIL ACCOUNT CHECKER");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("FAIL ACCOUNT CHECKER");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("FAIL ACCOUNT CHECKER");
                e.printStackTrace();
            }
        }
    }

    public void checkEmailTaken(Map<String, String> errors, String email, String current) {
        if(email != null && !email.equals(current) && accountDAO.findByEmail(email) != null)
            errors.put(EMAIL, "Email already taken");
    }

    public void validatePassword(Map<String, String> errors, String password) {
        if(password == null || password.length() < 8)
            errors.put(PASSWORD, "Password must be at least 8 characters long");
    }

    public String getPassword(HttpServletRequest request) {
        return request.getParameter(PASSWORD);
    }

    public void populate(HttpServletRequest request, Account account) {

        String snpa = request.getParameter(NPA);
        Integer npa = snpa == null || snpa.isEmpty() ? null : Integer.valueOf(request.getParameter(NPA));

        account.setEmail(resultOrNull(request.getParameter(EMAIL)));
        account.setCity(resultOrNull(request.getParameter(CITY)));
        account.setNpa(npa);
        account.setStreet(resultOrNull(request.getParameter(STREET)));
        account.setFirstName(resultOrNull(request.getParameter(FIRST_NAME)));
        account.setLastName(resultOrNull(request.getParameter(LAST_NAME)));
    }

    private String resultOrNull(String item) {
        return item == null || item.isEmpty() ? null : item;
    }
}
