import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@javax.persistence.Entity
@javax.persistence.Table(name = "client_table")


public class Client {
    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

