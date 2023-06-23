package dd.projects.ddshop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="subcategory")
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category categoryId;

  @ManyToMany(mappedBy = "subcategories", fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
          })
  private List<ProductAttribute> productAttributes;

  @OneToMany(mappedBy = "subcategoryId", cascade = CascadeType.ALL)
  private List<Product> products;


  public Subcategory(String name, Category category) {
    this.name = name;
    this.categoryId = category;
  }
}
