package hr;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by vigour on 2014-8-16.
 */
@Entity
@Table(name = "JOB_HISTORY",  catalog = "")
@IdClass(JobHistoryEntityPK.class)
public class JobHistoryEntity {
    private int employeeId;
    private Timestamp startDate;
    private Timestamp endDate;

    @Id
    @Column(name = "EMPLOYEE_ID")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "START_DATE")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobHistoryEntity that = (JobHistoryEntity) o;

        if (employeeId != that.employeeId) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
