package com.designfox.hyremapjobms.job;

import com.designfox.hyremapjobms.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job/v1")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> findOneById(@PathVariable Long id){
        JobDTO foundJob = jobService.findById(id);
        if(foundJob != null){
            return new ResponseEntity<>(foundJob, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        Job result = jobService.createJob(job);
        if(result!=null) {
            return new ResponseEntity<>("Job created successfully!", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Missing required fields!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deletedJob = jobService.deleteJobById(id);
        if(deletedJob){
            return ResponseEntity.ok("Job was deleted successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long id, @RequestBody Job body){
        Job updatedJob = jobService.updateJobById(id, body);
        if(updatedJob!=null){
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
