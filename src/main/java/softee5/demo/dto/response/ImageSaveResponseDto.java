package softee5.demo.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import softee5.demo.entity.Image;

import java.util.List;
import java.util.stream.Stream;

@Data
@Builder
public class ImageSaveResponseDto {
    private List<ImageData> imageDataList;

    public static ImageSaveResponseDto build(List<Image> images) {
        List<ImageData> imageDataList = images.stream()
                .map(m -> ImageData.builder()
                        .id(m.getImageID())
                        .imageLink(m.getLink())
                        .build())
                .toList();
        return ImageSaveResponseDto.builder()
                .imageDataList(imageDataList)
                .build();
    }

    public static ImageSaveResponseDto build(Image images) {
        List<ImageData> imageDataList = Stream.of(images)
                .map(m -> ImageData.builder()
                        .id(m.getImageID())
                        .imageLink(m.getLink())
                        .build())
                .toList();
        return ImageSaveResponseDto.builder()
                .imageDataList(imageDataList)
                .build();
    }

    @Builder
    @Getter
    static class ImageData {
        private long id;
        private String imageLink;
    }
}
