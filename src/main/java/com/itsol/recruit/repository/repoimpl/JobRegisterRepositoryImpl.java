package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.JobRegisterPaginationDto;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.StatusJobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.BaseRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.repository.repoext.JobRegisterRepositoryExt;
import com.itsol.recruit.utils.SqlReader;
import com.itsol.recruit.web.vm.SearchJobRegisterVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JobRegisterRepositoryImpl extends BaseRepository implements JobRegisterRepositoryExt {

    private long totalPage = 0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusJobRegisterRepository statusJobRegisterRepository;

    @Autowired
    private JobRepository jobRepository;


    @Override
    public JobRegisterPaginationDto search(SearchJobRegisterVM searchJobRegisterVM, String orderName, Integer pageNumber, Integer pageSize) {
        try{
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_MODULE, "searchJobRegister");
            Map<String, Object> parameters = new HashMap<>();
            if (!ObjectUtils.isEmpty(searchJobRegisterVM.getName())) {
                String name=searchJobRegisterVM.getName().toUpperCase();
                query += " and UPPER(users.name) like :p_name";
                parameters.put("p_name","%"+name+"%");
            }
            if (!ObjectUtils.isEmpty(searchJobRegisterVM.getStatusRegisterId())) {
                query += " and job_register.status_id = :p_status_id";
                parameters.put("p_status_id",searchJobRegisterVM.getStatusRegisterId());
            }


            Integer p_startrow;
            Integer p_endrow;
            if(pageNumber==0)
            {
                p_startrow=0;
                p_endrow=p_startrow+pageSize;
            }
            else {
                p_startrow=pageSize*pageNumber+1;
                p_endrow=p_startrow+pageSize-1;
            }

            query += " order by "+orderName+" DESC ),count_all as( select count (*) total from tempselect )," +
                    " paging as( select * from tempselect  where ROWNR between :p_startrow and :p_endrow " +
                    ") select p.*, c.total from paging p, count_all c ";
            parameters.put("p_startrow", p_startrow);
            parameters.put("p_endrow", p_endrow);
            JobRegisterPaginationDto jobPaginationDto = new JobRegisterPaginationDto();
            List<JobRegister> listJob = getNamedParameterJdbcTemplate().query(query, parameters, new JobRegisterMapper());
            if(listJob.isEmpty()){
                jobPaginationDto.setList(new ArrayList<>());
                jobPaginationDto.setTotalPage(0L);
            }
            else {
                jobPaginationDto.setList(listJob);
                jobPaginationDto.setTotalPage(totalPage);
            }

            return jobPaginationDto;
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    class JobRegisterMapper implements RowMapper<JobRegister> {

        @Override
        public JobRegister mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobRegister dto = new JobRegister();
            dto.setAddressInterview(rs.getString("address_interview"));
            dto.setDateInterview(rs.getDate("date_interview"));
            dto.setDateRegister(rs.getDate("date_register"));
            dto.setCv(rs.getString("cv_file"));
            dto.setMediaType(rs.getString("media_type"));
            dto.setMethodInterview(rs.getString("method_interview"));
            dto.setReason(rs.getString("reason"));
            dto.setIsDelete(rs.getBoolean("is_delete"));
            dto.setId(rs.getLong("id"));

            User user = new User();
            user.setId(rs.getLong("user_id"));
            dto.setUser(userRepository.findById(user.getId()).get());

            StatusJobRegister statusJobRegister = new StatusJobRegister();
            statusJobRegister.setId(rs.getLong("status_id"));
            StatusJobRegister newStatus = statusJobRegisterRepository.findById(statusJobRegister.getId()).get();
            dto.setStatusJobRegister(newStatus);


            Job job = new Job();
            job.setId(rs.getLong("job_id"));
            Job newJob = jobRepository.findById(job.getId()).get();
            dto.setJob(newJob);

            totalPage = rs.getLong("total");

            return dto;
        }
    }
}
