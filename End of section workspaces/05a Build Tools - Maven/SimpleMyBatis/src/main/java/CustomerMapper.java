import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customer WHERE id = #{id}")
    Customer find(@Param("id") int id);

    @Select("SELECT * FROM customer")
    List<Customer> selectCustomers();

    @Insert("INSERT INTO customer (id, name, age) values (#{customer.id}, #{customer.name}, #{customer.age})")
    void saveCustomer(@Param("customer") Customer customer);

    @Update("CREATE TABLE customer (`id` int, `name` varchar(255), `age` int)")
    void createTable();
}
