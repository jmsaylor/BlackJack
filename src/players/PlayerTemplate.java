package players;

import java.util.UUID;

public abstract class PlayerTemplate {
    private UUID id;

    public PlayerTemplate() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

}
