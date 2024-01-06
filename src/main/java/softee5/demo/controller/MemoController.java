package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.MemoRequestDto;
import softee5.demo.dto.response.MemoResponseDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.MemoService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<? extends BasicResponse> createMemo(@RequestBody @Valid MemoRequestDto memoRequestDto){
        MemoResponseDto memoResponseDto = memoService.createMemo(memoRequestDto.getContent());

        return ResponseEntity.ok().body(new DataResponse(memoResponseDto));
    }

    @PutMapping("/{memo_id}")
    public ResponseEntity<? extends BasicResponse> modifyMemo(@PathVariable("memo_id") Long memoId, @RequestBody @Valid MemoRequestDto memoRequestDto){
        memoService.modifyMemo(memoId, memoRequestDto.getContent());

        return ResponseEntity.ok().body(new SuccessResponse());
    }

}
