package com.designfox.hyremapjobms.dto;

import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;

public class JobCompanyDTO {
    private Job job;
    private Company company;

    public JobCompanyDTO() {
    }

    public JobCompanyDTO(Job job, Company company) {
        this.job = job;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}
