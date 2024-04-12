package com.designfox.hyremapjobms.job.impl;

import com.designfox.hyremapjobms.job.Job;
import com.designfox.hyremapjobms.job.JobRepository;
import com.designfox.hyremapjobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobsDatabase = new ArrayList<>();
//    private Long nextId = 1L;
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return this.jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
//        if(job.getCompany() == null) return null;
//        Long cid = job.getCompany().getId();
//        if(cid == null) return null;
//        boolean companyExists = this.companyService.companyExistsById(cid);
//        if(!companyExists) return null;
        job.setId(null);
        jobRepository.save(job);
        return job;
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Job updateJobById(Long id, Job body) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        System.out.println(jobOptional.isPresent());
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setId(id);
            job.setTitle(body.getTitle());
            job.setDescription(body.getDescription());
            job.setMinSalary(body.getMinSalary());
            job.setMaxSalary(body.getMaxSalary());
            job.setLocation(body.getLocation());
            jobRepository.save(job);
            return job;
        }
        return null;
    }

}
