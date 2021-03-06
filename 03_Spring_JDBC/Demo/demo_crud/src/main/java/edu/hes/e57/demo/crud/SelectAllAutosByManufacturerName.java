package edu.hes.e57.demo.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import edu.hes.e57.demo.domain.Auto;

/**
 * This Class selects all Autos from the DB for a specific Manufacturer 
 * by Manufacturer Name.  
 */
public class SelectAllAutosByManufacturerName extends MappingSqlQuery<Auto> {
    private static final String SQL_SELECT_ALL_AUTO_BY_MANUFACTURER_NAME = 
            "select id, manufacturer_id, model, year, msrp from auto where manufacturer_id = (select id from manufacturer where name = :manufacturer_name )";

    public SelectAllAutosByManufacturerName(DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_AUTO_BY_MANUFACTURER_NAME);
        super.declareParameter(new SqlParameter("manufacturer_name", Types.VARCHAR));
    }

    protected Auto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auto auto = new Auto();

        auto.setId(rs.getLong("id"));
        auto.setManufacturerId(rs.getLong("manufacturer_id"));
        auto.setModel(rs.getString("model"));
        auto.setYear(rs.getString("year"));
        auto.setMsrp(rs.getString("msrp"));
            
        return auto;
    }
}
