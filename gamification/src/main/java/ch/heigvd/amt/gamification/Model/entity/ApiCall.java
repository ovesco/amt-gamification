package ch.heigvd.amt.gamification.Model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ApiCall extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    private Application application;

    private Date date;

    public ApiCall() {
        this.date = new Date();
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
