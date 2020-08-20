package com.devils.pics;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devils.pics.domain.StudioFilter;

@SpringBootTest
class StudioFilterTest {

	@Autowired
	private SqlSession session;

	/* StudioFilter 등록 - 단위 테스트 성공 */
	@Test
	public void registerStudioFilter() {
		StudioFilter studioFilter = new StudioFilter(); 
		studioFilter.setSize(120);
		studioFilter.setOptions("카메라/조명/포토그래퍼"); 
		studioFilter.setParking(4);
		studioFilter.setUnitPrice(37000); 
		studioFilter.setDefaultCapacity(3);
		studioFilter.setMaxCapacity(5);
		studioFilter.setExcharge(5000); 
		studioFilter.setAddress("서울특별시 서초구 효령로 335");
		System.out.println(studioFilter);
		/*
		session.insert("StudioFilterMapper.registerStudioFilter", studioFilter);
		*/
	}
}
