package ch.heigvd.amt.gamification.services.session;

import javax.ejb.Local;

@Local
public interface IFlashBagLocal {

    public IFlashMessage[] getMessages();

    public void success(String message);

    public void info(String message);

    public void warning(String message);

    public void danger(String message);

    interface IFlashMessage {

        public String getType();

        public String getMessage();
    }
}
