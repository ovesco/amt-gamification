package ch.heigvd.amt.gamification.services.session;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class FlashBag implements IFlashBagLocal {

    private List<IFlashMessage> messages = new ArrayList<>();

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

    @Override
    public IFlashMessage[] getMessages() {

        IFlashMessage[] result = (IFlashMessage[])messages.toArray();
        messages.clear();
        return result;
    }

    private void addFlash(String type, String message) {
        messages.add(new FlashMessage(type, message));
    }

    class FlashMessage implements IFlashMessage {

        private String type;

        private String message;

        public FlashMessage(String type, String message) {

            this.type = type;
            this.message = message;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
