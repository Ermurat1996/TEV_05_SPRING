package kz.tev.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Person
 *
 */
public class LaptopDAO implements ILaptopDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Laptop notebook) { // Реализация вставки новой записи

        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO LAPTOP (NAME, DIAGONAL, CPU, VIDEO, PRICE) VALUES(?,?,?,?,?)",
                new Object[]{notebook.getName(), notebook.getDiagonal(), notebook.getCpu(), notebook.getVideo(), notebook.getPrice()});
    }

    @Override
    public void append(String name, int diagonal, String cpu, String video, int price) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO LAPTOP (NAME, DIAGONAL, CPU, VIDEO, PRICE) VALUES(?,?,?,?,?)", new Object[]{name, diagonal, cpu, video, price});
    }

    @Override
    public void deleteByLastName(String name) {  // Реализация удаления записей по модели
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM LAPTOP WHERE NAME LIKE ?", new Object[]{'%' + name + '%'});
    }

    @Override
    public void delete(final String name, final String cpu) {  // Реализация удаления записей с указанной моделью и фамилией
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from LAPTOP where NAME= ? AND CPU = ?", new Object[]{name, cpu});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
   public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from LAPTOP");
    }

    @Override
    public void update(String oldName, String newName) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE LAPTOP SET NAME = ? WHERE NAME = ?", new Object[]{newName, oldName});
    }

    @Override
    public Laptop findByAge(int diagonal) { // Реализация поиска записи с заданной диагональю
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<Laptop> laptop = select.query("SELECT * FROM LAPTOP WHERE DIAGONAL = ?",
                new Object[]{diagonal}, new LaptopRowMapper());
        return laptop.size() > 0 ? laptop.get(0) : null;
    }

    @Override
    public List<Laptop> findByFirstName(String name) {  // Реализация поиска записей по модели
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM LAPTOP WHERE NAME LIKE ?";
        List<Laptop> laptops = select.query(sql, new Object[]{'%' + name + '%'}, new LaptopRowMapper());
        return laptops;
    }

    @Override
    public List<Laptop> select(String name, String cpu) {  // Реализация получения записей с заданной моделью и процессором
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from LAPTOP where NAME = ? AND CPU= ?",
                new Object[]{name, cpu}, new LaptopRowMapper());
    }

    @Override
    public List<Laptop> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from LAPTOP", new LaptopRowMapper());
    }
}
