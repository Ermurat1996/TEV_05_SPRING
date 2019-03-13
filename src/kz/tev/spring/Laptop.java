package kz.tev.spring;

public class Laptop {

    int id;
    String name;
    int diagonal;
    String cpu;
    String video;
    int price;

    public Laptop() {
        this.id = 0;
        this.name = "";
        this.diagonal = 0;
        this.cpu = "";
        this.video = "";
        this.price = 0;
    }

    public Laptop(String name, int diagonal, String cpu, String video, int price) {
        this.id = 0;
        this.name = name;
        this.diagonal = diagonal;
        this.cpu = cpu;
        this.video = video;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public String getCpu() {
        return cpu;
    }

    public String getVideo() {
        return video;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Модель=%s, Диагональ=%d, Процессор=%s, Видео=%s, Цена=%d", name, diagonal, cpu, video, price);
    }
}
