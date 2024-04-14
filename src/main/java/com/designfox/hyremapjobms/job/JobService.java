package com.designfox.hyremapjobms.job;

import com.designfox.hyremapjobms.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    Job createJob(Job job);
    JobDTO findById(Long id);
    boolean deleteJobById(Long id);
    Job updateJobById(Long id, Job body);
}
