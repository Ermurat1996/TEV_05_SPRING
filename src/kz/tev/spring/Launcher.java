package kz.tev.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            LaptopDAO laptopDAO = (LaptopDAO) context.getBean("notebookDAO"); // Загрузка бина доступа к таблице

            laptopDAO.deleteAll(); // Удаление всех записей
            
            Laptop laptop = new Laptop("Asus", 15, "Intel Core I7", "Nvidia", 200000); // Создание нового объекта таблицы
            laptopDAO.insert(laptop); // Вставить новый объект (запись) в таблицу

            laptopDAO.insert(new Laptop("Acer", 17, "Intel Core I5", "Nvidia", 150000)); // Вставить новый объект (запись) в таблицу
            laptopDAO.insert(new Laptop("HP", 15, "Intel Core I3", "Nvidia", 100000)); // Вставить новый объект (запись) в таблицу
          
            //laptopDAO.deleteByLastName("Ac"); // Удаление записей по фрагменту модели
            //laptopDAO.delete("HP", "I3"); // Удаление записи по модели и процессору

            List<Laptop> laptops = laptopDAO.findByFirstName("As"); // Поиск записей по фрагменту модели
            System.out.println(laptops != null ? laptops : "Нет данных");

            laptopDAO.append("Packard", 17, "Intel Core I5", "Nvidia", 150000); // Добавление записей
            laptopDAO.append("Lenovo", 15, "Intel Core I3", "Nvidia", 160000);
 
            laptopDAO.update("LG", "Asus"); // Изменение записей в таблице

            System.out.println("Данные в таблице БД:");

            List<Laptop> list = laptopDAO.selectAll();
            for (Laptop myLaptop : list) {
                System.out.println(myLaptop.getName() + " " + myLaptop.getDiagonal() + " " + myLaptop.getCpu() + " " + myLaptop.getVideo() + " " + myLaptop.getPrice());
            }

            System.out.println("\nНоутбуки с диагональю 15: " );
            Laptop laptop1 = laptopDAO.findByAge(15); // Поиск записи по диагонали ноутбука
            System.out.println(laptop1 != null ? laptop1 : "Нет данных"); // Вывод на экран найденной записи
            
            System.out.println("\nВывод записей с моделью Asus и процессором Intel Core I7: ");
            list = laptopDAO.select("Asus", "Intel Core I7");
            for (Laptop myLaptop : list) {
                System.out.println(myLaptop.getName() + " " + myLaptop.getCpu());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}
