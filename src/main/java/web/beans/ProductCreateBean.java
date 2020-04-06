package web.beans;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import service.ProductService;
import service.models.ProductServiceModel;
import web.models.product.ProductCreateModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@NoArgsConstructor
@Named
@RequestScoped
public class ProductCreateBean extends BaseBean{

    private ProductCreateModel model;
    private ProductService productService;
    private ModelMapper modelMapper;

    @Inject
    public ProductCreateBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        model = new ProductCreateModel();
    }

    public ProductCreateModel getModel() {
        return model;
    }

    public void setModel(ProductCreateModel model) {
        this.model = model;
    }

    public void createProduct() throws IOException {
        try{
            productService.saveProduct(modelMapper.map(model, ProductServiceModel.class));
        }catch (Exception e){
            return;
        }
        redirect("/home");
    }
}
