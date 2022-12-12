--- level---
Create table rank(
                     id  NUMBER(4) primary key NOT NULL,
                     code nvarchar2(50) NOT NULL,
                     description nvarchar2(50),
                     is_delete INTEGER NOT NULL
);

---- status_job ---
Create table status_job(
                           id NUMBER(4) primary key,
                           code nvarchar2(50),
                           description nvarchar2(50),
                           is_delete INTEGER NOT NULL
);

Create table status_job_register(
                                    id NUMBER(4) primary key,
                                    code nvarchar2(50),
                                    description nvarchar2(50),
                                    is_delete INTEGER NOT NULL
);

----academic level (trình độ học vấn)----
Create table academic_level(
                               id NUMBER(4) primary key,
                               code nvarchar2(50), --- trình độ học vấn---
                               description nvarchar2(50),
                               is_delete INTEGER NOT NULL
);

---working_form (hình thức làm việc fulltime, parttime, intern)---
CREATE TABLE working_form(
                             id NUMBER NOT NULL,
                             code VARCHAR(50) NOT NULL,
                             description  VARCHAR(100) NOT NULL,
                             is_delete INTEGER NOT NULL,
                             PRIMARY KEY(id )
);

CREATE TABLE users(
                      id NUMBER(4) NOT NULL  PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      user_name VARCHAR(20) NOT NULL UNIQUE,
                      email VARCHAR(50) NOT NULL UNIQUE ,
                      password VARCHAR(60) NOT NULL,
                      phone_number VARCHAR(20) NOT NULL UNIQUE,
                      home_town VARCHAR(100) ,
                      gender VARCHAR(100) ,
                      birth_day DATE NOT NULL ,
                      avatar VARCHAR(100),
                      activate INTEGER NOT NULL,
                      is_delete INTEGER NOT NULL
);

Create table otp(
                    id NUMBER(4) primary key,
                    code varchar(20) NOT NULL,
                    issue_at DATE NOT NULL, --thời gian sống của mã otp--
                    user_id  NUMBER(4)  NOT NULL,
                    CONSTRAINT fk_user_otp FOREIGN KEY(user_id) REFERENCES users(id)
);

----file (hồ sơ)----
Create table profiles(
                         user_id  NUMBER(4) PRIMARY KEY,
                         skill varchar(50),
                         number_years_experience NUMBER(4),  ---số năm kinh nghiệm ---
                         academic_name_id number(4),
                         desired_salary varchar(50), --mức lương mong muốn ---
                         desired_working_address varchar(50), --địa chỉ làm việc mong muốn ---
                         desired_working_form varchar(50), --hình thức làm việc mong muốn
                         is_delete INTEGER NOT NULL,
                         CONSTRAINT fk_user_profile    FOREIGN KEY (user_id)    REFERENCES users (id),
                         CONSTRAINT fk_academic_level    FOREIGN KEY (academic_name_id)    REFERENCES academic_level(id)
);

CREATE TABLE roles(
                     id NUMBER(4) NOT NULL,
                     code VARCHAR(20) NOT NULL UNIQUE,
                     description VARCHAR(50) NOT NULL,
                     is_delete INTEGER NOT NULL,
                     PRIMARY KEY(id)
);

CREATE TABLE permisstion(
                            user_id NUMBER(4) NOT NULL,
                            role_id NUMBER(4) NOT NULL,
                            PRIMARY KEY(user_id, role_id),
                            CONSTRAINT fk_user    FOREIGN KEY (user_id)    REFERENCES users (id),
                            CONSTRAINT fk_role    FOREIGN KEY (role_id)    REFERENCES roles(id)
);

CREATE TABLE job(
                    id NUMBER(4) PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    job_position_id NUMBER(4) NOT NULL,
                    number_experience VARCHAR(100) NOT NULL,
                    working_form_id  NUMBER(4) NOT NULL,
                    address_work VARCHAR(100) NOT NULL,
                    academic_level_id NUMBER(4) NOT NULL,
                    rank_id NUMBER(4) NOT NULL,
                    qty_person INTEGER NOT NULL,
                    start_recruitment_date DATE NOT NULL,
                    due_date DATE NOT NULL,
                    skills VARCHAR(100) NOT NULL,
                    description VARCHAR(100) NOT NULL,
                    interest VARCHAR(100) NOT NULL,
                    job_requirement VARCHAR(100) NOT NULL,
                    salary_max INTEGER NOT NULL,
                    salary_min INTEGER NOT NULL,
                    contact_id NUMBER(4) NOT NULL,
                    create_id  NUMBER(4) NOT NULL,
                    create_date DATE NOT NULL,
                    update_id  NUMBER(4) NOT NULL,
                    update_date DATE NOT NULL,
                    status_id NUMBER(4) NOT NULL,
                    views  INTEGER ,
                    is_delete INTEGER NOT NULL,

                    CONSTRAINT fk_working_form FOREIGN KEY (working_form_id)  REFERENCES working_form(id),
                    CONSTRAINT fk_academic_level_job FOREIGN KEY (academic_level_id)  REFERENCES academic_level (id),
                    CONSTRAINT fk_rank FOREIGN KEY (rank_id)  REFERENCES rank (id),
                    CONSTRAINT fk_contact FOREIGN KEY (contact_id)  REFERENCES users (id),
                    CONSTRAINT fk_update FOREIGN KEY (update_id)  REFERENCES users (id),
                    CONSTRAINT fk_create FOREIGN KEY (create_id)  REFERENCES users (id),
                    CONSTRAINT fk_status_job FOREIGN KEY (status_id)  REFERENCES status_job(id),
                    CONSTRAINT FK_JOB_POSITION FOREIGN KEY (job_position_id) REFERENCES JOB_POSITION(id)
);

CREATE TABLE jobs_register(
                              job_id NUMBER NOT NULL,
                              user_id NUMBER NOT NULL,
                              date_register DATE NOT NULL ,
                              date_interview DATE,
                              method_interview VARCHAR(50),
                              address_interview VARCHAR(50),
                              status_id NUMBER NOT NULL,
                              reason VARCHAR(50),
                              cv_file VARCHAR(50) NOT NULL,
                              media_type VARCHAR(50)NOT NULL,
                              is_delete INTEGER NOT NULL,
                              PRIMARY KEY(job_id, user_id ),

                              CONSTRAINT fk_job_register  FOREIGN KEY (job_id)   REFERENCES job(id),
                              CONSTRAINT fk_user_register   FOREIGN KEY (user_id)    REFERENCES users (id),
                              CONSTRAINT fk_reg_status FOREIGN KEY (status_id)  REFERENCES status_job_register(id)
);

--type_notifications--
Create table type(
                     id NUMBER(4) primary key,
                     code nvarchar2(50),
                     description nvarchar2(50),
                     is_delete INTEGER NOT NULL
);

Create table job_position(
                     id NUMBER(4) primary key,
                     code nvarchar2(50),
                     description nvarchar2(50),
                     is_delete INTEGER NOT NULL
);

CREATE TABLE notifications(
                              id NUMBER NOT NULL,
                              sender_id  NUMBER NOT NULL,
                              receiver_id  NUMBER NOT NULL,
                              create_date DATE NOT NULL,
                              content  varchar(200) NOT NULL,
                              res_id NUMBER NOT NULL, -- là job_id hoặc là job_reg_id;
                              type_id NUMBER NOT NULL,
                              is_delete INTEGER NOT NULL,
                              PRIMARY KEY(id )   ,
                              CONSTRAINT fk_type FOREIGN KEY (type_id)  REFERENCES type (id),
                              CONSTRAINT fk_sender_id  FOREIGN KEY (sender_id)   REFERENCES users (id),
                              CONSTRAINT fk_receiver_id   FOREIGN KEY (receiver_id)    REFERENCES users (id)
);

CREATE TABLE company(
                        id NUMBER NOT NULL,
                        name  VARCHAR(200) NOT NULL,
                        email VARCHAR(100) NOT NULL,
                        hotline VARCHAR(100) NOT NULL,
                        date_incoporation DATE NOT NULL, -- ngày thành lập công ty
                        tax_code VARCHAR(100) NOT NULL,
                        tax_date DATE NOT NULL,-- Ngày cấp mã số thuế
                        tax_place VARCHAR(50) NOT NULL, -- Nơi cấp mã số thuế
                        head_office VARCHAR(50) NOT NULL, -- trụ sở chính
                        number_staff INTEGER NOT NULL, -- số lượng nhân viên
                        link_web VARCHAR(50) NOT NULL, -- trụ sở chính
                        description VARCHAR(50) NOT NULL, -- mô tả công ty
                        avatar VARCHAR(50) NOT NULL, -- ảnh đại diện
                        backdrop_img VARCHAR(50) NOT NULL, -- ảnh bìa
                        is_delete INTEGER NOT NULL,
                        PRIMARY KEY(id )
);



-- dữ liệu////
--------------------------------------------------------
--  File created - Tuesday-November-22-2022
--------------------------------------------------------
REM INSERTING into TEAM2.ACADEMIC_LEVEL
SET DEFINE OFF;
Insert into TEAM2.ACADEMIC_LEVEL (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'cao Đẳng','abc',0);
Insert into TEAM2.ACADEMIC_LEVEL (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'đại học','abc',0);
Insert into TEAM2.ACADEMIC_LEVEL (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'cử nhân','abc',0);
REM INSERTING into TEAM2.COMPANY
SET DEFINE OFF;
REM INSERTING into TEAM2.JOB
SET DEFINE OFF;
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (12,'Hà Nội','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('30-APR-22','DD-MON-RR'),0,'Giao tiếp tốt , đánh gold giỏi','Nhân viên kế toán',2,2,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),0,2,4,4,2,2,1,4,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (13,'HCM','Nằm',to_date('13-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('30-APR-22','DD-MON-RR'),0,'Java, Python','Dạy bơi',2,5,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),0,2,4,4,2,2,1,4,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (17,'HCM','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('22-APR-22','DD-MON-RR'),0,'Giao tiếp tốt , đánh gold giỏi','Lập trình',2,7,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),0,2,4,5,2,2,3,5,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (18,'Sài Gòn','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('22-APR-22','DD-MON-RR'),0,'Giao tiếp tốt , đánh gold giỏi','Nhân viên Bất động sản',2,7,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),45,2,4,4,2,2,2,4,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (20,'Lạng Sơn','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('14-APR-22','DD-MON-RR'),0,'Java, Python','Quản lý nhân sự',2,4,null,15,12,'Java, Python',to_date('12-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),5,2,4,5,3,1,2,5,2);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (19,'Poi AJD','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('22-APR-22','DD-MON-RR'),0,'Giao tiếp tốt , đánh gold giỏi','Diễn viên',2,7,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),0,2,4,4,2,2,2,4,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (14,'Bắc Giang','Nằm',to_date('13-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('15-APR-22','DD-MON-RR'),0,'Java, Python','Bán vàng mã',2,5,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),27,2,4,5,2,2,2,5,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (21,'ádad','ssss',to_date('03-JUL-22','DD-MON-RR'),'ádasdsa',to_date('22-JUL-22','DD-MON-RR'),0,'ádsadsa','ádasdsa',21,12,null,111,12,'22112',to_date('05-JUL-22','DD-MON-RR'),to_date('03-JUL-22','DD-MON-RR'),0,1,4,1,1,2,1,1,1);
Insert into TEAM2.JOB (ID,ADDRESS_WORK,BENEFITS,CREATE_DATE,DESCRIPTION,DUE_DATE,IS_DELETE,JOB_REQUIREMENT,NAME,NUMBER_EXPERIENCE,QTY_PERSON,REASON,SALARY_MAX,SALARY_MIN,SKILLS,START_RECRUITMENT_DATE,UPDATE_DATE,VIEWS,ACADEMIC_LEVEL_ID,CONTACT_ID,CREATE_ID,JOB_POSITION_ID,RANK_ID,STATUS_ID,UPDATE_ID,WORKING_FORM_ID) values (15,'Hà Nội','đi chơi, giải trí',to_date('11-APR-22','DD-MON-RR'),'Chụp ảnh đánh golf',to_date('22-APR-22','DD-MON-RR'),0,'Giao tiếp tốt , đánh gold giỏi','Nhân viên kinh doanh',2,7,null,31,20,'english,sport',to_date('19-APR-22','DD-MON-RR'),to_date('11-APR-22','DD-MON-RR'),0,2,4,4,2,2,2,4,1);
REM INSERTING into TEAM2.JOB_POSITION
SET DEFINE OFF;
Insert into TEAM2.JOB_POSITION (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'Test','anc',0);
Insert into TEAM2.JOB_POSITION (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'Dev','abc',0);
Insert into TEAM2.JOB_POSITION (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'Ba','abc',0);
Insert into TEAM2.JOB_POSITION (ID,CODE,DESCRIPTION,IS_DELETE) values (4,'Pm','abc',0);
REM INSERTING into TEAM2.JOB_REGISTER
SET DEFINE OFF;
Insert into TEAM2.JOB_REGISTER (ID,ADDRESS_INTERVIEW,CV_FILE,DATE_INTERVIEW,DATE_REGISTER,IS_DELETE,MEDIA_TYPE,METHOD_INTERVIEW,REASON,JOB_ID,STATUS_ID,USER_ID) values (6,null,'job.pdf',null,to_date('12-APR-22','DD-MON-RR'),0,null,null,null,14,2,7);
Insert into TEAM2.JOB_REGISTER (ID,ADDRESS_INTERVIEW,CV_FILE,DATE_INTERVIEW,DATE_REGISTER,IS_DELETE,MEDIA_TYPE,METHOD_INTERVIEW,REASON,JOB_ID,STATUS_ID,USER_ID) values (5,null,'job.pdf',null,to_date('11-APR-22','DD-MON-RR'),0,null,null,null,20,1,2);
Insert into TEAM2.JOB_REGISTER (ID,ADDRESS_INTERVIEW,CV_FILE,DATE_INTERVIEW,DATE_REGISTER,IS_DELETE,MEDIA_TYPE,METHOD_INTERVIEW,REASON,JOB_ID,STATUS_ID,USER_ID) values (4,null,'job.pdf',null,to_date('11-APR-22','DD-MON-RR'),0,null,null,null,14,1,2);
REM INSERTING into TEAM2.NOTIFICATIONS
SET DEFINE OFF;
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (12,null,to_date('12-APR-22','DD-MON-RR'),0,14,5,7,1);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (21,null,to_date('03-JUL-22','DD-MON-RR'),0,21,1,1,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (7,null,to_date('11-APR-22','DD-MON-RR'),0,18,1,1,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (5,null,to_date('11-APR-22','DD-MON-RR'),0,12,1,1,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (6,null,to_date('11-APR-22','DD-MON-RR'),0,17,1,1,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (8,null,to_date('11-APR-22','DD-MON-RR'),0,19,1,1,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (9,null,to_date('11-APR-22','DD-MON-RR'),0,14,5,2,1);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (10,null,to_date('11-APR-22','DD-MON-RR'),0,20,1,5,3);
Insert into TEAM2.NOTIFICATIONS (ID,CONTENT,CREATE_DATE,IS_DELETE,JOB_ID,RECEIVER_ID,SENDER_ID,TYPE_ID) values (11,null,to_date('11-APR-22','DD-MON-RR'),0,20,5,2,1);
REM INSERTING into TEAM2.OTP
SET DEFINE OFF;
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (8,'27881',1649732426476,8);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (6,'12875',1649728630389,6);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (7,'24790',1649731794553,7);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (9,'14542',1649732478530,9);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (41,'28185',1655715344293,41);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (68,'11253',1669105111863,62);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (1,'85849',1649673637577,1);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (2,'27551',1649674244797,2);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (3,'13358',1649675053739,3);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (4,'14624',1649684506619,4);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (5,'14164',1649675419074,5);
Insert into TEAM2.OTP (ID,CODE,ISSUE_AT,USER_ID) values (21,'19077',1652763395599,21);
REM INSERTING into TEAM2.PERMISSTION
SET DEFINE OFF;
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (1,1);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (2,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (3,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (4,2);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (5,2);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (6,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (7,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (8,2);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (9,2);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (21,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (41,3);
Insert into TEAM2.PERMISSTION (USER_ID,ROLE_ID) values (62,3);
REM INSERTING into TEAM2.PROFILES
SET DEFINE OFF;
Insert into TEAM2.PROFILES (ID,DESCRIPTION,DESIRED_SALARY,DESIRED_WORKING_ADDRESS,DESIRED_WORKING_FORM,IS_DELETE,NUMBER_YEARS_EXPERIENCE,SKILL,ACADEMIC_NAME_ID,USER_ID) values (2,'K gì là k thể','12','Hà dông','fulltime',0,5,'java,python,devop',2,3);
Insert into TEAM2.PROFILES (ID,DESCRIPTION,DESIRED_SALARY,DESIRED_WORKING_ADDRESS,DESIRED_WORKING_FORM,IS_DELETE,NUMBER_YEARS_EXPERIENCE,SKILL,ACADEMIC_NAME_ID,USER_ID) values (3,null,'14','Hanoi',null,0,null,'javaxxx',2,6);
Insert into TEAM2.PROFILES (ID,DESCRIPTION,DESIRED_SALARY,DESIRED_WORKING_ADDRESS,DESIRED_WORKING_FORM,IS_DELETE,NUMBER_YEARS_EXPERIENCE,SKILL,ACADEMIC_NAME_ID,USER_ID) values (1,'Cần cù bủ siêng năng','20','Thanh xuân','parttime',0,2,'Giao tiếp, ngoại ngữ',1,2);
Insert into TEAM2.PROFILES (ID,DESCRIPTION,DESIRED_SALARY,DESIRED_WORKING_ADDRESS,DESIRED_WORKING_FORM,IS_DELETE,NUMBER_YEARS_EXPERIENCE,SKILL,ACADEMIC_NAME_ID,USER_ID) values (4,'ádnnsadsa','15','Hà Nội',null,0,0,'Java, c++',2,7);
REM INSERTING into TEAM2.RANK
SET DEFINE OFF;
Insert into TEAM2.RANK (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'senior','abc',0);
Insert into TEAM2.RANK (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'fresher','abc',0);
Insert into TEAM2.RANK (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'junior','abc',0);
Insert into TEAM2.RANK (ID,CODE,DESCRIPTION,IS_DELETE) values (4,'master','abc',0);
REM INSERTING into TEAM2.ROLES
SET DEFINE OFF;
Insert into TEAM2.ROLES (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'ROLE_ADMIN','ROLE_ADMIN',0);
Insert into TEAM2.ROLES (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'ROLE_JE','ROLE_JE',0);
Insert into TEAM2.ROLES (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'ROLE_USER','ROLE_USER',0);
REM INSERTING into TEAM2.STATUS_JOB
SET DEFINE OFF;
Insert into TEAM2.STATUS_JOB (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'Chờ duyệt','chờ xét duyệt',0);
Insert into TEAM2.STATUS_JOB (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'đang tuyển','đang tuyển',0);
Insert into TEAM2.STATUS_JOB (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'chưa đăng tuyển','chưa đăng tuyển',0);
Insert into TEAM2.STATUS_JOB (ID,CODE,DESCRIPTION,IS_DELETE) values (4,'đã đóng','đã đóng',0);
Insert into TEAM2.STATUS_JOB (ID,CODE,DESCRIPTION,IS_DELETE) values (5,'đã từ chối','đã từ chối',0);
REM INSERTING into TEAM2.STATUS_JOB_REGISTER
SET DEFINE OFF;
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'Chờ duyệt','abc',0);
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'Chờ phỏng vấn','Chờ phỏng vấn',0);
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'Đang phỏng vấn','Đang phỏng vấn',0);
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (4,'Đã tuyển','Đã tuyển',0);
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (5,'Ứng viên bị từ chối','Ứng viên bị từ chối',0);
Insert into TEAM2.STATUS_JOB_REGISTER (ID,CODE,DESCRIPTION,IS_DELETE) values (6,'Ứng viên đã hủy ứng tuyển','Ứng viên đã hủy ứng tuyển',0);
REM INSERTING into TEAM2.TYPE
SET DEFINE OFF;
Insert into TEAM2.TYPE (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'Ứng tuyển','ứng tuyển',0);
Insert into TEAM2.TYPE (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'Hủy ứng tuyển','hủy ứng tuyển',0);
Insert into TEAM2.TYPE (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'job add','job add',0);
REM INSERTING into TEAM2.USERS
SET DEFINE OFF;
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (8,null,null,'anhanhss97@gmail.com',0,null,null,0,0,'je4','$2a$10$2xi.3c3m8MyNQktLVW4D/O3/w.cDMsoP.JazhPYQRNrEQfJsdOW/e','0961130572','je4');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (62,null,null,'lehieu1112001@gmail.com',0,null,null,1,0,'test01','$2a$10$jMmHOrMSeQ7rm8j39q3n8O2lw6J3yOglan/AbyZWf1cvz.NcZq/JW','0987342233','test01');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (6,'avatar2.png',to_date('06-APR-22','DD-MON-RR'),'soncv97@gmail.com',0,'Fulltime','Nam Dinh',1,0,'DO PHUC DAI','$2a$10$fkWa2Kx99OTTXY2ov/3f.ub9iczbVzdl3qisqFEd6yhXXocw.WbCe','0961120561','user3');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (7,'avatar1.png',null,'daido7031@gmail.com',0,'Parttime','Nam Dinh',0,0,'user4','$2a$10$LSB0ANFE5i73DS6AeSoXGODBhjngAjxxjHWmVGhhQHAJnEvP7PaCi','0961130564','user4');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (9,null,null,'anhanhss971@gmail.com',0,null,null,1,0,'je4','$2a$10$kSOm40Zf1yaEfxHzfDhkZ.sR0NbH9BwxmYbvb.kXbMezithYhU.yG','0961130571','je41');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (41,'string',null,'lehieu@gmail.com',0,'string','string',0,0,null,'$2a$10$T8/78MnGCzmF6n4jBVy0LeXJn4FKzxA9pEumrHPOrCz5iFUZTjoJK','string','string');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (1,'admin.png',to_date('04-SEP-22','DD-MON-RR'),'zexaldai@gmail.com',0,'Nữ','Sài gòn',1,0,'admin','$2a$10$fJuYX0sfwznWPKW0Z2k9QOlFaC50m2pYg28pu.ynt9Ze1qViqYjBW','0961130568','admin');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (2,'avatar2.png',to_date('12-MAR-09','DD-MON-RR'),'thanhlonghoangdao1101@gmail.com',0,'nam','Hà nội',1,0,'user1','$2a$10$fJuYX0sfwznWPKW0Z2k9QOlFaC50m2pYg28pu.ynt9Ze1qViqYjBW','0958454543','user1');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (3,'avatar2.png',to_date('12-MAR-09','DD-MON-RR'),'hieulevan1112001@gmail.com',0,'nữ','Bắc ninh',1,0,'user2','$2a$10$7ptbCBT9ecFz5o54zWtRxu/BTWJeauypEKqN7KGzWu3zcl12Dvx/G','0889324782','user2');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (4,'avatar1.png',to_date('02-SEP-22','DD-MON-RR'),'lehieu112001@gmail.com',0,null,'Lạng Sơn',1,0,'je1','$2a$10$zZSEu1BvWEJRSlSiwd1M.ePyuZ242y6SHOUf6zAbdDhJsJZcmE1zi','0983348234','je1');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (5,'avatar1.png',to_date('15-APR-21','DD-MON-RR'),'chiennesta25@gmail.com',0,'Nữ','Cao Bằng',1,0,'je2','$2a$10$yKbCKttIyjprhQcj5HuhkOftoSdnXZ1P7MSQL4BSGTe472err5l2W','0909038323','je2');
Insert into TEAM2.USERS (ID,AVATAR,BIRTH_DAY,EMAIL,FIRST_TIME_LOGIN,GENDER,HOME_TOWN,ACTIVATE,IS_DELETE,NAME,PASSWORD,PHONE_NUMBER,USER_NAME) values (21,null,null,'xuanpham1793@gmail.com',0,null,null,0,0,'fhfghfhfg','$2a$10$kCk33t0vdtHY5a4SM8ON3e1V9o1s4aUMtHbpj/KPXWZxJMWCkDb2y','0923456987','fghbgfhfghfgh');
REM INSERTING into TEAM2.WORKING_FORM
SET DEFINE OFF;
Insert into TEAM2.WORKING_FORM (ID,CODE,DESCRIPTION,IS_DELETE) values (1,'fulltime','loi',0);
Insert into TEAM2.WORKING_FORM (ID,CODE,DESCRIPTION,IS_DELETE) values (2,'part time','nua thoi gian',0);
Insert into TEAM2.WORKING_FORM (ID,CODE,DESCRIPTION,IS_DELETE) values (3,'freelancer','abc',0);
Insert into TEAM2.WORKING_FORM (ID,CODE,DESCRIPTION,IS_DELETE) values (4,'intern','abc',0);
