package utilities;

import exceptions.CurrencyNotFoundException;

import java.sql.*;

public final class DatabaseUtilsFinal {
    private final String DB_URL = "jdbc:h2:file:./testdb";

    public String version() {
        return "3.2.7";
    }

    public DatabaseUtilsFinal() {
        try {
            Class.forName("org.h2.Driver");
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialiseDatabase() {
        try(Connection conn = DriverManager.getConnection (DB_URL, "sa","");
            Statement st = conn.createStatement();) {
            st.executeUpdate("DROP TABLE IF EXISTS rates;");
            st.executeUpdate("CREATE TABLE rates (currency varchar(3), rate double);");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('USD', 1)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('GBP', 1.344)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('EUR', 1.1276)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('JPY', 0.0087)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('CHF', 1.0821)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('CNY', 0.1614)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('CAD', 0.7922)");
            st.executeUpdate("INSERT INTO rates (currency, rate) values ('MXN', 0.0481)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getRate(String currency) throws CurrencyNotFoundException {
        try(Connection conn = DriverManager.getConnection (DB_URL, "sa","");
            PreparedStatement ps = conn.prepareStatement("SELECT rate FROM rates where currency =  ?"))
        {
            ps.setString(1, currency);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            else {
                throw new CurrencyNotFoundException(currency);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
