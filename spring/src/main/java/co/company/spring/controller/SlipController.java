package co.company.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class SlipController {
	
	//1.Map이 아닌 ResponseEntity를 사용해서 JSON 데이터 처리하는 방법
	//성공,실패시 따로 처리
	@RequestMapping("/slip")
	public ResponseEntity slip(@RequestBody List<SlipVO> list) {//@RequestBody:jackson 라이브러리가 데이터 형식 변환 어쩌궁
		System.out.println(list);
		if(list.size()<1) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(null);//BAD_REQUEST=401에러
		} else {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("process", true);
		map.put("tcount", list.size()); //전체 건수 넘겨줌
		map.put("pcount", list.size()); //
//		map.put("list", list);리스트도 넘겨 줄수 있음
		return ResponseEntity.status(HttpStatus.OK).body(map);//OK = 200번
		}
		
	}

	//2.Map으로 보낼때 : 무조건 성공으로 처리
	@RequestMapping("/slip2")
	@ResponseBody
	public Map<String,Object> slip2(@RequestBody List<SlipVO> list) {//@RequestBody:jackson 라이브러리가 데이터 형식 변환 어쩌궁
		System.out.println(list);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("process", true);
		map.put("tcount", list.size()); //전체 건수 넘겨줌
		map.put("pcount", list.size()); //
//		map.put("list", list);리스트도 넘겨 줄수 있음
		return map;
		
	}
	
}
