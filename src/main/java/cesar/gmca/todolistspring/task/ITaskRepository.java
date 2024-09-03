package cesar.gmca.todolistspring.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


public interface ITaskRepository  extends JpaRepository<TaskModel, UUID>{
    List<TaskModel>findByIdUser(UUID idUser);
//    TaskModel findByIdAndByIdUser(UUID id, UUID idUser);
}
