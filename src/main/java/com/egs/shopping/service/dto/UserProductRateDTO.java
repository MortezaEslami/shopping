package com.egs.shopping.service.dto;

import com.egs.shopping.service.validation.Rate;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProductRateDTO {

    @Rate
    @ApiModelProperty(required = true, example = "5")
    private Integer rate;

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long productId;

    @ApiModelProperty(required = true, example = " Perfect laptop !")
    private String comment;

    // ------------------------------

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("UserProductRateInfo")
    public static class Info extends UserProductRateDTO {
        private Long id;
        private Date createdDate;
        private Date lastModifiedDate;
        private Long userId;

    }
    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("UserProductRateCreateRq")
    public static class Create extends UserProductRateDTO {

    }

    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("UserProductRateUpdateRq")
    public static class Update extends UserProductRateDTO {
        @NotNull
        @ApiModelProperty(required = true)
        private Integer version;
    }
}
