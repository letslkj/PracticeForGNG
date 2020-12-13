package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BookDTO;
import com.example.demo.paging.Pagination;

@Mapper
public interface BookMapper {
    int insertBook(BookDTO dto);
    List<BookDTO> getBookList();
    List<BookDTO> getBookListWithPaging(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    BookDTO getBookInfo(String id);
    int deleteBook(String id);
    int updateBook(BookDTO dto);
    int getAllCnt();
}
