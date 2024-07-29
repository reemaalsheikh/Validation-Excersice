package com.example.q1validationexercise.Controller;

import com.example.q1validationexercise.Api.ApiResponse;
import com.example.q1validationexercise.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    // Create (add) a new project (ID,title , description , status, companyName)
    @PostMapping("/add")
    public ResponseEntity CreateProject (@Valid @RequestBody Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project successfully Added!"));
    }

    //• Display all project .
    @GetMapping("/get/projects")
    public ResponseEntity getProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    //• Update a project
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject (@PathVariable int index,@Valid @RequestBody Project project , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project successfully Updated!"));
    }


    //• Delete a project
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project successfully Deleted!"));
    }


    //• Change(update)the project status as done or not done
    @PutMapping("/update/status/{index}")
    public ResponseEntity changeProject(@PathVariable int index){
        if(projects.get(index).getStatus().equalsIgnoreCase("Not Started")){
            projects.get(index).setStatus("In Progress");
        }else if (projects.get(index).getStatus().equalsIgnoreCase("Progress")){
            projects.get(index).setStatus("Completed");
        }else if (projects.get(index).getStatus().equalsIgnoreCase("Completed")){
            projects.get(index).setStatus("Completed");
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project successfully Updated!"));

    }


    //• Search for a project by given title
    @GetMapping("/searchproj/{title}")
    public ResponseEntity getProjectBytitle(@PathVariable String title){
        for (Project project : projects) {
            if(project.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project not found at title:"+title));
    }


    //• Display All project for one company by companyName.
    @GetMapping("/pcn/{companyName}")
    public ResponseEntity getProjectsByCN (@PathVariable String companyName) {
        ArrayList<Project> projectbycompanyname = new ArrayList<>();
        for (Project project : projects) {
            if(project.getCompanyName().equalsIgnoreCase(companyName)){
                projectbycompanyname.add(project);
            }

        }
        return ResponseEntity.status(200).body(projectbycompanyname);
    }


}