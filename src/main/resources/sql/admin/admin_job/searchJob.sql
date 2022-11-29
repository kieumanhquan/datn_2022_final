with tempselect as(
    select job.*, rownum ROWNR
    from  job
    where job.is_delete =0