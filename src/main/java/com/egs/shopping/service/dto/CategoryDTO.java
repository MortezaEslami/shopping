package com.egs.shopping.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    @NotNull
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "Laptop")
    private String name;

    @NotNull
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "lp")
    private String code;

    @ApiModelProperty(example = " This category is for laptop")
    private String comment;

    @ApiModelProperty(required = true, example = "1")
    private Long parentId;

    // ------------------------------

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("CategoryInfo")
    public static class Info extends CategoryDTO {
        private Long id;
        private Date createdDate;
        private Date lastModifiedDate;
    }
    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("CategoryCreateRq")
    public static class Create extends CategoryDTO {

    }

    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("CategoryUpdateRq")
    public static class Update extends CategoryDTO {
        @NotNull
        @ApiModelProperty(required = true)
        private Integer version;
    }
}
