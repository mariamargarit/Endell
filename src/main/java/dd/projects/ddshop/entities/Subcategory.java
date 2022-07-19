package dd.projects.ddshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category categoryId;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "subcategory_product_attribute",
          joinColumns = @JoinColumn(name = "subcategory_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "product_attribute_id",
                  referencedColumnName = "id"))
  private List<ProductAttribute> productAttributes;

  @OneToMany(mappedBy = "subcategoryId")
  private List<Product> products;


}
