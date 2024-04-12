package com.designfox.hyremapjobms.job.impl;

import com.designfox.hyremapjobms.dto.JobCompanyDTO;
import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;
import com.designfox.hyremapjobms.job.JobRepository;
import com.designfox.hyremapjobms.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobCompanyDTO> findAll() {
        return this.jobRepository
                .findAll()
                .stream()
                .map(this::mergeCompanyInJob)
                .collect(Collectors.toList());
    }

    private JobCompanyDTO mergeCompanyInJob(Job job){
        if(job==null) return null;
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:5051/company/v1/"+job.getCompanyId(), Company.class);
        return new JobCompanyDTO(job, company);
    }

    @Override
    public Job createJob(Job job) {
//        if(job.getCompany() == null) return null;
        Long cid = job.getCompanyId();
        if(cid == null) return null;
//        boolean companyExists = this.companyService.companyExistsById(cid);
//        if(!companyExists) return null;
        job.setId(null);
        jobRepository.save(job);
        return job;
    }

    @Override
    public JobCompanyDTO findById(Long id) {
        Job foundJob = jobRepository.findById(id).orElse(null);
        return this.mergeCompanyInJob(foundJob);
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
