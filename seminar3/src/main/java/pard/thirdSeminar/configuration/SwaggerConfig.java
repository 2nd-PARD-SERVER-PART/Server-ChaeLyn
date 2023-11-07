package pard.thirdSeminar.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //어노테이션별로 패키지 나눈거란당~
public class SwaggerConfig {
    @Bean //내가 직접 올리겠다 주입하겠음 config - bean ㄱㅌ이 움직여
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                .title("Swagger 처음 써용 히힛")
                .description("메롱메롱추현이춤춰줘")
                .version("1.0.0"); //완전 바뀜. 꽤 바뀜. 사소하게 바뀜
    }
}
