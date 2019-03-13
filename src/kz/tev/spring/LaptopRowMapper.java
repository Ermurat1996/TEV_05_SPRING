package kz.tev.spring;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Person из считанной записи таблицы БД
 *
 */
public class LaptopRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        LaptopResultSetExtractor extractor = new LaptopResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class LaptopResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Laptop laptop = new Laptop();
            laptop.setId(rs.getInt(1));
            laptop.setName(rs.getString(2));
            laptop.setDiagonal(rs.getInt(3));
            laptop.setCpu(rs.getString(4));
            laptop.setVideo(rs.getString(5));
            laptop.setPrice(rs.getInt(6));
            return laptop;
        }
    }
}
