package softee5.demo.dto.response;

import lombok.Builder;
import lombok.Data;
import softee5.demo.entity.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ImageSaveResponseDto {
    private List<Long> imageIDs;

    public static ImageSaveResponseDto build(List<Image> images) {
        List<Long> ids = images.stream()
                .map(Image::getImageID) // Assuming Image class has getId method
                .toList();

        return ImageSaveResponseDto.builder()
                .imageIDs(ids)
                .build();
    }

    public static ImageSaveResponseDto build(Image image) {
        return ImageSaveResponseDto.builder()
                .imageIDs(image.getImageID())
                .build();
    }
}
