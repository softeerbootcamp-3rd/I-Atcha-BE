package softee5.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.entity.Memo;
import softee5.demo.exception.NoContentException;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.MemoRepository;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    public void createMemo(String content) {
        if(content.isBlank()){
            throw new NoContentException("메모 내용은 필수입니다.");
        }

        Memo memo = Memo.createMemo(content);
        memoRepository.save(memo);
    }

    @Transactional
    public void modifyMemo(Long memoId, String content) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new NoExistException("존재하지 않는 메모입니다."));

        memo.modifyMemo(content);
    }

}
