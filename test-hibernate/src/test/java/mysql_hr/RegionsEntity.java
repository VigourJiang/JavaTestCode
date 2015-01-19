package mysql_hr;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by vigour on 2014-8-16.
 */
@Entity
@Table(name = "REGIONS", catalog = "")
public class RegionsEntity {
    private Long regionId;
    private String regionName;
    private Set<CountriesEntity> countries;

    @Id
    @Column(name = "REGION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "REGION_NAME")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @OneToMany(mappedBy = "region")
    public Set<CountriesEntity> getCountries() {
        return countries;
    }

    void setCountries(Set<CountriesEntity> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionsEntity that = (RegionsEntity) o;

        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null)
            return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regionId != null ? regionId.hashCode() : 0;
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        return result;
    }
}
