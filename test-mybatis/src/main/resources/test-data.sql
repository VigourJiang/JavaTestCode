--创建一个员工基本信息表
   create  table "EMPLOYEES"(
       "EMPLOYEE_ID" NUMBER(6) not null,
      "FIRST_NAME" VARCHAR2(20),
      "LAST_NAME" VARCHAR2(25) not null,
      "EMAIL" VARCHAR2(25) not null unique,
      "SALARY" NUMBER(8,2),
       constraint "EMP_EMP_ID_PK" primary key ("EMPLOYEE_ID")
   );
   comment on table EMPLOYEES is '员工信息表';
   comment on column EMPLOYEES.EMPLOYEE_ID is '员工id';
   comment on column EMPLOYEES.FIRST_NAME is 'first name';
   comment on column EMPLOYEES.LAST_NAME is 'last name';
   comment on column EMPLOYEES.EMAIL is 'email address';
   comment on column EMPLOYEES.SALARY is 'salary';

   --添加数据
insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (100, 'Steven', 'King', 'SKING', 24000.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (101, 'Neena', 'Kochhar', 'NKOCHHAR', 17000.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (102, 'Lex', 'De Haan', 'LDEHAAN', 17000.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (103, 'Alexander', 'Hunold', 'AHUNOLD', 9000.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (104, 'Bruce', 'Ernst', 'BERNST', 6000.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (105, 'David', 'Austin', 'DAUSTIN', 4800.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (106, 'Valli', 'Pataballa', 'VPATABAL', 4800.00);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, SALARY)
values (107, 'Diana', 'Lorentz', 'DLORENTZ', 4200.00);
commit;