with tempselect as(
    select job_register.*, rownum ROWNR
    from job_register, job, users, STATUS_JOB_REGISTER
    where 1=1
      and job_register.job_id=job.id
      and job_register.user_id=users.id
      and job_register.status_id = STATUS_JOB_REGISTER.id
      and job_register.is_delete =0