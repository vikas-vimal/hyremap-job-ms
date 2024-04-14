package com.designfox.hyremapjobms.mapper;

import com.designfox.hyremapjobms.dto.JobCompanyDTO;
import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;

public class JobMapper {
    public static JobCompanyDTO mapCompanyToJob(Job job, Company company){
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setCompanyId(job.getCompanyId());
        jobCompanyDTO.setCompany(company);
        return  jobCompanyDTO;
    }
}
