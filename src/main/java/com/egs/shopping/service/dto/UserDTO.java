package com.egs.shopping.service.dto;

import com.egs.shopping.service.validation.UserName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @UserName
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "moris")
    private String userName;

    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(required = true, example = " Morteza")
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(required = true, example = " Eslami")
    private String lastName;

    @Email
    @ApiModelProperty(example = " morteza@test.com")
    @Size(max = 100)
    private String email;

    @NotBlank
    @ApiModelProperty(example = "123456")
    private String password;

    private boolean activated = true;

    // ------------------------------

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("UserInfo")
    public static class Info extends UserDTO {
        private Long id;

    }
    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("UserCreateRq")
    public static class Create extends UserDTO {

    }

    // ------------------------------

    @Getter
    @Setter
    @Accessors(chain = true)
    @ApiModel("UserUpdateRq")
    public static class Update extends UserDTO {
        @NotNull
        @ApiModelProperty(required = true)
        private Integer version;
    }

    @Data
    @ApiModel("UserBlocking")
    public static class BlockingDTO {
        private boolean activated;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ApiModel("UserToken")
    public static class JWTToken {
        private String token;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ApiModel("UserLogin")
    public static class LoginDTO {

        @NotNull
        private String username;

        @NotNull
        private String password;

        private boolean rememberMe;
    }
}
