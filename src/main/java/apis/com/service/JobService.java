package apis.com.service;

import java.util.List;

import apis.com.dto.JobDto;
import apis.com.entities.Job;

public interface JobService {

	public List<JobDto> getAllJobs();
	public JobDto getJobById(Long id);
	public JobDto createJob(JobDto jobDto);
	public JobDto updateJob(Long id, JobDto jobDto);
	public void deleteJob(Long id);
}
