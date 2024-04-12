package com.designfox.hyremapjobms.job;

import com.designfox.hyremapjobms.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobCompanyDTO> findAll();
    Job createJob(Job job);
    JobCompanyDTO findById(Long id);
    boolean deleteJobById(Long id);
    Job updateJobById(Long id, Job body);
}
