/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.springdatajdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootApplication
@PropertySource({"secrets.properties"})
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

        try {
            // create employee table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            // nothing
        }

        // create a list of first and last names
        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Java Guru", "Spring Ninja")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // for each first & last name pair insert an Employee into the database
        for (Object[] name : splitUpNames) {
            jdbcTemplate.execute(
                    String.format("INSERT INTO employees(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        // query the database for Employees with first name Java
        jdbcTemplate
                .query(
                        "SELECT id, first_name, last_name FROM employees WHERE first_name = 'Java'",
                        (rs, rowNum) ->
                                new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
                // print each found employee to the console
                .forEach(employee -> System.out.println(employee.toString()));

        // truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE employees;");
        // delete the table
        jdbcTemplate.execute("DROP TABLE employees");

        doJobStuff();
    }

    private void doJobStuff() {
        final String INSERT_SQL = "INSERT INTO job_role (name, grade, salary) values (?,?,?)";
        //create a table
        jdbcTemplate.execute("""
                CREATE TABLE job_role
                  ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    grade VARCHAR(1) NOT NULL,
                    salary INT NOT NULL);""");

        //populate the table
        Object[][] jobArray = {{"CEO", "A", 10000}, {"Director", "B", 8000}, {"Head Of", "C", 5000}, {"Team Lead", "D", 3000}};

        jdbcTemplate.batchUpdate(INSERT_SQL, Arrays.asList(jobArray));

        //fetch some data from the table
        RowMapper<JobRole> jobRoleRowMapper = (rs, rownum) -> {
            return new JobRole(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("grade"),
                    rs.getInt("salary"));
        };
        List<JobRole> jobs = jdbcTemplate.query("SELECT * FROM job_role", jobRoleRowMapper);

        //print the data
        jobs.forEach(System.out::println);

        //clean up
        jdbcTemplate.execute("DROP TABLE job_role");

    }
}
