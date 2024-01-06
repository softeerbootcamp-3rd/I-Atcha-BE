package softee5.demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.entity.Member;
import softee5.demo.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void memberDataInit(){
        if(memberRepository.findById(1L).isEmpty()){
            Member testMember = Member.createMember("test", "1234", 0.0, 0.0);
            memberRepository.save(testMember);
        }
    }
}
