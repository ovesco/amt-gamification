package ch.heigvd.amt.gamification.Model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
    @NamedQuery(name = "Application.findByDeveloper", query = "Select a from Application a Where a.account.id = :id"),
    @NamedQuery(name = "Application.countForDeveloper", query = "Select COUNT(a) from Application a Where a.account.id = :id")
})
public class Application extends BaseEntity<Long> {

    private String name;

    private String description;

    private UUID apiKey;

    private UUID apiSecret;

    private Date creation;

    @ManyToOne
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
