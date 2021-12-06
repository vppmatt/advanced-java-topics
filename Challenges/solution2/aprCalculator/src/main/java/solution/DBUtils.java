package solution;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

public class DBUtils {

    private SqlSession session;

    public DBUtils() throws ClassNotFoundException {
        setUpFactory();
        createDB();

    }

    public void close() {
        session.close();
    }

    private void setUpFactory() throws ClassNotFoundException {

            Class.forName("org.h2.Driver");

            DataSource dataSource
                    = new PooledDataSource("org.h2.Driver", "jdbc:h2:mem:ratesDB;DB_CLOSE_DELAY=-1", "sa", "");
            Environment environment = new Environment("ProofOfConcept", new JdbcTransactionFactory(), dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(LoanRateMapper.class);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(configuration);
            session = sqlSessionFactory.openSession();

    }

    private void createDB() {

            LoanRateMapper mapper = session.getMapper(LoanRateMapper.class);
            mapper.createTable();
            System.out.println("DB table created");

    }

    public void save(int id, double rate) {
            LoanRateMapper mapper = session.getMapper(LoanRateMapper.class);
            int result = mapper.saveLoan(new LoanRate(id, rate));
    }

    public void printAllSavedLoanRates() {

            LoanRateMapper mapper = session.getMapper(LoanRateMapper.class);
            List<LoanRate> loanRates = mapper.selectAll();
            loanRates.forEach(System.out::println);

    }
}
