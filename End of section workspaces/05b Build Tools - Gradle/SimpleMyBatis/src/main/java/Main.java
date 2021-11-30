import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

public class Main {

    private static SqlSessionFactory sqlSessionFactory;

    public static void setUpFactory() throws ClassNotFoundException {
        if(sqlSessionFactory == null) {
            Class.forName("org.h2.Driver");

            DataSource dataSource
                    = new PooledDataSource("org.h2.Driver", "jdbc:h2:mem:customerDB", "sa", "");
            Environment environment = new Environment("ProofOfConcept", new JdbcTransactionFactory(), dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(CustomerMapper.class);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = builder.build(configuration);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Customer c1 = new Customer(1,"James Sizzler", 34);
        Customer c2 = new Customer(2, "Phillipa Wheezer", 28);
        Customer c3 = new Customer(3, "Andrea Greezer", 31);

        setUpFactory();
        try(SqlSession session = sqlSessionFactory.openSession()) {
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            customerMapper.createTable();
            customerMapper.saveCustomer(c1);
            customerMapper.saveCustomer(c2);
            customerMapper.saveCustomer(c3);
            Customer foundCustomer = customerMapper.find(2);
            System.out.println(foundCustomer);
            List<Customer> customers = customerMapper.selectCustomers();
            System.out.println(customers);
        }
    }
}
