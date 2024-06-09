package com.example.Delivery.Food.DTO;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    @NotBlank(message = "메뉴 이름은 비워 둘 수 없습니다.")
    private String name;

    @Positive(message = "가격은 양수여야 합니다.")
    private int price;

    @NotBlank(message = "메뉴 설명은 비워 둘 수 없습니다.")
    private String description;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;


    public FoodDTO() {
    }


}
