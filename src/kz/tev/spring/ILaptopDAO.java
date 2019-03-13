package kz.tev.spring;

import java.util.List;
import javax.sql.DataSource;

public interface  ILaptopDAO {
    
    void setDataSource(DataSource ds); // Установка связи с данныими

    void insert(Laptop notebook); // Вставка новой записи

    void append(String name, int diagonal, String cpu, String video, int price); // Добавление новой записи

    void deleteByLastName(String name); // Удаление записи по модели

    void delete(String name, String cpu); // Удаление записи с указанной моделью и процессором

    void deleteAll(); // Удаление всех запией

    void update(String oldName, String newName); // Изменение записей в таблице

    Laptop findByAge(int id); // Получение записи с заданным возрастом

    List<Laptop> findByFirstName(String name); // Получение записей с заданной моделью

    List<Laptop> select(String name, String cpu); // Получение записей с заданной моделью и процессором

    List<Laptop> selectAll(); // Получение всех записей
}
