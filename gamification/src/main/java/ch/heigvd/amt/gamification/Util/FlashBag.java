package ch.heigvd.amt.gamification.Util;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlashBag {

    final static String MESSAGES_KEY = "_messages";

    private HttpSession session;

    public FlashBag(HttpSession session) {
        this.session = session;

        if(session.getAttribute(MESSAGES_KEY) == null)
            session.setAttribute(MESSAGES_KEY, new ArrayList<FlashMessage>());
    }

    public void success(String message) {
        addFlash("success", message);
    }

    public void info(String message) {
        addFlash("info", message);
    }

    public void warning(String message) {
        addFlash("warning", message);
    }

    public void danger(String message) {
        addFlash("danger", message);
    }

    public List<FlashMessage> getMessages() {

        ArrayList<FlashMessage> list = (ArrayList<FlashMessage>)session.getAttribute(MESSAGES_KEY);
        List<FlashMessage> result    = (ArrayList<FlashMessage>)list.clone();
        list.clear();

        return result;
    }

    private void addFlash(String type, String message) {
        ((List<FlashMessage>)session.getAttribute(MESSAGES_KEY)).add(new FlashMessage(type, message));
    }
}
