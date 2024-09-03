package cesar.gmca.todolistspring.task;

import cesar.gmca.todolistspring.utils.Utils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskCntroller {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){

        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        if (LocalDateTime.now().isAfter(taskModel.getStartAt()) ||
                LocalDateTime.now().isAfter(taskModel.getEndAt()) ||
                taskModel.getStartAt().isAfter(taskModel.getEndAt()) ){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data invalida");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(taskModel));

    }
@GetMapping("/")
public List<TaskModel> list(HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
}

@PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){

        var task = this.taskRepository.findById(id).orElse(null);
        var idUser = request.getAttribute("idUser");
        if (task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("no tarefa");
        }
        if (!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("usuario não é o dono dessa tarefa");

        }


        Utils.copyNonNullProperties(taskModel, task);


        return ResponseEntity.ok().body(this.taskRepository.save(task));
    }
}

