import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    private int cityId;
    private String cityName;
    @Id
    private Long id;

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public City() {

    }

    // Добавляем геттеры и сеттеры для связанной сущности Employee

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}