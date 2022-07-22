package dd.projects.ddshop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id")
  private Category categoryId;

  @ManyToMany(mappedBy = "subcategories", fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
          })
  private List<ProductAttribute> productAttributes;

  @OneToMany(mappedBy = "subcategoryId")
  private List<Product> products;


  public Subcategory(String name, Category category) {
    this.name = name;
    this.categoryId = category;
  }
}
