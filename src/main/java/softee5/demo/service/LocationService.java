package softee5.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.entity.Member;
import softee5.demo.exception.ErrorMessage;
import softee5.demo.exception.NoContentException;
import softee5.demo.repository.MemberRepository;
import softee5.demo.utils.Compute;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final MemberRepository memberRepository;

    public Boolean checkFar(Long id, Double latitude, Double longitude) {
        Member member = memberRepository.findById(1L).get();

        if (member.getLatitude() == null || member.getLongitude() == null) {
            throw new NoContentException(ErrorMessage.NOT_EXIST_LOCATION);
        }

        double distance = Compute.haversine(latitude, longitude, member.getLatitude(), member.getLongitude());

        return distance > 2;
    }

    @Transactional
    public void setLocation(Long id, Double latitude, Double longitude) {

        Member member = memberRepository.findById(1L).get();

        member.setLocation(latitude, longitude);
    }
}
