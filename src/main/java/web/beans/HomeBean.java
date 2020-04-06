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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<ProductViewModel> allProducts;
    private ProductService productService;
    private ModelMapper mapper;

    @Inject
    public HomeBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.mapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        allProducts = productService.findAllProducts()
                .stream()
                .map(p->mapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    public List<ProductViewModel> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<ProductViewModel> allProducts) {
        this.allProducts = allProducts;
    }

    public void seeProductDetails(String productName) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("productName", productName);
        redirect("/products/details?name="+productName);
    }
}
