package custom.dao;

import custom.domain.Process;
import com.google.common.base.Optional;
import custom.exception.DropwizardExampleException;
import custom.domain.ProcessTask;
import custom.domain.Task;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskDAO extends BaseDAO<Task> {

    private ProcessDAO processDAO;

    public TaskDAO(SessionFactory factory) {
        super(factory);
    }

    public void setProcessDAO(ProcessDAO processDAO) {
        this.processDAO = processDAO;
    }

    public List<Task> findAll() {
        return list(namedQuery("custom.domain.Task.findAll"));
    }

    public Task create(Task task) {
        assocWithProcess(task);
        return persist(task);
    }

    public Task update(Task task) {
        assocWithProcess(task);
        return persist(task);
    }

    public void delete(Task task) {
        currentSession().delete(task);
    }

    private void assocWithProcess(Task task) {
        for (ProcessTask assoc : task.getProcessAssoc()) {
            long processId = assoc.getProcess().getId();
            Optional<Process> processOptional = processDAO.findById(processId);
            if (!processOptional.isPresent()) {
                throw new DropwizardExampleException("Process with given Id doesn't exist");
            }
            assoc.setProcess(processOptional.get());
            assoc.setTask(task);
        }
    }
}
