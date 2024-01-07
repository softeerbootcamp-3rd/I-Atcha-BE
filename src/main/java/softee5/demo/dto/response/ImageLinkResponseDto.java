package softee5.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import softee5.demo.entity.Image;

@Builder
@Getter
public class ImageLinkResponseDto {
    private String link;

    public static ImageLinkResponseDto build(Image image) {
        return ImageLinkResponseDto.builder()
                .link(image.getLink())
                .build();
    }
}
