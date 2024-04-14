package com.designfox.hyremapjobms.dto;

import com.designfox.hyremapjobms.external.Company;
import com.designfox.hyremapjobms.job.Job;

public class JobCompanyDTO extends Job {
    private Company company;

    public JobCompanyDTO() {
    }

    public JobCompanyDTO(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
