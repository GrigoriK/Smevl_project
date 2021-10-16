package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smevel.entity.Employee;
import smevel.entity.VacationLeave;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public interface VacationLeaveRepo extends JpaRepository<VacationLeave, UUID> {

    @Query("SELECT vl from VacationLeave vl  WHERE vl.vacationStartDate >= ?1 and vl.vacationEndDate  <= ?2")
    Collection<VacationLeave> findByDateRange(Date startDate, Date endDate);

    Collection<VacationLeave> findByEmployee(Employee employee);

    Collection<VacationLeave> findByEmployeeIn(Collection<Employee> employees);

}
