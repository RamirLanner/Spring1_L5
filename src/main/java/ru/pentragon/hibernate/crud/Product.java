package ru.pentragon.hibernate.crud;


import javax.persistence.*;
import java.util.Objects;

//по хорошему надо бы добавить lombok
@Entity
@Table(name = "products" )
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private float cost;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Product(Long id, String title, float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, float cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.cost, cost) == 0 && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
