package com.egs.shopping.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    @NotNull
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "lenovo")
    private String name;

    @NotNull
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "lnv")
    private String code;

    @ApiModelProperty(example = " This product is loptop")
    private String comment;

    @NotNull
    @ApiModelProperty(required = true,example = "1000")
    private BigDecimal price;

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long categoryId;

    // ------------------------------

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("ProductInfo")
    public static class Info extends ProductDTO {
        private Long id;
        private Date createdDate;
        private Date lastModifiedDate;
        private String categoryName;
        private Double rate;


    }
    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("ProductCreateRq")
    public static class Create extends ProductDTO {

    }

    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("ProductUpdateRq")
    public static class Update extends ProductDTO {
        @NotNull
        @ApiModelProperty(required = true)
        private Integer version;
    }


    @Data
    public static class FilterDTO {
        private String name;
        private BigDecimal fromPrice;
        private BigDecimal toPrice;
        private Double fromRate;
        private Double toRate;
    }
}
