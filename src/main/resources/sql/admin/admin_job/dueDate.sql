with tempselect as( select job.*, rownum ROWNR from Job
inner join( select job.id, count(job.id) as countJob from job
inner join job_register on job.id = job_register.job_id
where job.status_id = 2
group by job.id)
 temp on temp.id = job.id where temp.countJob < job.qty_person
and job.due_date <= :p_number_date ),count_all as( select count (*) total from tempselect ),paging as
( select * from tempselect  where ROWNR between :p_startrow and :p_endrow) select p.*, c.total from paging p, count_all c