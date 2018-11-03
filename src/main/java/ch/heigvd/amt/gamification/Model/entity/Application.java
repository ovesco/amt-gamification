package ch.heigvd.amt.gamification.Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
public class Application extends BaseEntity<Long> {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private UUID apiKey;

    @Column
    private UUID apiSecret;

    @Column
    private Date creation;

    @ManyToOne
    private Developer developer;

    public Application() {

        creation = new Date();
        apiKey = UUID.randomUUID();
        apiSecret = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    // Automatically set
    public Date getCreation() {
        return creation;
    }

    public UUID getApiKey() {
        return apiKey;
    }

    public UUID getApiSecret() {
        return apiSecret;
    }
}
