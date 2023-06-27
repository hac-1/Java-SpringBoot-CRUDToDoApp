package com.example.springlearn.ToDo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@Validated // Used to ensure @Validated is allowed inside class
@RequestMapping("/todo")
public class ToDoController {

    // LEARNING METHODS - USED FOR LEARNING PURPOSES - DO NOT PLAY ACTIVE ROLE IN
    // THE APPLICATION

    // USE REQUEST MAPPING WHEN YOU HAVE TO SPECIFY SOME CONSTRAINTS ELSE YOU CAN
    // USE OTHER ANNOTATIONS
    @RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
    public String toDoHomePage() {
        return "Hello this is a todo application";
    }

    @PostMapping("/infov2")
    @GetMapping("/infov2") // Get Mapping wont work here
    public String toDoHomePageV2() {
        return "Hello this is a todo application";
    }

    // This function would display the id given in params
    @GetMapping("/testdisplaygivenid/{id}")
    public String toDotestdisplaygivenid(@PathVariable String id) {
        return "Get Method with id " + id;
    }

    // this function would return the json body back
    @PostMapping("/checkinput")
    public ResponseEntity<String> toDoReturnBody(@RequestBody String jsoString) {
        return ResponseEntity.ok(jsoString);
    }

    @PostMapping("/validator")
    public ResponseEntity<ToDo> toDosampleValidator(@Valid @RequestBody ToDo taskDetails) {
        // @Valid Annontaion is used to enforce the Validation
        // @RequestBody is used to to access the request body and put it of type given\
        // HOWEVER validation wont take place as the ToDo.java object has Validator
        // groups and hence this would not validate
        // For validation Use @Validated(group(such as Onupdate).class) to ensure
        // validation
        return ResponseEntity.ok(taskDetails);
    }

    // --------------------------------------------------
    // APPLICATION METHODS
    @Autowired
    private ToDoService toDoService;

    // POST MAPPING
    @PostMapping("/create")
    @Validated(OnCreate.class) // This will ensure that during validation - validators with groups with
                               // OnCreate.class will be used
                               // If OnUpdate.class is given only those validators with it will be validated
    public ResponseEntity<String> toDoPost(@Valid @RequestBody ToDo taskDetails) {
        // @Valid Annontaion is used to enforce the Validation
        // @RequestBody is used to to access the request body and put it of type given
        if (toDoService.writeRecord(taskDetails)) {
            return ResponseEntity.ok("Done. URI:"+ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(taskDetails.getId()).toUri());
        } else {
            return new ResponseEntity<>("Unable to Create", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/read")
    public ResponseEntity<List<ToDo>> toDoGet() {
        return ResponseEntity.ok(toDoService.getAllRecords());
    }

    @PatchMapping("/update")
    @Validated(OnUpdate.class) // This will ensure that during validation - validators with groups with
                               // OnUpdate.class will be used
                               // If OnCreate.class is given only those validators with it will be validated
    public ResponseEntity<String> toDoupdate(@Valid @RequestBody ToDo taskDetails, @RequestParam int id) {
        // @ RequestParam is used to get values such as id from url like
        // localhost:8080/todo/update?id=2
        if (toDoService.updateRecord(id, taskDetails)) {
            return ResponseEntity.ok("Done");
        } else {
            return new ResponseEntity<>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> toDoDelete(@PathVariable int id) {
        // @ RequestParam is used to get values such as id from url like
        // localhost:8080/todo/delete/102
        if (toDoService.deleteRecord(id)) {
            return ResponseEntity.ok("Done");
        } else {
            return new ResponseEntity<>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // --------------------------------------------------
    // METHODS THAT CAN BE USEFUL ELESEWHERE

    // BELOW FUNCTION HANDLES METHOD ARGUEMENT NOT VALID EXCEPTION RAISED WHEN
    // VALIDATION FAILS IN /create METHOD ABOVE

    @ResponseStatus(HttpStatus.BAD_REQUEST) // Annontation is used to handle as to when the below code is to be excuted
    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class }) // Annotation is
                                                                                                     // used to handle
                                                                                                     // exception for
                                                                                                     // given
    // exception class type
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
