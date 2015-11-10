package custom.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "task_connections")
@NamedQueries({
        @NamedQuery(
                name = "custom.domain.TaskConnection.findAll",
                query = "SELECT tc FROM TaskConnection tc"
        )
})
public class TaskConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fromId", nullable = false)
    private Task from;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "toId", nullable = false)
    private Task to;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId", nullable = false)
    private Process process;

    @Column(name = "fromConnector", nullable = false)
    private String fromConnector;

    @Column(name = "toConnector", nullable = false)
    private String toConnector;

    public TaskConnection() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getFrom() {
        return from;
    }

    public void setFrom(Task from) {
        this.from = from;
    }

    public Task getTo() {
        return to;
    }

    public void setTo(Task to) {
        this.to = to;
    }

    public String getFromConnector() {
        return fromConnector;
    }

    public void setFromConnector(String fromConnector) {
        this.fromConnector = fromConnector;
    }

    public String getToConnector() {
        return toConnector;
    }

    public void setToConnector(String toConnector) {
        this.toConnector = toConnector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    // TODO equals and hashcode
}