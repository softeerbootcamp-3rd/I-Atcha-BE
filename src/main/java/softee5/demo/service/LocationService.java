package softee5.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.entity.Member;
import softee5.demo.entity.Memo;
import softee5.demo.exception.NoContentException;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.MemberRepository;
import softee5.demo.utils.Compute;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final MemberRepository memberRepository;

    public Boolean checkFar(String id, Double latitude, Double longitude) {
        Member member = memberRepository.findById(id);

        if (member.getLatitude() == null || member.getLongitude() == null) {
            throw new NoContentException("위치 정보가 없습니다.");
        }

        double distance = Compute.haversine(latitude, longitude, member.getLatitude(), member.getLongitude());

        return distance > 2;
    }

    @Transactional
    public void setLocation(String id, Double latitude, Double longitude) {

        Member member = memberRepository.findById(id);

        member.setLocation(latitude, longitude);
    }
}
