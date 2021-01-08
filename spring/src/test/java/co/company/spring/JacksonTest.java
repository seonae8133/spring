package co.company.spring;



import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.company.spring.controller.SlipVO;

public class JacksonTest {
	
	@Test
	public void test1() {
		ObjectMapper mapper = new ObjectMapper();
		String str =
		"[{\"slipAmount\":13860,\"salDt\":\"202101\",\"customer\":\""
		+ "149\",\"bankAcct\":\"1\"},{\"slipAmount\":15730,\"salDt\":"
		+ "\"202101\",\"customer\":\"174\",\"bankAcct\":\"1\"}]";
		
		//1.JsonNode node = mapper.readTree(str);
		try {
			SlipVO[] list;
			list = mapper.readValue(str, SlipVO[].class);
			//1.System.out.println(node.get(0).path("slipAmount"));
			System.out.println(list[0].getSlipAmount());
			
			URL url = new URL("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101");
			JsonNode movie = mapper.readTree(url);
			System.out.println(movie);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
}
