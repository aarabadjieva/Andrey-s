package web.beans;

import data.entity.Category;
import data.entity.Sex;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@NoArgsConstructor
@Named
@RequestScoped
public class EnumBean {

     public Sex[] sexNames(){
        return Sex.values();
    }

    public Category[] categories(){
        return Category.values();
    }
}
