package com.designfox.hyremapjobms.mapper;

import com.designfox.hyremapjobms.dto.JobDTO;
import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;

public class JobMapper {
    public static JobDTO mapCompanyToJob(Job job, Company company){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompanyId(job.getCompanyId());
        jobDTO.setCompany(company);
        return jobDTO;
    }
}
