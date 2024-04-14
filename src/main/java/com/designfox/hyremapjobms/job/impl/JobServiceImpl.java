package com.designfox.hyremapjobms.job.impl;

import com.designfox.hyremapjobms.dto.JobDTO;
import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;
import com.designfox.hyremapjobms.job.JobRepository;
import com.designfox.hyremapjobms.job.JobService;
import com.designfox.hyremapjobms.mapper.JobMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobsList = this.jobRepository
                .findAll();
        return this.mergeCompaniesInJobs(jobsList);
    }

    private List<Company> fetchCompaniesByIds(List<Long> companyIds){
        if(companyIds.isEmpty()) return List.of();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("companyIds", companyIds);

        ResponseEntity<List<Company>> response = restTemplate.exchange("http://HYREMAP-COMPANY-MS:5051/company/v1/get-bulk",
                HttpMethod.POST,
                new HttpEntity<String>(jsonObject.toString(), headers),
                new ParameterizedTypeReference<List<Company>>() {
        });
        return response.getBody();
    }

    private List<JobDTO> mergeCompaniesInJobs(List<Job> jobsList){
        if(jobsList.isEmpty()) return List.of();
        Map<Long, Company> companiesMap = new HashMap<>();
        List<Long> companyIds = new ArrayList<>();
        jobsList.forEach(job->companyIds.add(job.getCompanyId()));

        List<Company> companies = this.fetchCompaniesByIds(companyIds);
        companies.forEach(com -> companiesMap.put(com.getId(), com));

        return jobsList.stream()
                .map(job->JobMapper.mapCompanyToJob(
                        job,
                        companiesMap.get(job.getCompanyId())
                        )
                )
                .collect(Collectors.toList());
    }

    private JobDTO mergeCompanyInJob(Job job){
        if(job==null) return null;
        Company company = restTemplate.getForObject("http://HYREMAP-COMPANY-MS:5051/company/v1/"+job.getCompanyId(), Company.class);
        return JobMapper.mapCompanyToJob(job, company);
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
    public JobDTO findById(Long id) {
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
