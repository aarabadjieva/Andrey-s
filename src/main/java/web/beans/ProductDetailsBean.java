package web.beans;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import service.ProductService;
import web.models.product.ProductViewModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@NoArgsConstructor
@Named
@RequestScoped
public class ProductDetailsBean extends BaseBean{

    private ProductViewModel product;
    private ModelMapper mapper;
    private ProductService productService;

    @Inject
    public ProductDetailsBean(ModelMapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    public ProductViewModel getProductByName(String productName){
        return mapper.map(productService.findByName(productName), ProductViewModel.class);
    }

    public void delete(String productName) throws IOException {
        productService.deleteProduct(productName);
        redirect("/home");
    }
}
