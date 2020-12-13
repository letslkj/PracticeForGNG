package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BookMapper;
import com.example.demo.dto.BookDTO;
import com.example.demo.paging.Pagination;

@Controller
public class DemoController {
	
	@Autowired BookMapper bookMapper;
	
	@GetMapping("/")
	public String index() {

		return "redirect:loginForm";
	}

	@GetMapping("loginForm")
	public String loginForm(Model model) {

		return "loginForm";
	}
	
	@GetMapping("bookList")
	public String bookList(Model model, @RequestParam(defaultValue = "1") int page) {
		
		//全リスト数
		int totalListCnt = bookMapper.getAllCnt();
		
		//paginationオブジェクト作成
		Pagination pagination = new Pagination(totalListCnt, page);
		
		//DBインデックス
		int startIndex = pagination.getStartIndex();
		//ページに表示される最大リスト数
		int pageSize = pagination.getPageSize();

		try {
			List<BookDTO> bookList = bookMapper.getBookListWithPaging(startIndex, pageSize); //DBの本のリストを持ってくる
			
			model.addAttribute("bookList", bookList);
			model.addAttribute("pagination", pagination);
			return "bookList";
			
		} catch (Exception e) {
			model.addAttribute("errMsg", "DBエラー");
			return "bookList";
		}
	}

	@PostMapping("login")
	public String login(HttpServletRequest req, Model model, @RequestParam String loginid, String loginpw) {
		HttpSession session = req.getSession();
		
		// アカウント情報と一致しているかをチェック
		if (loginid.equals("admin") && loginpw.equals("Admin1234")) { // 一致時に成功ページに移動
			session.setAttribute("loginid", loginid);
			return "redirect:bookList";
		} else {
		// 形式の不一致時にエラーメッセージと共にログインフォームに移動
			session.setAttribute("loginid", null);
			model.addAttribute("errMsg", "一致する情報がありません。");
			return "loginForm";
		}
		
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}

	@PostMapping("saveBook")
	public String saveBook(Model model, @RequestParam String itemName, String itemAuthor, String itemPublisher,
			String itemPublicationDate, String itemPrice) throws Exception {
		
		System.out.println("itemName : " + itemName);
		System.out.println("itemAuthor : " + itemAuthor);
		System.out.println("itemPublisher : " + itemPublisher);
		System.out.println("itemPublicationDate : " + itemPublicationDate);
		System.out.println("itemPrice : " + itemPrice);
		
        String publisher = null;
        if(!itemPublisher.equals("")) { //値が存在する場合
        	publisher = itemPublisher;
        }
        Date publicationDate = null;
        if(!itemPublicationDate.equals("")) { //値が存在する場合
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
        	publicationDate = sdf.parse(itemPublicationDate);
        }
        int price = Integer.parseInt(itemPrice);
		
        try { //DBへの入力
        	BookDTO dto = new BookDTO(itemName, itemAuthor, publisher, publicationDate, price);
        	bookMapper.insertBook(dto);
    		
        	model.addAttribute("resultMsg3", "本を登録しました");
    		return "resultPage";
    		
        } catch (Exception e) { //登録失敗
    		
        	model.addAttribute("resultMsg1", "登録中にエラーが発生しました。");
			model.addAttribute("resultMsg2", "管理者にお問合せ下さい。");
			return "resultPage";
		}
		
	}
	
	@PostMapping("deleteBook")
	public String deleteBook(Model model, @RequestParam String bookId) {
		
        try { //DBへの入力
        	bookMapper.deleteBook(bookId);
        	
    		return "redirect:bookList";
    		
        } catch (Exception e) { //登録失敗
    		
        	model.addAttribute("resultMsg1", "削除中にエラーが発生しました。");
			model.addAttribute("resultMsg2", "管理者にお問合せ下さい。");
			return "resultPage";
		}
		
	}
	
	@GetMapping("registrationForm")
	public String registrationForm(Model model) {
		model.addAttribute("reg", "reg");
		return "regAndUpdateForm";
	}
	
	@PostMapping("goUpdateForm")
	public String updateForm(Model model, @RequestParam String bookId) {
		
		model.addAttribute("update", "update");

		BookDTO bookDTO = bookMapper.getBookInfo(bookId);
		model.addAttribute("bookDTO", bookDTO);

		return "regAndUpdateForm";
	}
	
	@PostMapping("updateBook")
	public String updateBook(Model model, @RequestParam String itemName, String itemAuthor, String itemPublisher,
			String itemPublicationDate, String itemPrice, String bookId) throws ParseException {
		
		String publisher = null;
        if(!itemPublisher.equals("")) { //値が存在する場合
        	publisher = itemPublisher;
        }
        Date publicationDate = null;
        if(!itemPublicationDate.equals("")) { //値が存在する場合
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
        	publicationDate = sdf.parse(itemPublicationDate);
        }
        int price = Integer.parseInt(itemPrice);
		
        int id = Integer.parseInt(bookId);
        
        try { //DBへの入力
        	BookDTO dto = new BookDTO(id, itemName, itemAuthor, publisher, publicationDate, price);
        	bookMapper.updateBook(dto);
    		
        	model.addAttribute("resultMsg3", "本を修正しました");
    		return "resultPage";
    		
        } catch (Exception e) { //登録失敗
        	model.addAttribute("bookId", bookId);
        	model.addAttribute("resultMsg1", "修正中にエラーが発生しました。");
			model.addAttribute("resultMsg2", "管理者にお問合せ下さい。");
			return "resultPage";
		}
		
	}


}
