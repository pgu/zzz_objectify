package pgu.shared;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Note implements IsSerializable {
    @Id
    private Long   id;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
