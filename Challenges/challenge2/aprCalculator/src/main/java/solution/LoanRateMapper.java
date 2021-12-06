/* package solution;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoanRateMapper {

    @Select("SELECT * FROM loanRates")
    List<LoanRate> selectAll();

    @Insert("INSERT INTO loanRates (id, rate) values (#{lr.id}, #{lr.rate})")
    int saveLoan(@Param("lr") LoanRate lr);

    @Update("CREATE TABLE loanRates (`id` int, `rate` decfloat)")
    void createTable();

    @Select("Select count(*) from loanRates")
    int howMany();
}
*/