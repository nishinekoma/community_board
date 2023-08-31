package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.application.dto.UserCommentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper//MyBatisのMapperは実行SQLファイルとJavaコードのマッピングに使用される。
public interface UserCommentMapper {
	@Insert("sql/insertUserComment.sql")//利用するSQLファイルを指す。
	void insert(@Param("dto") UserCommentDto dto);//
}
