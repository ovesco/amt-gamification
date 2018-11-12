package ch.heigvd.amt.gamification.services.session;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateful(name = "flashbag")
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

        System.out.println("AMOUNT OF MESSAGES: " + messages.size());
        IFlashMessage[] result = Arrays.copyOf(messages.toArray(), messages.size(), IFlashMessage[].class);
        messages.clear();
        return result;
    }

    private void addFlash(String type, String message) {
        System.out.println("FLASH ADDED: " + message);
        messages.add(new FlashMessage(type, message));
        System.out.println(messages.size());
    }

    class FlashMessage implements IFlashMessage {

        private String type;

        private String message;

        FlashMessage(String type, String message) {

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
