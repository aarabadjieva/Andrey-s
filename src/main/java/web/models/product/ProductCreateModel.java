package web.models.product;

import data.entity.Category;
import data.entity.Sex;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateModel {

    private String name;
    private String description;
    private BigDecimal price;
    private Sex sex;
    private Category category;
}
