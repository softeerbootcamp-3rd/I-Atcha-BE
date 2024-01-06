package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.entity.Member;
import softee5.demo.repository.MemberRepository;
import softee5.demo.utils.Compute;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final MemberRepository memberRepository;

    public Boolean checkFar(String id, Double latitude, Double longitude) {
        Member member = memberRepository.findById(id);

        double distance = Compute.haversine(latitude, longitude, member.getLatitude(), member.getLongitude());
        System.out.println("distance = " + distance);

        return distance > 2;
    }
}
