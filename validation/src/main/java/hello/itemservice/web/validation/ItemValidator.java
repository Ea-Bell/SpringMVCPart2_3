package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //Item == class
        //Item == subItem
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item=(Item) target;

        log.info("ItemName= {}",item.getItemName());

        if(!StringUtils.hasText(item.getItemName())){
            // bindingFauluere:은 들어온 데이터가 잘들어왔는지에 대한 여부 false한 이유 if문에서부터 item.getItemName을 검증을 하고 들어오기때문
            errors.rejectValue("itemName", "required");
        }
        if(item.getPrice()== null || item.getPrice()<1000 || item.getPrice()>1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 10000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity()>= 9999){
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity()!=null){
            int resultPrice = item.getPrice()*item.getQuantity();
            if(resultPrice<10000){
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }


}
