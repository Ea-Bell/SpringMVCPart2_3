package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price *  _this.quantity>=10000", message = "총합 10000원이 넘게 입력해주세요")
public class Item {

    @NotNull //수정 요구사항
    private Long id;
    @NotBlank(message = "공백XXX")
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;
    @NotNull
    //@Max(9999) //수정 요구사항
    private Integer quantity;
    public Item() {
    }
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
