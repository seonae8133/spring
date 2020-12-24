package co.company.spring.users.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.company.spring.users.service.UserService;
import co.company.spring.users.service.UserVO;

@Controller
public class UserController {
	
	
	@Autowired UserService  service;
	
	//등록페이지로 이동
	@GetMapping("userInsert")
	public String userInsertForm() {
		return "user/insert";
	}
	
	//등록처리
	@PostMapping("userInsert")
	public String userInsert(HttpServletRequest request, UserVO user) throws IllegalStateException, IOException {
		//request를 multipart로 캐스팅
	
		MultipartHttpServletRequest multipartRequest =
				(MultipartHttpServletRequest) request;
				//이미지파일
				MultipartFile multipartFile = multipartRequest.getFile("uploadFile");//업로드한 파일 읽어냄
				if(! multipartFile.isEmpty()&& multipartFile.getSize()>0) {//파일이 있다면
					String path = request.getSession()
							             .getServletContext()
							             .getRealPath("/images");
					System.out.println("path=" + path );
					//업로드한 파일이름을 vo에 담고
					multipartFile.transferTo(new File(path,multipartFile.getOriginalFilename()));
				    user.setProfile(multipartFile.getOriginalFilename());//user에 insert함
				}
				service.insertUser(user);
				return "user/insert";
	}
	
 }	

