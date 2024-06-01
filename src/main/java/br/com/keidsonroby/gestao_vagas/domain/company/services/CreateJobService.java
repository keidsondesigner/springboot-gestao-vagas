package br.com.keidsonroby.gestao_vagas.domain.company.services;

import br.com.keidsonroby.gestao_vagas.domain.company.entities.JobEntity;
import br.com.keidsonroby.gestao_vagas.domain.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
