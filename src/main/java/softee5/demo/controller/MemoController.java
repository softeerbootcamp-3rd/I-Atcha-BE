package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softee5.demo.dto.request.CreateMemoRequest;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.MemoService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

//    private final MemoService memoService;
//
//    @PostMapping
//    public ResponseEntity< ? extends BasicResponse> createMemo(@RequestBody @Valid CreateMemoRequest createMemoRequest){
//        memoService.createMemo();
//        return ResponseEntity.ok().body(new SuccessResponse());
//    }

}
